package rentals;

import java.util.stream.Stream;

public class PriceCalculator {
    public double getTotalPrice(Stream<MovieRental2> rentals) {
        return rentals.map(this::getPrice)
                .reduce(0.0, Double::sum);
    }

    public double getPrice(MovieRental2 rental) {
        return rental.movie.getRentalPrice(rental.days);
    }
}
