package details;

import rentals.*;
import rentals.MovieRental;

import java.util.stream.Stream;

public class RentalInfo {

    private final MovieRepository movieRepository;
    private final PriceCalculator priceCalculator;
    private final FrequentRenterPointsCalculator frequentRenterPointsCalculator;

    public RentalInfo(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        priceCalculator = new PriceCalculator();
        frequentRenterPointsCalculator = new FrequentRenterPointsCalculator();
    }

    public String statement(Customer customer) {
        final Stream<ReportFormatter.LineItem> lineItems =
                streamRentals(customer).map(r -> new ReportFormatter.LineItem(
                        r.movie.getTitle(),
                        r.getPrice())
                );
        final double totalAmount = priceCalculator.getTotalPrice(streamRentals(customer));
        final int frequentRenterPoints = frequentRenterPointsCalculator.getPoints(streamRentals(customer));
        final ReportFormatter reportFormatter = new ReportFormatter(customer, totalAmount, frequentRenterPoints, lineItems::iterator);
        return reportFormatter.statement();
    }

    private Stream<MovieRental> streamRentals(Customer customer) {
        return customer.rentals.stream()
                    .map(r -> new MovieRental(movieRepository.get(r.movieId), r.days));
    }
}
