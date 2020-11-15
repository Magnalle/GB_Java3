package Task_3;

public class main {
    public static void main(String[] args) {
        Box<Apple> box1 = new Box<>();
        for(int i = 0; i < 5; i++){
            box1.addFruit(new Apple());
        }

        Box<Orange> box2 = new Box<>();
        for(int i = 0; i < 10; i++){
            box2.addFruit(new Orange());
        }

        Box<Apple> box3 = new Box<>();
        for(int i = 0; i < 5; i++){
            box3.addFruit(new Apple());
        }

        System.out.println("Вес 1 коробки: " + box1.getWeight()+", 2 коробки: " + box2.getWeight() + ", 3 коробки: " + box3.getWeight());
        System.out.println("Вес коробки 1 равен весу коробки 2: " + box1.compare(box2));
        System.out.println("Вес коробки 1 равен весу коробки 3: " + box1.compare(box3));
        //box1.addFruitsFromBox(box2); // не компилируется из-за несоответствия типов, можно было конечно через cast сделать runtime ошибку
        box1.addFruitsFromBox(box3);
        System.out.println("Вес 1 коробки: " + box1.getWeight()+", 2 коробки: " + box2.getWeight() + ", 3 коробки: " + box3.getWeight());
    }
}
