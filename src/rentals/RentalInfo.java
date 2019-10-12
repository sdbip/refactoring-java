package rentals;

import java.util.stream.Stream;

public class RentalInfo {

    private final MovieRepository movieRepository;
    private final PriceCalculator priceCalculator;
    private final FrequentEnterPointsCalculator frequentEnterPointsCalculator;

    public RentalInfo(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        priceCalculator = new PriceCalculator(movieRepository);
        frequentEnterPointsCalculator = new FrequentEnterPointsCalculator(movieRepository);
    }

    public String statement(Customer customer) {
        final Stream<ReportFormatter.LineItem> lineItems =
                customer.getRentals().stream().map(r -> new ReportFormatter.LineItem(
                        movieRepository.titleOfMovie(r.getMovieId()),
                        priceCalculator.getPrice(r))
                );
        final double totalAmount = priceCalculator.getTotalPrice(customer.getRentals().stream());
        final int frequentEnterPoints = frequentEnterPointsCalculator.getPoints(customer.getRentals().stream());
        final ReportFormatter reportFormatter = new ReportFormatter(customer, totalAmount, frequentEnterPoints, lineItems::iterator);
        return reportFormatter.statement();
    }
}
