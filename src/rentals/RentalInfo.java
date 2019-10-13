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
                customer.rentals.stream().map(r -> new ReportFormatter.LineItem(
                        movieRepository.get(r.movieId).getTitle(),
                        priceCalculator.getPrice(r))
                );
        final double totalAmount = priceCalculator.getTotalPrice(customer.rentals.stream());
        final int frequentRenterPoints = frequentRenterPointsCalculator.getPoints(customer.rentals.stream());
        final ReportFormatter reportFormatter = new ReportFormatter(customer, totalAmount, frequentRenterPoints, lineItems::iterator);
        return reportFormatter.statement();
    }
}
