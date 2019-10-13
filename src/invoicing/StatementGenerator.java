package invoicing;

import rentals.MovieRental;

import java.util.Collection;
import java.util.stream.Stream;

public class StatementGenerator {
    public RentalStatement getRentalStatement(Customer customer, Collection<MovieRental> rentals) {
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
        return new RentalStatement(customer, totalAmount, frequentRenterPoints, lineItems::iterator);
    }
}
