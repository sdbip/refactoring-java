package rentals;

import java.util.stream.Stream;

class FrequentEnterPointsCalculator {
    private final MovieRepository movieRepository;

    FrequentEnterPointsCalculator(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    int getPoints(Stream<MovieRental> rentals) {
        return rentals.map(this::getPoints).reduce(0, Integer::sum);
    }

    private Integer getPoints(MovieRental rental) {
        final MovieType typeOfMovie = movieRepository.typeOfMovie(rental.getMovieId());
        final int daysRented = rental.getDays();
        return typeOfMovie.getFrequentEnterPoints(daysRented);
    }
}
