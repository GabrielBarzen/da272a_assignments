package org.group4;

public class Item {
    private double value;
    private double weight;

    private double ratio;

    public Item(double value, double weight) {
        this.value = value;
        this.weight = weight;
        this.ratio = value/weight;
    }

    public double getValue() {
        return value;
    }

    public double getRatio() {
        return ratio;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("Item(value=%f, weight=%f, ratio=%f)", value, weight, ratio);
    }
}
