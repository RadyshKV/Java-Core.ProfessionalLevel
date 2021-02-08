package HomeWork1;

import java.util.ArrayList;
import java.util.Arrays;

public class Box <T extends Fruit>{
    ArrayList<T> fruits;

    public Box(T[] fruits) {
        this.fruits = new ArrayList<>();
        this.fruits.addAll(Arrays.asList(fruits));
    }

    public void add(T fruit){
        fruits.add(fruit);
    }

    public T getFruit( int index) {
        return fruits.get(index);
    }

    public void remove( int index){
        fruits.remove(index);
    }

    public int getSize(){
        return fruits.size();
    }

    public float getWeight() {
        return (fruits.size() == 0) ? 0 : fruits.size()*fruits.get(0).getWeight();
    }

    public boolean compare(Box<?> anotherBox){
        return this.getWeight() == anotherBox.getWeight();
    }

    public void shiftingFruits(Box<T> anotherBox, int quantityFruit){
        int quantity = Math.min(quantityFruit, this.getSize());
        for (int i = 0; i < quantity; i++) {
            anotherBox.add(this.getFruit(0));
            this.remove(0);
        }
    }
}
