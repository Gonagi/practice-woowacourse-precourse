package racingcar.domain;

import java.util.Objects;

public class Car {
    private final String name;
    private String location;

    private Car(final String name, final String location) {
        this.name = name;
        this.location = location;
    }

    public static Car of(final String name) {
        return new Car(name, "");
    }

    public void move(final int RandomValue) {
        if (RandomValue >= 4) {
            this.location += "-";
        }
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
