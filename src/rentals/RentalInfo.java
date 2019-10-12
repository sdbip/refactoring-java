package rentals;

public class RentalInfo {

  private final MovieRepository movieRepository;
  private final PriceCalculator priceCalculator;
  private FrequentEnterPointsCalculator frequentEnterPointsCalculator;

  public RentalInfo(final MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
    priceCalculator = new PriceCalculator(movieRepository);
    frequentEnterPointsCalculator = new FrequentEnterPointsCalculator(movieRepository);
  }

  public String statement(Customer customer) {
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      final double thisAmount = priceCalculator.getPrice(r);

      //print figures for this rental
      result.append("\t").append(movieRepository.titleOfMovie(r.getMovieId())).append("\t").append(thisAmount).append("\n");
    }
    // add footer lines
    final double totalAmount = priceCalculator.getTotalPrice(customer.getRentals().stream());
    result.append("Amount owed is ").append(totalAmount).append("\n");
    int frequentEnterPoints = frequentEnterPointsCalculator.getPoints(customer.getRentals().stream());
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }
}
