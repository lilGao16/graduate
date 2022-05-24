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
import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping("/update1")
    public String update1(@RequestBody Data data) {
        //System.out.println(data);
        dataService.update1(data);
        return "Update OK!";

    }

    @RequestMapping("/update2")
    public String update2(@RequestBody Record record) {
        //System.out.println(data);
        dataService.update2(record);
        return "Update OK!";

    }

    @RequestMapping("/upload")
    public String upload(MultipartFile file) throws Exception {
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
            Data data = new Data();
            //System.out.println(data);
            data.setName(row.getCell(0).toString());
            data.setGender(row.getCell(1).toString());
            data.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(row.getCell(2).toString()));
            data.setAddress(row.getCell(3).toString());
            data.setPhoneNum(row.getCell(4).toString());
            dataService.insert(data);
            Record record = new Record();
            record.setName(row.getCell(0).toString());
            dataService.insert2(record);
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
        Record record = new Record();
        record.setName(data.getName());
        dataService.insert2(record);
        return "Insert OK!";
    }

    @RequestMapping("/delete/{name}")
    public String deletePatient(@PathVariable("name")String name) {
        dataService.delete(name);
        dataService.delete2(name);
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

    @RequestMapping("/statistics")
    public List<Integer> statistics() {
        List<Data> datas = dataService.searchAll();
        int male = 0;  int female = 0;  int below60 = 0;  int sixtyTo70 = 0;  int seventyTo80 = 0;
        int beyond80 = 0;
        for (Data data: datas) {
            if (data.getGender().equals("男")) {
                male++;
            }
            if(data.getGender().equals("女")) {
                female++;
            }
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            cal.setTime(data.getBirthday());
            int yearBirth = cal.get(Calendar.YEAR);
            int age = yearNow - yearBirth;
            if(age <= 60) {
                below60++;
            }
            if(age > 60 && age <= 70) {
                sixtyTo70++;
            }
            if(age > 70 && age <= 80) {
                seventyTo80++;
            }
            if(age > 80) {
                beyond80++;
            }
        }
        List<Record> records = dataService.getAllRecord();
        int ability = 0;  int disability = 0;  int keep0 = 0;  int keep30 = 0;
        int keep60 = 0;  int keep90 = 0;  int keep120 = 0;
        for(Record record : records) {
            if(record.getAbility() != null) {
                if(record.getAbility().equals("可自理")) {
                    ability++;
                }
                if(record.getAbility().equals("不能自理")) {
                    disability++;
                }
            }
            if(record.getKeeptime1() != null) {
                int time = Integer.parseInt(record.getKeeptime1());
                if(time == 0) {
                    keep0++;
                }
                if(time == 30) {
                    keep30++;
                }
                if(time == 60) {
                    keep60++;
                }
                if(time == 90) {
                    keep90++;
                }
                if(time == 120) {
                    keep120++;
                }
            }
        }
        List<Integer> s = new ArrayList<>();
        s.add(male);
        s.add(female);
        s.add(below60);
        s.add(sixtyTo70);
        s.add(seventyTo80);
        s.add(beyond80);
        s.add(ability);
        s.add(disability);
        s.add(keep0);
        s.add(keep30);
        s.add(keep60);
        s.add(keep90);
        s.add(keep120);
        return s;
    }
}
