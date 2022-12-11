package org.group4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final int KNAPSACKS = 10;
    private static final int ITEMS = 100;
    private static final int ITEMVALUE = 200;
    private static final int ITEMWEIGHT = 200;


    public static void main(String[] args) {
        Knapsack[] ksArr = new Knapsack[KNAPSACKS];
        Random rand = new Random(23242344L);
        for (int i = 0; i < KNAPSACKS; i++) {
            ksArr[i] = new Knapsack(rand.nextDouble(400));
        }


        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < ITEMS; i++) {
            items.add(new Item(rand.nextDouble(ITEMVALUE)+1.0,rand.nextDouble(ITEMWEIGHT)+1.0));
        }

        KnapsackSolver.multipleKnapsackGreedy(items, Arrays.asList(ksArr));
    }
}