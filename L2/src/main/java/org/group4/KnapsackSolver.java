package org.group4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class KnapsackSolver {

    public static List<Knapsack> knapsacksListGreedy;
    public static List<Knapsack> knapsacksListNeighbour;
    public  Knapsack knapsackGreedy(List<Item> items, int capacity) {
        // Sort the items in decreasing order of their value-to-weight ratio
        items = items.stream()
                .sorted((a, b) -> (int) ((b.getValue() / b.getWeight()) - (a.getValue() / a.getWeight())))
                .collect(Collectors.toList());



        // Create a knapsack from the items and add items to it until it is no longer feasible to do so
        Knapsack knapsack = Knapsack.fromItems(items, capacity);
        for (Item item : items) {
            if (!knapsack.isFeasible()) {
                knapsack.removeItem(item);
            }
        }
        return knapsack;
    }


    List<Item> remainingItems = null;
    List<Item> removedItems = null;

    public  List<Knapsack> multipleKnapsackGreedy(List<Item> items, List<Knapsack> ksArr) {
        items = items.stream()
                .sorted((a, b) -> Double.compare(b.getValue() / b.getWeight(), a.getValue() / a.getWeight()))
                .collect(Collectors.toList());

        ksArr = ksArr.stream()
                .sorted(Comparator.comparingDouble(Knapsack::getCapacity))
                .collect(Collectors.toList());



        removedItems = new ArrayList<>();
        System.out.println("items: " + items);

        boolean filled = false;
        noMoreItems:
        while (!filled) {
            boolean allFilled = true;
            for (Knapsack knapsack : ksArr) {
                if(!items.isEmpty()) {
                    for (int i = 0; i < items.size(); i++) {
                        if (knapsack.getWeight() + items.get(i).getWeight() < knapsack.getCapacity()) {
                            knapsack.addItem(items.get(i));
                            removedItems.add(items.get(i));
                            items.remove(i);
                            allFilled = false;
                            break;
                        }
                    }
                } else {
                    System.out.println("No more items");
                    break noMoreItems;
                }
            }
            if (allFilled) filled = true;
        }



        double totalutil = 0;
        System.out.println("-------------");
        System.out.println(">>Knapsacks<<");
        for (Knapsack knapsack : ksArr) {
            System.out.println(knapsack);
            totalutil += knapsack.utility();
        }
        System.out.println("-------------");
        System.out.println("Total utility: " + totalutil);
        System.out.println("Removed items: " + removedItems);
        System.out.println("Remaining items: " + items);
        System.out.println("Sorted Remaining items on weight: " + items.stream().sorted((a, b) -> (int) (a.getWeight() - b.getWeight()))
                .toList());

        remainingItems = items;

        knapsacksListGreedy = ksArr;



        return ksArr;
    }

    public void saveFile() {
        BufferedWriter bw = null;
        File file = new File("C:\\Users\\Workstation\\Desktop\\Plugg\\AI\\da272a_assignments\\L2\\src\\main\\data\\outData.txt");

        double totalutil = 0;

        for (Knapsack knapsack : knapsacksListGreedy) {
            System.out.println(knapsack);
            totalutil += knapsack.utility();
        }

        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Knapsack knapsack : knapsacksListGreedy) {
                bw.write("Knapsack: " + knapsacksListGreedy.indexOf(knapsack) + "; ");
                bw.write("Utility: " + knapsack.utility());
                bw.write("\n");
                for (Item item : knapsack) {
                    bw.write(item + "; ");
                }
                bw.write("\n");
            }

            bw.write("Total util: " + totalutil);
            bw.write("\n");

            for (Knapsack knapsack : knapsacksListNeighbour) {
                bw.write("Knapsack: " + knapsacksListNeighbour.indexOf(knapsack) + "; ");
                bw.write("Utility: " + knapsack.utility());
                bw.write("\n");
                for (Item item : knapsack) {
                    bw.write(item + "; ");
                }
                bw.write("\n");
            }

            bw.write("Total util: " + totalutil);
            bw.write("\n");

            bw.flush();

        } catch (IOException e) {
            System.out.println("shit went wrong my dude");
        }
    }


    public void multipleKnapsackNeighbour(ArrayList<Knapsack> ksArr) {
        Random rand = new Random();
        int iter = 0;
        while (iter < 1000000) {
            int currKnapsack = rand.nextInt(ksArr.size());
            Knapsack prevKnapsack = ksArr.get(currKnapsack);
            Knapsack knapsack = (Knapsack) ksArr.get(currKnapsack).clone();
            double preUtility = knapsack.utility();
            Item removedItem = knapsack.removeItem(rand.nextInt(knapsack.size()));
            List<Item> saveRemainingItem = new ArrayList<>();
            for (Item remainingItem : remainingItems) {
                knapsack.addItem(remainingItem);
                saveRemainingItem.add(remainingItem);
                if(!knapsack.isFeasible()) {
                    knapsack.removeItem(remainingItem);
                    saveRemainingItem.remove(remainingItem);
                }
            }


            if(knapsack.utility() > preUtility) {
                ksArr.remove(prevKnapsack);
                ksArr.add(knapsack);
                for (Item item : saveRemainingItem) {
                    remainingItems.remove(item);
                }
                remainingItems.add(removedItem);
            }

            iter++;
        }

        knapsacksListNeighbour = ksArr;

        double totalutil = 0;
        System.out.println("-------------");
        System.out.println(">>Knapsacks<<");
        for (Knapsack knapsack : ksArr) {
            System.out.println(knapsack);
            totalutil += knapsack.utility();
        }
        System.out.println("-------------");
        System.out.println("Total utility: " + totalutil);
        System.out.println("Remaining items: " + remainingItems);



        //Ta bort slumpmässigt item från slumpmässig knapsack
        //Shuffla tills item får plats
        //kolla utility
        //spara om ny utility > gammal utility
        


    }

    public List<Item> getRemainingItems() {
        return remainingItems;
    }

    public void setRemainingItems(List<Item> remainingItems) {
        this.remainingItems = remainingItems;
    }

    public List<Item> getRemovedItems() {
        return removedItems;
    }

    public void setRemovedItems(List<Item> removedItems) {
        this.removedItems = removedItems;
    }

}
