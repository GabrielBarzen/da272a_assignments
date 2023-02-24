package org.group4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final int KNAPSACKS = 3;
    private static final int ITEMS = 30;
    private static final int ITEMVALUE = 50;
    private static final int ITEMWEIGHT = 10;
    private static final int KNAPSACKCAPACITYBOUND = 50;


    public static void main(String[] args) {
        Knapsack[] ksArr = new Knapsack[KNAPSACKS];
        Random rand = new Random();
        for (int i = 0; i < KNAPSACKS; i++) {
            ksArr[i] = new Knapsack(rand.nextDouble(KNAPSACKCAPACITYBOUND-1)+1);
        }


        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < ITEMS; i++) {
            items.add(new Item(rand.nextDouble(ITEMVALUE-1)+1,rand.nextDouble(ITEMWEIGHT-1)+1));
        }

        KnapsackSolver solver = new KnapsackSolver();
        solver.multipleKnapsackGreedy(items, Arrays.asList(ksArr));
        solver.multipleKnapsackNeighbour(new ArrayList<>(Arrays.asList(ksArr)));

    }
}