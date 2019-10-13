package details;

import invoicing.Customer;
import invoicing.ReportFormatter;
import rentals.*;
import rentals.MovieRental;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class RentalInfo {

    private final MovieRepository movieRepository;

    public RentalInfo(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String statement(Customer customer, List<details.MovieRental> rentals) {
        final Stream<ReportFormatter.LineItem> lineItems =
                streamRentals(rentals).map(r -> new ReportFormatter.LineItem(
                        r.getMovieTitle(),
                        r.getPrice())
                );
        final double totalAmount = streamRentals(rentals)
                .map(MovieRental::getPrice)
                .reduce(0.0, Double::sum);
        final int frequentRenterPoints = streamRentals(rentals)
                .map(MovieRental::getFrequentRenterPoints)
                .reduce(0, Integer::sum);
        final ReportFormatter reportFormatter = new ReportFormatter(customer, totalAmount, frequentRenterPoints, lineItems::iterator);
        return reportFormatter.statement();
    }

    private Stream<MovieRental> streamRentals(Collection<details.MovieRental> rentals) {
        return rentals.stream()
                .map(r -> new MovieRental(movieRepository.get(r.movieId), r.days));
    }
}
