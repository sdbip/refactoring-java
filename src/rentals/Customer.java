package rentals;

import java.util.List;

public class Customer {
    private final String name;
    private final List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }

    String getName() {
        return name;
    }

    List<MovieRental> getRentals() {
        return rentals;
    }
}
