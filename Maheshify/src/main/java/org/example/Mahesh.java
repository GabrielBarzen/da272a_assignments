package org.example;
import java.util.*;

public class Mahesh {
    public static class Node {
        String attribute;
        Map<String, Node> children;
        String label;

        public Node(String attribute) {
            this.attribute = attribute;
            children = new HashMap<>();
        }

        public Node(String label, String attribute) {
            this.label = label;
            this.attribute = attribute;
            children = new HashMap<>();
        }
    }


    public static void main(String[] args) {
        new Mahesh();
    }
    Mahesh(){
        String[][] tempExamples =
                {
                        {"sunny","sunny","overcast","rain","rain","rain","overcast","sunny","sunny","rain","sunny","overcast","overcast","rain"},
                        {"hot","hot","hot","mild","cool","cool","cool","mild","cool","mild","mild","mild","hot","mild"},
                        {"high","high","high","high","normal","normal","normal","high","normal","normal","normal","high","normal","high"},
                        {"weak","strong","weak","weak","weak","strong","strong","weak","weak","weak","strong","strong","weak","strong"},
                        {"no","no","yes","yes","yes","no","yes","no","yes","yes","yes","yes","yes","no"},
                };
        String[] tempLabels = {"outlook","temperature", "humidity", "wind", "tennis"};


        List<List<String>> examples = new ArrayList<>();
        for (String[] tempExample : tempExamples) {
            System.out.println("Array convert column : " + Arrays.asList(tempExample));
            examples.add(Arrays.asList(tempExample));
        }
        for (List<String> example : examples) {
            System.out.println("Example column : " + example);
        }
        List<String> labels = Arrays.asList(tempLabels);
        System.out.println("Labels : " + labels);

        id3(examples, labels);
    }

    public Node id3(List<List<String>> examples, List<String> labels) {
        double setEntropy = entropy(examples.get(examples.size()-1));
        System.out.println("Set entropy : " + setEntropy);


        for (int i = 0; i < labels.size() - 1; i++) {

            HashMap<String,Double> entropyMap = entropy(examples.get(i), examples.get(labels.size()-1));
            double labelAvreageEntropy = 0.0;
            for (String attributeValue : entropyMap.keySet()) {
                Double divisor = (double) examples.get(examples.size()-1).size();
                Double dividend = 0.0;
                for (String attributeCompareValue : examples.get(i)) {
                    if (attributeValue.equals(attributeCompareValue)) {
                        dividend++;


                    }
                }
                labelAvreageEntropy += (dividend/divisor) * entropyMap.get(attributeValue);
            }
            double informationGain = setEntropy - labelAvreageEntropy;
            //System.out.println("Set entropy for : " + labels.get(i) + ", : " + setEntropy);
            //System.out.println("Label average entropy for : " + labels.get(i) + ", : " + labelAvreageEntropy);
            System.out.println("Information gain for : " + labels.get(i) + ", : " + informationGain);
        }



        return null;
    }

    private HashMap<String,Double> entropy(List<String> attribute, List<String> classAttribute) {
        HashMap<String,HashMap<String,Integer>> outcomeProportion = new HashMap<>();

        for (int i = 0; i < attribute.size(); i++) {
            //If outcome proportion contains an attribute, ex rain.
            if (outcomeProportion.containsKey(attribute.get(i))) {
                //Get the hashmap for rain.
                HashMap<String,Integer> proportionFrequencyMap = outcomeProportion.get(attribute.get(i));
                //If the proportion contains the class attribute
                if (proportionFrequencyMap.containsKey(classAttribute.get(i))) {
                    //add one to the frequency;
                    proportionFrequencyMap.put(classAttribute.get(i),proportionFrequencyMap.get(classAttribute.get(i)) + 1);
                } else {
                    //Set one as the frequency
                    proportionFrequencyMap.put(classAttribute.get(i), 1);
                }
            } else {
                //If it does not contain an attribute
                HashMap<String,Integer> proportionFrequencyMap = new HashMap<>();
                proportionFrequencyMap.put(classAttribute.get(i),1);
                outcomeProportion.put(attribute.get(i),proportionFrequencyMap);

            }

        }

        HashMap<String, Double> entropyMap = new HashMap<>();
        for (String s : outcomeProportion.keySet()) {
            entropyMap.put(s,entropy(outcomeProportion.get(s)));
        }


        return entropyMap;
    }

    public double entropy(List<String> values) {
        return entropy(values,null, 0);
    }
    public double entropy(HashMap<String,Integer> premadeFrequencyMap) {
        Integer total = 0;
        for (Integer value : premadeFrequencyMap.values()) {
            total += value;
        }
        return entropy(null,premadeFrequencyMap,total);
    }

    public double entropy(List<String> values, HashMap<String,Integer> premadeFrequencyMap, Integer total) {
        HashMap<String, Integer> frequencyMap = premadeFrequencyMap;

        if (premadeFrequencyMap == null) {
            frequencyMap = new HashMap<>();
            for (String value : values) {
                if (frequencyMap.containsKey(value)) {
                    frequencyMap.put(value, frequencyMap.get(value)+1);
                } else {
                    frequencyMap.put(value,1);
                }
            }
            total = values.size();
        }

        double entropy = 0.0;
        for (String value : frequencyMap.keySet()) {
            double proportion = (double) frequencyMap.get(value)/total;
            if (proportion>0) {
                entropy -= proportion * (Math.log(proportion)/Math.log(2));
            }
        }
        return entropy;
    }

    public double informationGain() {
        return -1.0;
    }
}
