package Task_3;

import java.util.ArrayList;

public class Box <T extends Fruit>{


    ArrayList<T> fruits = new ArrayList<>();

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public void addFruit(T fruit){
        fruits.add(fruit);
    }

    public void addFruits(ArrayList<T> newFruits){
        newFruits.forEach(this::addFruit);
    }

    public void dropFruits(){
        fruits.clear();
    }

    public Double getWeight(){
        Double sum = 0.0;
        for(T fruit : fruits){
            sum += fruit.getWeight();
        };
        return sum;
    }

    public Boolean compare(Box<?> another){
        return this.getWeight().equals(another.getWeight());
    }

    public void addFruitsFromBox(Box<T> another){
        this.addFruits(another.getFruits());
        another.dropFruits();
    }
}
