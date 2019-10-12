package rentals;

import static rentals.MovieType.*;

class PriceCalculator {
    private final MovieRepository repository;

    PriceCalculator(MovieRepository repository) {
        this.repository = repository;
    }

    double getPrice(MovieRental r) {
        double thisAmount = 0;

        // determine amount for each movie
        if (repository.typeOfMovie(r.getMovieId()) == regular) {
            thisAmount = 2;
            if (r.getDays() > 2) {
                thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
            }
        }
        if (repository.typeOfMovie(r.getMovieId()) == newRelease) {
            thisAmount = r.getDays() * 3;
        }
        if (repository.typeOfMovie(r.getMovieId()) == forChildren) {
            thisAmount = 1.5;
            if (r.getDays() > 3) {
                thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
            }
        }
        return thisAmount;
    }
}
