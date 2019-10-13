package rentals;

import java.util.stream.Stream;

public class FrequentRenterPointsCalculator {
    public int getPoints(Stream<MovieRental2> rentals) {
        return rentals.map(this::getPoints).reduce(0, Integer::sum);
    }

    private int getPoints(MovieRental2 rental) {
        return rental.movie.getFrequentRenterPoints(rental.days);
    }
}
