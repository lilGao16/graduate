package com.example.healthdata.controller;

import com.example.healthdata.Util.Predict;
import com.example.healthdata.entity.Data;
import com.example.healthdata.entity.ImData;
import com.example.healthdata.entity.Record;
import com.example.healthdata.service.DataService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @RequestMapping("/search/{name}")
    public ImData patient(HttpServletRequest request, HttpServletResponse response, @PathVariable("name")String name) {
        Data data = dataService.searchByName(name);
        ImData imdata = new ImData();
        imdata.inject(data);
        return imdata;
    }

    @RequestMapping("/update")
    public String update(@RequestBody Data data) {
        //System.out.println(data);
        dataService.update(data);
        return "Update OK!";

    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String path = "D:\\作业\\HealthData\\" + file.getOriginalFilename();
        File destFile = new File(path);
        // 如果没有就根据地址创建一个
        if(!destFile.exists()){
            destFile.getParentFile().mkdirs();
        }
        file.transferTo(destFile);
        //System.out.println(fileName);
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        for(int i = 1; i < sheet.getLastRowNum()+1; i++) {
            Row row = sheet.getRow(i); //i是行数
            Double d = row.getCell(1).getNumericCellValue();
            Data data = new Data(/*row.getCell(0).toString(), d.intValue()*/);
            //System.out.println(data);
            dataService.insert(data);
        }
        return "Upload OK!";
    }

    @RequestMapping("/getall")
    public List<ImData> allPatient() {
        List<Data> datas = dataService.searchAll();
        List<ImData> imDatas = new ArrayList<>();
        for(Data data : datas) {
            ImData imData = new ImData();
            imData.inject(data);
            imDatas.add(imData);
        }
        return imDatas;
    }

    @RequestMapping("/insert")
    public String newPatient(@RequestBody Data data) {
        dataService.insert(data);
        return "Insert OK!";
    }

    @RequestMapping("/delete/{name}")
    public String deletePatient(@PathVariable("name")String name) {
        dataService.delete(name);
        return "Delete OK!";
    }

    @RequestMapping("/predict")
    public String toPredict(@RequestBody Data data) {
        Predict p = new Predict();
        Double d = Predict.predict(data.getHeight(), data.getWeight(), data.getHeat(), data.getPulse());
        if(d == 1.0) {
            return "1";
        }
        else {
            return "0";
        }
    }

    @RequestMapping("/searchlike/{name}")
    public List<ImData> fuzzySearch(@PathVariable("name")String name) {
        name += "%";
        System.out.println(name);
        List<Data> datas = dataService.fuzzySearch(name);
        List<ImData> imDatas = new ArrayList<>();
        for(Data data : datas) {
            ImData imData = new ImData();
            imData.inject(data);
            imDatas.add(imData);
        }
        return imDatas;
    }

    @RequestMapping("/getrecord/{name}")
    public Record getRecord(@PathVariable("name")String name) {
        System.out.println(name);
        Record record = dataService.getRecord(name);
        return record;
    }
}
