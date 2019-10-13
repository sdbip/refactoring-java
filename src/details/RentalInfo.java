package details;

import invoicing.Customer;
import invoicing.LineItem;
import invoicing.RentalStatement;
import rentals.MovieRental;
import rentals.MovieRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RentalInfo {

    private final MovieRepository movieRepository;

    public RentalInfo(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String statement(Customer customer, List<details.MovieRental> inputRentals) {
        final Collection<MovieRental> rentals = convertRentals(inputRentals);
        final Stream<LineItem> lineItems = rentals.stream()
                .map(r -> new LineItem(
                        r.getMovieTitle(),
                        r.getPrice())
                );
        final double totalAmount = rentals.stream()
                .map(MovieRental::getPrice)
                .reduce(0.0, Double::sum);
        final int frequentRenterPoints = rentals.stream()
                .map(MovieRental::getFrequentRenterPoints)
                .reduce(0, Integer::sum);
        final RentalStatement rentalStatement = new RentalStatement(customer, totalAmount, frequentRenterPoints, lineItems::iterator);
        return rentalStatement.toString();
    }

    private Collection<MovieRental> convertRentals(Collection<details.MovieRental> rentals) {
        return rentals.stream()
                .map(r -> new MovieRental(movieRepository.get(r.movieId), r.days))
                .collect(Collectors.toUnmodifiableList());
    }
}
