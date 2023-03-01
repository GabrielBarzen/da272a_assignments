package org.group4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int KNAPSACKS = 30;
    private static final int ITEMS = 300;
    private static final int ITEMVALUE = 500;
    private static final int ITEMWEIGHT = 100;
    private static final int KNAPSACKCAPACITYBOUND = 500;


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

        solver.saveFile();

    }


}