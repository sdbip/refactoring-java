package rentals;

class PriceCalculator {
    private final MovieRepository repository;

    PriceCalculator(MovieRepository repository) {
        this.repository = repository;
    }

    double getPrice(MovieRental rental) {
        final MovieType typeOfMovie = repository.typeOfMovie(rental.getMovieId());
        return typeOfMovie.getPrice(rental.getDays());
    }
}
