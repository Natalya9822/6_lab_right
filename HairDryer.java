package lab;

import java.util.Objects;

public class HairDryer {
    private String name;
    private String brand;
    private String colour;
    private int price;
    private double rating;

    @Override
    public String toString() {
        return "HairDryer{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", colour='" + colour + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }

    public HairDryer(String name, String brand, String colour, int price, double rating) {
        this.name = name;
        this.brand = brand;
        this.colour = colour;
        this.price = price;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HairDryer)) return false;
        HairDryer hairDryer = (HairDryer) o;
        return getPrice() == hairDryer.getPrice() && Double.compare(hairDryer.getRating(), getRating()) == 0 && Objects.equals(getName(), hairDryer.getName()) && Objects.equals(getBrand(), hairDryer.getBrand()) && Objects.equals(getColour(), hairDryer.getColour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBrand(), getColour(), getPrice(), getRating());
    }

    public String getColour() {
        return colour;
    }

    public int getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
}

