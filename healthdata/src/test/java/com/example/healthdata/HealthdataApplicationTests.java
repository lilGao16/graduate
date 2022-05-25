package com.example.healthdata;

import com.example.healthdata.Util.Predict;
import com.example.healthdata.entity.Data;
import com.example.healthdata.entity.ImData;
import com.example.healthdata.entity.Record;
import com.example.healthdata.entity.User;
import com.example.healthdata.mapper.UserMapper;
import com.example.healthdata.service.DataServiceImpl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.*;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Double.NaN;

@SpringBootTest
class HealthdataApplicationTests {

    @Autowired
    DataServiceImpl dataService;
    @Autowired
    UserMapper userMapper;
    String path = "D:\\作业\\HealthData.xlsx";
    String arffpath = "D:\\作业\\毕设\\myinstance.arff";
    @Test
    void contextLoads() throws IOException {

        Instances ins = null;
        Classifier cfs = null;
        try {
            // read the training set
            File file = new File("D:\\作业\\HealthData\\iris.arff");
            ArffLoader loader = new ArffLoader();   //ArffLoader类是weka.core.converters下的
            loader.setFile(file);
            ins = loader.getDataSet();
            //System.out.println(ins.numAttributes());
            ins.setClassIndex(ins.numAttributes() - 1);

            // 初始化分类器
            cfs = (Classifier) Class.forName("weka.classifiers.bayes.NaiveBayes").newInstance();

            // 使用训练集对数据集训练
            cfs.buildClassifier(ins);

            // 使用测试数据集测试分类器的性能
            /*Instance testInst;

            Evaluation testingEvaluation = new Evaluation(ins);
            int length = ins.numInstances(); //得到数据集样本个数

            for (int i = 0; i < length; i++) {
                testInst = ins.instance(i);
                testingEvaluation.evaluateModelOnceAndRecordPrediction(cfs, testInst);
            }

            // print the classifying results
            System.out.println("分类正确率:" + (1 - testingEvaluation.errorRate()));*/

            /*for (SecRepoEntity secRepoEntity: entities) {
                Instance instance = new DenseInstance(attributes.size());
                instance.setValue(0,secRepoEntity.getForkCount());
                instance.setValue(1,secRepoEntity.getSize());
                instance.setValue(2,secRepoEntity.getSumFollower());
                instance.setValue(3,secRepoEntity.getAvgFollower());
                instance.setValue(4,secRepoEntity.getWeightFollower());
                instances.add(instance);
            }*/

            Instances unlabeled = new Instances(

                    new BufferedReader(

                            new FileReader("D:\\作业\\HealthData\\iris-1.arff")));

            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

            //System.out.println(unlabeled);
            Instances labeled = new Instances(unlabeled);
            for (int i = 0; i < unlabeled.numInstances(); i++) {

                double clsLabel = cfs.classifyInstance(unlabeled.instance(i));
                //System.out.println(clsLabel);
                labeled.instance(i).setClassValue(clsLabel);
                /*BufferedWriter writer = new BufferedWriter(

                        new FileWriter("D:\\作业\\HealthData\\labeled.arff"));

                writer.write(labeled.toString());

                writer.newLine();

                writer.flush();

                writer.close();*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void csv2arff() throws Exception{
        Instances allData = ConverterUtils.DataSource.read("D:\\java项目\\healthdata\\src\\main\\resources\\299s.CSV");
        ArffSaver saver = new ArffSaver();
        saver.setInstances(allData);
        saver.setFile(new File("D:\\java项目\\healthdata\\src\\main\\resources\\299s.arff"));
        saver.writeBatch();
        System.out.println("已经转化为arff文件");
    }

    @Test
    void myinstance() throws Exception{
        Instances ins = null;
        Classifier cfs = null;
        try {
            // read the training set
            File file = new File("D:\\作业\\HealthData\\iris.arff");
            ArffLoader loader = new ArffLoader();   //ArffLoader类是weka.core.converters下的
            loader.setFile(file);
            ins = loader.getDataSet();
            //System.out.println(ins.numAttributes());
            ins.setClassIndex(ins.numAttributes() - 1);

            // 初始化分类器
            cfs = (Classifier) Class.forName("weka.classifiers.bayes.NaiveBayes").newInstance();

            // 使用训练集对数据集训练
            cfs.buildClassifier(ins);
            ArrayList<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("sepallength"));
            attributes.add(new Attribute("sepalwidth"));
            attributes.add(new Attribute("petallength"));
            attributes.add(new Attribute("petalwidth"));
            ArrayList<String> labels=new ArrayList<String>();
            labels.add("Iris-setosa");
            labels.add("Iris-versicolor");
            labels.add("Iris-virginica");
            attributes.add(new Attribute("class", labels));
            //set instances
            Instances instances = new Instances("repo_popular",attributes,1);
            instances.setClassIndex(instances.numAttributes() - 1);
            //add instance
            Instance instance = new DenseInstance(attributes.size());

            instance.setValue(0,5.9);
            instance.setValue(1,3);
            instance.setValue(2,5.1);
            instance.setValue(3,1.8);
            instance.setValue(4,NaN);
            instances.add(instance);
            instance.setDataset(instances);
            ArffSaver saver = new ArffSaver();
            saver.setInstances(instances);
            try {
                saver.setFile(new File(arffpath));
                saver.writeBatch();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Instances unlabeled = new Instances(
                    new BufferedReader(
                            new FileReader("D:\\作业\\毕设\\myinstance.arff")));
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            double clsLabel2 = cfs.classifyInstance(unlabeled.instance(0));
            System.out.println(instance.value(0));
            System.out.println(clsLabel2);
            /*double clsLabel2 = cfs.classifyInstance(instances.instance(0));
            System.out.println(instance.value(0));
            System.out.println(clsLabel2);*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void datawash() throws IOException{
        File path = new File("D:\\java项目\\healthdata\\src\\main\\resources\\data_example.xlsx");
        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        for(int i = 1; i < sheet.getLastRowNum()+1; i++) {
            Row row = sheet.getRow(i); //i是行数
            if(row.getCell(2).getCellType().toString().equals("STRING")) {
                String value = row.getCell(2).getStringCellValue();
                //System.out.println(value);
                if(value.equals("")) {
                    System.out.println("set!");
                    row.createCell(2).setCellValue("36");
                }
            }
            else {
                Double d = row.getCell(2).getNumericCellValue();
                if(d == 0.0) {
                    System.out.println("set!");
                    row.createCell(2).setCellValue(36);
                }
                System.out.println(d);
            }
        }
    }

    @Test
    void predict() {
        Instances ins = null;
        Classifier cfs = null;
        try {
            // read the training set
            File file = new File("D:\\java项目\\healthdata\\src\\main\\resources\\299s.arff");
            ArffLoader loader = new ArffLoader();   //ArffLoader类是weka.core.converters下的
            loader.setFile(file);
            ins = loader.getDataSet();
            //System.out.println(ins.numAttributes());
            ins.setClassIndex(ins.numAttributes() - 1);

            // 初始化分类器
            cfs = (Classifier) Class.forName("weka.classifiers.bayes.NaiveBayes").newInstance();

            // 使用训练集对数据集训练
            cfs.buildClassifier(ins);
            ArrayList<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("height"));
            attributes.add(new Attribute("weight"));
            attributes.add(new Attribute("heat"));
            attributes.add(new Attribute("pulse"));
            ArrayList<String> labels=new ArrayList<String>();
            labels.add("0");
            labels.add("1");
            attributes.add(new Attribute("sick", labels));
            //set instances
            Instances instances = new Instances("299s",attributes,1);
            instances.setClassIndex(instances.numAttributes() - 1);
            //add instance
            Instance instance = new DenseInstance(attributes.size());

            instance.setValue(0,173);
            instance.setValue(1,70);
            instance.setValue(2,36);
            instance.setValue(3,80);
            instance.setValue(4,NaN);
            instances.add(instance);
            instance.setDataset(instances);
            ArffSaver saver = new ArffSaver();
            saver.setInstances(instances);
            try {
                saver.setFile(new File("D:\\作业\\毕设\\data_example1res.arff"));
                saver.writeBatch();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Instances unlabeled = new Instances(
                    new BufferedReader(
                            new FileReader("D:\\作业\\毕设\\data_example1res.arff")));
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            double clsLabel2 = cfs.classifyInstance(unlabeled.instance(0));
            System.out.println(clsLabel2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test //和上面csv2arff一样的
    void toarff() throws Exception{
        Instances allData = ConverterUtils.DataSource.read("D:\\作业\\毕设\\test.csv");
        ArffSaver saver = new ArffSaver();
        saver.setInstances(allData);
        saver.setFile(new File("D:\\作业\\毕设\\test.arff"));
        saver.writeBatch();
        System.out.println("已经转化为arff文件");
    }

    @Test
    void tongjiTest() throws Exception{
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
            if(record.getKeeptime1() != null){
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
    }

    @Test
    void userTest()throws Exception {
        //userMapper.insert(new User("new", "123456", "notad"));
        //System.out.println(userMapper.select());
        //userMapper.update(new User("new", "1234567", "notad"));
        System.out.println(userMapper.selectOne("admi"));
    }
}
