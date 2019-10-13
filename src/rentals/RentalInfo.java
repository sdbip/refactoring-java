package rentals;

import java.util.stream.Stream;

public class RentalInfo {

    private final MovieRepository movieRepository;
    private final PriceCalculator priceCalculator;
    private final FrequentRenterPointsCalculator frequentRenterPointsCalculator;

    public RentalInfo(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        priceCalculator = new PriceCalculator(movieRepository);
        frequentRenterPointsCalculator = new FrequentRenterPointsCalculator(movieRepository);
    }

    public String statement(Customer customer) {
        final Stream<ReportFormatter.LineItem> lineItems =
                customer.getRentals().stream().map(r -> new ReportFormatter.LineItem(
                        movieRepository.titleOfMovie(r.getMovieId()),
                        priceCalculator.getPrice(r))
                );
        final double totalAmount = priceCalculator.getTotalPrice(customer.getRentals().stream());
        final int frequentRenterPoints = frequentRenterPointsCalculator.getPoints(customer.getRentals().stream());
        final ReportFormatter reportFormatter = new ReportFormatter(customer, totalAmount, frequentRenterPoints, lineItems::iterator);
        return reportFormatter.statement();
    }
}
