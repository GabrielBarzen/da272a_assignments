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

        List<List<String>> examples = new ArrayList<>();

        for (ID3DataTuple id3gameDatum : ID3gameData) {
            examples.add(id3gameDatum.getAttributes());
        }

        List<String> target = new ArrayList<>();
        for (List<String> example : examples) {
            target.add(example.get(example.size()-1));
        }
        System.out.println("Entropy : " + calculateEntropy(target));


        //tree = id3(examples, ID3gameData[0].getLabels(), "DirectionChosen");

    }

    private Node id3(List<List<String>> examples, List<String> labels, String targetAttribute) {
        return null;
    }

    public static double calculateEntropy(List<String> strings, List<String> classLabels) {
        // Create a HashMap to store the frequency of each string
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        int total = 0;

        // Calculate the frequency of each string
        for (String str : strings) {
            if (frequencyMap.containsKey(str)) {
                frequencyMap.put(str, frequencyMap.get(str) + 1);
            } else {
                frequencyMap.put(str, 1);
            }
            total++;
        }
        
        // Calculate the entropy based on the frequency of each string
        double entropy = 0e-11;
        for (String outcome : frequencyMap.keySet()) {
            double proportion = (double) frequencyMap.get(outcome) / total;
            if (proportion > 0) {
                entropy += proportion * (Math.log(proportion) / Math.log(2));
            }
        }
        return -(entropy);

    }

    public static double calculateEntropy2(List<List<String>> strings, List<String> classLabels) {
        // Create a HashMap to store the frequency of each string
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        int total = 0;

        // Calculate the frequency of each string
        for (String str : strings) {
            if (frequencyMap.containsKey(str)) {
                frequencyMap.put(str, frequencyMap.get(str) + 1);
            } else {
                frequencyMap.put(str, 1);
            }
            total++;
        }

        // Calculate the entropy based on the frequency of each string
        double entropy = 0e-11;
        for (String outcome : frequencyMap.keySet()) {
            double proportion = (double) frequencyMap.get(outcome) / total;
            if (proportion > 0) {
                entropy += proportion * (Math.log(proportion) / Math.log(2));
            }
        }
        return -(entropy);

    }



    //public double entropy(int total, int[] outcomes) {
    //    System.out.println("outcomes        : " + Arrays.toString(outcomes));
    //    System.out.println("total           : " + total);
    //    System.out.println("outcomes length : " + outcomes.length);
//
//
    //}

    public double informationGain() {
        return -1;
    }


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

