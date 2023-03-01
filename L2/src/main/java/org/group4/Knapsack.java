package org.group4;
import java.util.ArrayList;
import java.util.List;

public class Knapsack extends ArrayList<Item> implements Cloneable {
    private double capacity;

    public Knapsack(double capacity) {
        this.capacity = capacity;
    }

    public void addItem(Item item) {
        this.add(item);

    }

    public double getCapacity() {
        return capacity;
    }

    public void removeItem(Item item) {
        this.remove(item);
    }

    public Item removeItem(int index) {
        return this.remove(index);
    }

    public double getValue() {
        return this.stream().mapToDouble(Item::getValue).sum();
    }

    public double getWeight() {
        return this.stream().mapToDouble(Item::getWeight).sum();
    }

    public boolean isFeasible() {
        return getWeight() <= capacity;
    }

    public Item removeLast() {
        return this.remove(this.size()-1);
    }

    public static Knapsack fromItems(List<Item> items, int capacity) {
        Knapsack knapsack = new Knapsack(capacity);
        for (Item item : items) {
            knapsack.addItem(item);
        }
        return knapsack;
    }
    public double utility() {
        return getValue() / getCapacity();
    }

    @Override
    public Object clone() {
        Knapsack cloned = new Knapsack(this.capacity);
        for (Item item : this) {
            cloned.add( (Item) item.clone());
        }
        return cloned;
    }

    @Override
    public String toString() {
        return String.format("Knapsack(feasible=%b capacity=%f, remaining capacity=%f, filled=%f, value=%f, utility=%f, num items=%d)", this.isFeasible(),capacity, this.getCapacity()-this.getWeight(), (this.stream().mapToDouble(Item::getWeight).sum()),((this.stream().mapToDouble(Item::getValue).sum())),utility(),this.size());
        //return String.format("Knapsack(feasible=%b capacity=%f, remaining capacity=%f, filled=%f, items=%s)", this.isFeasible(),capacity, this.getCapacity()-this.getWeight(), (items.stream().mapToDouble(Item::getWeight).sum()), items);
    }
}
