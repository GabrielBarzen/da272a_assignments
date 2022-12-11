package org.group4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KnapsackSolver {
    public static Knapsack knapsackGreedy(List<Item> items, int capacity) {
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

    public static void multipleKnapsackGreedy(List<Item> items, List<Knapsack> ksArr) {
        items = items.stream()
                .sorted((a, b) -> Double.compare(b.getValue() / b.getWeight(), a.getValue() / a.getWeight()))
                .collect(Collectors.toList());

        ksArr = ksArr.stream()
                .sorted(Comparator.comparingDouble(Knapsack::getCapacity))
                .collect(Collectors.toList());


        List<Item> removedItems = new ArrayList<>();
        System.out.println("items: " + items);

        boolean filled = false;
        noMoreItems:
        while (!filled) {
            boolean allFilled = true;
            for (Knapsack knapsack : ksArr) {
          /*      if (!knapsack.isFeasible()) {
                    Item item = knapsack.removeLast();
                    removedItems.add(item);
                    System.out.println("removed item over capacity " + item);
                    continue;
                }
                allFilled = false;*/
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

        /*boolean done = false;
        do {
            boolean allFesible = true;
            for (Knapsack knapsack : ksArr) {
                if (!knapsack.isFeasible()) {
                    allFesible = false;
                    removedItems.add(knapsack.removeLast());
                }
            }
            if (allFesible) done = true;

        } while (!done);*/

        System.out.println("-------------");
        System.out.println(">>Knapsacks<<");
        for (Knapsack knapsack : ksArr) {
            System.out.println(knapsack);
        }
        System.out.println("-------------");
        System.out.println("Removed items: " + removedItems);
        System.out.println("Remaining items: " + items);
        System.out.println("Sorted Remaining items on weight: " + items.stream().sorted((a, b) -> (int) (a.getWeight() - b.getWeight()))
                .toList());

    }
}
