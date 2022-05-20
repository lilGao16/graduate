package com.example.healthdata.Util;

import org.springframework.stereotype.Component;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Double.NaN;

@Component
public class Predict {
    public static Double predict(Double height, Double weight, Double heat, Double pulse) {
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
            instance.setValue(0,height);
            instance.setValue(1,weight);
            instance.setValue(2,heat);
            instance.setValue(3,pulse);
            instance.setValue(4,NaN);
            instances.add(instance);
            instance.setDataset(instances);
            ArffSaver saver = new ArffSaver();
            saver.setInstances(instances);
            /*try {
                File f = new File("D:\\作业\\毕设\\data_example1res.arff");
                f.delete();
                saver.setFile(new File("D:\\作业\\毕设\\data_example1res.arff"));
                saver.writeBatch();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            Instances unlabeled = new Instances(
                    new BufferedReader(
                            new FileReader("D:\\作业\\毕设\\data_example1res.arff")));
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            double clsLabel2 = cfs.classifyInstance(instances.instance(0));
            System.out.println(clsLabel2);
            return clsLabel2;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
