package pacman.entries.pacman;
import dataRecording.DataSaverLoader;
import pacman.controllers.Controller;
import pacman.game.Constants.*;
import pacman.game.Game;

import java.util.*;

public class ID3Man2ElectricBoogaloo extends Controller<MOVE> {

    @Override
    public MOVE getMove(Game game, long timeDue) {
        //Sök the träd
        return null;
    }

    public static void main(String[] args) {new ID3Man2ElectricBoogaloo();}

    Node tree;

    public ID3Man2ElectricBoogaloo() {
        ID3DataTuple[] ID3gameData = DataSaverLoader.ID3LoadPacManData();
        Arrays.stream(ID3gameData).forEach(ID3DataTuple::discretizeAll);

        List<List<String>> examplesList = new ArrayList<>();
        HashMap<String, List<String>> examples = new HashMap<>();
        List<String> attributes = ID3gameData[0].getLabels();
        List<String> labels = new ArrayList<>();
        labels.add("VERY_LOW");
        labels.add("LOW");
        labels.add("MEDIUM");
        labels.add("HIGH");
        labels.add("VERY_HIGH");



        for (ID3DataTuple id3gameDatum : ID3gameData) {
            examplesList.add(id3gameDatum.getAttributes());
        }

        for (int i = 0; i < attributes.size(); i++) {
            List<String> tempList = new ArrayList<>();
            for (int j = 0; j < examplesList.size(); j++) {
                tempList.add(examplesList.get(j).get(i));
            }
            System.out.println(attributes.get(i));
            examples.put(attributes.get(i), tempList);
        }

        tree = id3(examples, "DirectionChosen");

    }

    private Node id3(HashMap<String, List<String>> examples, String targetAttribute) {
        return null;
    }

    public static double informationGain(HashMap<String, List<String>> examples, String attribute, String targetAttribute) {
        // Create a HashMap to store the frequency of each string
        HashMap<String, Integer> attributeFrequencyMap = new HashMap<>();
        HashMap<String, Integer> classAttributeFrequencyMap = new HashMap<>();
        List<String> attributeList = examples.get(attribute);
        List<String> outcomeList = examples.get(targetAttribute);

        double total = 0.0;

        //Calculate total frequency of class attribute labels
        for (String str : outcomeList) {
            if (classAttributeFrequencyMap.containsKey(str)) {
                classAttributeFrequencyMap.put(str, classAttributeFrequencyMap.get(str) + 1);
            } else {
                classAttributeFrequencyMap.put(str, 1);
            }
        }

        // Calculate the frequency of each label
        for (String str : attributeList) {
            if (attributeFrequencyMap.containsKey(str)) {
                attributeFrequencyMap.put(str, attributeFrequencyMap.get(str) + 1);
            } else {
                attributeFrequencyMap.put(str, 1);
            }
            total++;
        }

        //Calculates complete set entropy
        double setEntropy = actuallyEntropy(classAttributeFrequencyMap, total);


        //Calculates individual entropy for each value (e.g. VERY_LOW,LOW) and saves in Hattribute
        HashMap<String, Double> Hattribute = new HashMap<>();
        for (String s : attributeFrequencyMap.keySet()) {
            HashMap<String, Integer> outcomeToAttributeFreq = new HashMap<>();
            for (int i = 0; i < attributeList.size(); i++) {
                if(s.equals(attributeList.get(i))) {
                    if (outcomeToAttributeFreq.containsKey(outcomeList.get(i))) {
                        outcomeToAttributeFreq.put(outcomeList.get(i), outcomeToAttributeFreq.get(outcomeList.get(i)) + 1);
                    } else {
                        outcomeToAttributeFreq.put(outcomeList.get(i), 1);
                    }
                }
            }
            int tempTot = 0;
            for (Integer value : outcomeToAttributeFreq.values()) {
                tempTot += value;
            }
            Hattribute.put(s, actuallyEntropy(outcomeToAttributeFreq, tempTot));

        }

        //Averages attribute entropy
        double averageEntropyAttribute = 0;
        for (String s : attributeFrequencyMap.keySet()) {
            averageEntropyAttribute += (attributeFrequencyMap.get(s) / total) * Hattribute.get(s);
        }

        //Returns Complete set entropy - average entropy for the attribute, aka information gain
        return setEntropy - averageEntropyAttribute;
    }

    public static double actuallyEntropy(HashMap<String, Integer> frequencyMap, double total) {
        double entropy = 0e-11;
        for (String outcome : frequencyMap.keySet()) {
            double proportion = (double) frequencyMap.get(outcome) / total;
            if (proportion > 0) {
                entropy += proportion * (Math.log(proportion) / Math.log(2));
            }
        }
        return -(entropy);
    }


    public double e(List<String> attributes) {
        double entropy = 0.0;
        int totalElements = 0;

        HashMap<String,Integer> frequencyMap = new HashMap<>();
        for (String str : attributes) {
            if (frequencyMap.containsKey(str)) {
                frequencyMap.put(str, frequencyMap.get(str) + 1);
            } else {
                frequencyMap.put(str, 1);
            }
            totalElements++;
        }

        for (String attribute : attributes) {
            double proportion = (double) frequencyMap.get(attribute) / totalElements;
            if (proportion > 0) {
                entropy += proportion * (Math.log(proportion) / Math.log(2));
            }
        }
        return entropy;
    }

    public double ig() {
        return -1;
    }

    public double informationGain() {
        return -1;
    }

    //# x is examples in training set
    //# y is set of attributes
    //# labels is labeled data
    //# Node is a class which has properties values, childs, and next
    //# root is top node in the decision tree# Declare:
    //x = # Multi dimensional arrays
    //y = # Column names of x
    //labels = # Classification values, for example {0, 1, 0, 1}
    //         # correspond that row 1 is false, row 2 is true, and so on
    //root = ID3(x, y, label, root)# Define:
    //ID3(x, y, label, node)
    //  initialize node as a new node instance
    //  if all rows in x only have single classification c, then:
    //    insert label c into node
    //    return node
    //  if x is empty, then:
    //    insert dominant label in x into node
    //    return node
    //  bestAttr is an attribute with maximum information gain in x
    //  insert attribute bestAttr into node
    //  for vi in values of bestAttr:
    //    // For example, Outlook has three values: Sunny, Overcast, and Rain
    //    insert value vi as branch of node
    //    create viRows with rows that only contains value vi
    //    if viRows is empty, then:
    //      this node branch ended by a leaf with value is dominant label in x
    //    else:
    //      newY = list of attributes y with bestAttr removed
    //      nextNode = next node connected by this branch
    //      nextNode = ID3(viRows, newY, label, nextNode)
    //  return node


    //Sure! Here's a step-by-step guide on how the ID3 algorithm works:
    //
    //    Start with the original set of examples (the "training data")
    //    and the target attribute that you want to predict.
    //
    //    Calculate the entropy of the target attribute to determine its "impurity."
    //    The entropy is a measure of the amount of randomness or uncertainty in the data.
    //
    //    For each feature (attribute) in the training data, calculate the information gain that would result
    //    from splitting the data based on that feature. The information gain is the reduction in entropy that results from splitting the data.
    //
    //    Choose the feature with the highest information gain as the root node of the decision tree.
    //
    //    Split the training data into subsets based on the chosen feature.
    //
    //    For each subset of the training data, repeat the process, starting at step 2.
    //
    //    Continue this process until either all examples in a subset have the same target attribute or there are no more features to split on.
    //
    //    At this point, you should have a complete decision tree that can be used to make predictions for new examples.
    //
    //Note: The ID3 algorithm is prone to overfitting, so it's important to prune the decision tree to prevent this.
    //Pruning involves removing branches of the tree that are not helpful in making accurate predictions.

    //Here are the steps to prune an ID3 decision tree:
    //
    //    Start with the fully grown decision tree obtained from the ID3 algorithm.
    //
    //    Remove the sub-trees that have a low impact on the accuracy of the tree.
    //    This can be done by measuring the accuracy of the tree with and without each sub-tree, and removing the sub-tree that has the smallest impact.
    //
    //    Repeat step 2 for each sub-tree in the tree, until no further improvement can be made.
    //
    //    The pruned decision tree is the final result.
    //
    //Note: There are several methods to determine which sub-tree to prune, including reduced error pruning, minimum description length pruning, and cost complexity pruning.
    //The choice of method will depend on the specific application and the trade-off between accuracy and complexity that is desired.


}

