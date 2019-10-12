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
            thisAmount = rental.getDays() * 3;
        }
        if (repository.typeOfMovie(rental.getMovieId()) == forChildren) {
            thisAmount = 1.5;
            if (rental.getDays() > 3) {
                thisAmount = ((rental.getDays() - 3) * 1.5) + thisAmount;
            }
        }
        return thisAmount;
    }
}
