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
            int frequentEnterPoints = 1;
            if (movieRepository.typeOfMovie(r.getMovieId()) == newRelease && r.getDays() > 2) frequentEnterPoints++;
            return frequentEnterPoints;
        }).reduce(0, Integer::sum);
    }
}
