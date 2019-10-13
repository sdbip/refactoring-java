package rentals;

import java.util.stream.Stream;

public class PriceCalculator {
    public double getTotalPrice(Stream<MovieRental> rentals) {
        return rentals.map(MovieRental::getPrice)
                .reduce(0.0, Double::sum);
    }

}
