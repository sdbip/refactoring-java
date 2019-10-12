package rentals;

import java.util.stream.Stream;

import static rentals.MovieType.newRelease;

class FrequentEnterPointsCalculator {
    int getPoints(Stream<MovieRental2> rentals) {
        return rentals.map(r -> {
            final MovieType typeOfMovie = r.getTypeOfMovie();
            final int daysRented = r.getDaysRented();
            return typeOfMovie == newRelease && daysRented > 2 ? 2 : 1;
        }).reduce(0, Integer::sum);
    }
}
