package rentals;

import java.util.stream.Stream;

public class PriceCalculator {
    public double getTotalPrice(Stream<MovieRental> rentals) {
        return rentals.map(this::getPrice)
                .reduce(0.0, Double::sum);
    }

    public double getPrice(MovieRental rental) {
        return rental.movie.getRentalPrice(rental.days);
    }
}
