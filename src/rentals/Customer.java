package rentals;

import java.util.List;

public class Customer {
    final String name;
    final List<MovieRental> rentals;

    public Customer(String name, List<MovieRental> rentals) {
        this.name = name;
        this.rentals = rentals;
    }
}
