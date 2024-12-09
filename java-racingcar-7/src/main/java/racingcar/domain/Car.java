package racingcar.domain;

public class Car {
    private final String name;
    private String location;

    private Car(final String name, final String location) {
        this.name = name;
        this.location = location;
    }

    public static Car of(final String name){
        return new Car(name, "");
    }

    public void move(final int RandomValue){
        if(RandomValue >= 4) {
            this.location += "-";
        }
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
