package day5;

public class Car {
    private final Engine engine;          // has-a

    public Car(Engine engine) {           // injected (Day 3!)
        if (engine == null) throw new IllegalArgumentException("Engine is required");
        this.engine = engine;
    }

    public void start() {
        engine.start();                   // delegation
    }
}
