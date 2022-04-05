package ch02;

public class Apple {
    private Color color;
    private int weight;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(int weight, Color color) {
        this.color = color;
        this.weight = weight;
    }
}
