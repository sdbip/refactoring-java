package rentals;

import java.util.stream.Stream;

import static rentals.MovieType.newRelease;

class FrequentEnterPointsCalculator {
    private MovieRepository movieRepository;

    FrequentEnterPointsCalculator(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    int getPoints(Stream<MovieRental> rentals) {
        return rentals.map(r -> {
            final MovieType typeOfMovie = movieRepository.typeOfMovie(r.getMovieId());
            final int daysRented = r.getDays();
            return typeOfMovie == newRelease && daysRented > 2 ? 2 : 1;
        }).reduce(0, Integer::sum);
    }
}
