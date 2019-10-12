package rentals;

class PriceCalculator {
    private final MovieRepository repository;

    PriceCalculator(MovieRepository repository) {
        this.repository = repository;
    }

    double getTotalPrice(Iterable<MovieRental> rentals) {
        double totalAmount = 0;
        for (MovieRental r : rentals) {
            double thisAmount = getPrice(r);
            totalAmount = totalAmount + thisAmount;
        }
        return totalAmount;
    }

    double getPrice(MovieRental rental) {
        final MovieType typeOfMovie = repository.typeOfMovie(rental.getMovieId());
        return typeOfMovie.getPrice(rental.getDays());
    }
}
