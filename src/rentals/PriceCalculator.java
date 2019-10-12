package rentals;

import static rentals.MovieType.*;

class PriceCalculator {
    private final MovieRepository repository;

    PriceCalculator(MovieRepository repository) {
        this.repository = repository;
    }

    double getPrice(MovieRental rental) {
        double thisAmount = 0;

        // determine amount for each movie
        if (repository.typeOfMovie(rental.getMovieId()) == regular) {
            thisAmount = regular.getPrice(rental.getDays());
        }
        if (repository.typeOfMovie(rental.getMovieId()) == newRelease) {
            thisAmount = newRelease.getPrice(rental.getDays());
        }
        if (repository.typeOfMovie(rental.getMovieId()) == forChildren) {
            thisAmount = forChildren.getPrice(rental.getDays());
        }
        return thisAmount;
    }
}
