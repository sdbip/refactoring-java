package details;

import rentals.*;
import rentals.MovieRental;

import java.util.stream.Stream;

public class RentalInfo {

    private final MovieRepository movieRepository;

    public RentalInfo(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String statement(Customer customer) {
        final Stream<ReportFormatter.LineItem> lineItems =
                streamRentals(customer).map(r -> new ReportFormatter.LineItem(
                        r.movie.getTitle(),
                        r.getPrice())
                );
        final double totalAmount = streamRentals(customer)
                .map(MovieRental::getPrice)
                .reduce(0.0, Double::sum);
        final int frequentRenterPoints = streamRentals(customer)
                .map(MovieRental::getFrequentRenterPoints)
                .reduce(0, Integer::sum);
        final ReportFormatter reportFormatter = new ReportFormatter(customer, totalAmount, frequentRenterPoints, lineItems::iterator);
        return reportFormatter.statement();
    }

    private Stream<MovieRental> streamRentals(Customer customer) {
        return customer.rentals.stream()
                    .map(r -> new MovieRental(movieRepository.get(r.movieId), r.days));
    }
}
