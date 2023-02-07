package pacman.entries.pacman;

import dataRecording.DataSaverLoader;
import pacman.controllers.Controller;
import pacman.game.Constants.MOVE;
import pacman.game.Game;

import java.util.*;


/*
 * This is the class you need to modify for your entry. In particular, you need to
 * fill in the getAction() method. Any additional classes you write should either
 * be placed in this package or sub-packages (e.g., game.entries.pacman.mypackage).
 */
public class ID3Man extends Controller<MOVE> {


    public ID3Man() {
        ID3DataTuple[] ID3gameData = DataSaverLoader.ID3LoadPacManData();
        Arrays.stream(ID3gameData).forEach(ID3DataTuple::discretizeAll);

        List<List<String>> examples = new ArrayList<>();

        for (ID3DataTuple id3gameDatum : ID3gameData) {
            examples.add(id3gameDatum.getAttributes());
        }

        tree = id3(examples, ID3gameData[0].getLabels(), "DirectionChosen");

        for (Node value : tree.children.values()) {
            System.out.println("label" + value.label + ", attribute : " + value.attribute);
        }
    }

    Node tree;


    public MOVE getMove(Game game, long timeDue) {

        //Place your game logic here to play the game as Ms Pac-Man

        return null;
    }


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

    public static Node id3(List<List<String>> examples, List<String> attributes, String targetAttribute) {
        // If all examples have the same label, return a leaf node with that label
        boolean sameLabel = true;
        String label = examples.get(0).get(examples.get(0).size() - 1);
        for (List<String> example : examples) {
            if (!example.get(example.size() - 1).equals(label)) {
                sameLabel = false;
                break;
            }
        }
        //Returns DirectionChosen if all DirectionChosen are the same, hence pure class
        if (sameLabel) {
            return new Node(label, null);
        }

        System.out.println(attributes.toString());


        // If there are no attributes left, return a leaf node with the majority label
        if (attributes.size() == 1) {
            System.out.println("kommer du in hit bror?");
            /*
            int count = 0;
            for (List<String> example : examples) {
                if (example.get(example.size() - 1).equals(label)) {
                    count++;
                }
            }
            */
            return new Node(label, "DirectionChosen");
        }

        // Find the attribute with the highest information gain
        String bestAttribute = null;
        double maxGain = Double.NEGATIVE_INFINITY;
        for (String attribute : attributes) {
            System.out.println("attributes array before information gain " + attributes);
            double gain = informationGain(examples, attribute, targetAttribute);
            if (gain > maxGain) {
                maxGain = gain;
                bestAttribute = attribute;
            }
        }

        // Create a new decision tree node with the best attribute
        Node tree = new Node(bestAttribute);

        // Create a new decision tree sub-node for each of the values in the best attribute field
        List<String> newAttributes = new ArrayList<>(attributes);
        newAttributes.remove(bestAttribute);
        for (List<String> example : examples) {
            String value = example.get(attributes.indexOf(bestAttribute));
            List<List<String>> newExamples = new ArrayList<>();
            for (List<String> e : examples) {
                if (e.get(attributes.indexOf(bestAttribute)).equals(value)) {
                    newExamples.add(e);
                }
            }
            tree.children.put(value, id3(newExamples, newAttributes, targetAttribute));
        }

        return tree;
    }
    public static double informationGain(List<List<String>> examples, String attribute, String targetAttribute) {
        double entropy = entropy(examples, targetAttribute);

        Map<String, List<List<String>>> attributeValues = new HashMap<>();
        for (List<String> example : examples) {
            System.out.println("Attribute : " + attribute);
            System.out.println(Arrays.toString(example.toArray()));
            String value = example.get(example.indexOf(attribute));
            if (!attributeValues.containsKey(value)) {
                attributeValues.put(value, new ArrayList<>());
            }
            attributeValues.get(value).add(example);
        }

        double gain = entropy;
        for (List<List<String>> examplesWithValue : attributeValues.values()) {
            double probability = (double) examplesWithValue.size() / examples.size();
            gain -= probability * entropy(examplesWithValue, targetAttribute);
        }

        return gain;
    }

    public static double entropy(List<List<String>> examples, String targetAttribute) {
        Map<String, Integer> labelCounts = new HashMap<>();
        for (List<String> example : examples) {
            //String label = example.get(example.indexOf(targetAttribute));
            String label = example.get(example.size()-1);
            if (!labelCounts.containsKey(label)) {
                labelCounts.put(label, 0);
            }
            labelCounts.put(label, labelCounts.get(label) + 1);
        }

        double entropy = 0;
        for (int count : labelCounts.values()) {
            double probability = (double) count / examples.size();
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }

        return entropy;
    }
}



