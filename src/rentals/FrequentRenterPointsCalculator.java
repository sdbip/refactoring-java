package rentals;

import java.util.stream.Stream;

public class FrequentRenterPointsCalculator {
    public int getPoints(Stream<MovieRental> rentals) {
        return rentals.map(MovieRental::getFrequentRenterPoints).reduce(0, Integer::sum);
    }

}
