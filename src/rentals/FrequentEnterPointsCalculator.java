package rentals;

import static rentals.MovieType.newRelease;

class FrequentEnterPointsCalculator {
    private MovieRepository movieRepository;

    FrequentEnterPointsCalculator(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    int getPoints(Iterable<MovieRental> rentals) {
        int frequentEnterPoints = 0;
        for (MovieRental r : rentals) {
            frequentEnterPoints++;
            if (movieRepository.typeOfMovie(r.getMovieId()) == newRelease && r.getDays() > 2) frequentEnterPoints++;
        }
        return frequentEnterPoints;
    }
}
