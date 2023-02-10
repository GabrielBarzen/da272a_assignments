package org.group4;
import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private double capacity;
    private List<Item> items;

    public Knapsack(double capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();

    }

    public void addItem(Item item) {
        items.add(item);

    }

    public double getCapacity() {
        return capacity;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double getValue() {
        return items.stream().mapToDouble(Item::getValue).sum();
    }

    public double getWeight() {
        return items.stream().mapToDouble(Item::getWeight).sum();
    }

    public boolean isFeasible() {
        return getWeight() <= capacity;
    }

    public Item removeLast() {
        return items.remove(items.size()-1);
    }

    public static Knapsack fromItems(List<Item> items, int capacity) {
        Knapsack knapsack = new Knapsack(capacity);
        for (Item item : items) {
            knapsack.addItem(item);
        }
        return knapsack;
    }
    public double utility() {
        return (getValue() / (getCapacity() - getWeight()))/getCapacity() ;
    }

    @Override
    public String toString() {
        return String.format("Knapsack(feasible=%b capacity=%f, remaining capacity=%f, filled=%f, value=%f)", this.isFeasible(),capacity, this.getCapacity()-this.getWeight(), (items.stream().mapToDouble(Item::getWeight).sum()),((items.stream().mapToDouble(Item::getValue).sum())));
        //return String.format("Knapsack(feasible=%b capacity=%f, remaining capacity=%f, filled=%f, items=%s)", this.isFeasible(),capacity, this.getCapacity()-this.getWeight(), (items.stream().mapToDouble(Item::getWeight).sum()), items);
    }
}
