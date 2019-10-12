package rentals;

public class RentalInfo {

  private final MovieRepository movieRepository;
  private final PriceCalculator priceCalculator;

  public RentalInfo(final MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
    priceCalculator = new PriceCalculator(movieRepository);
  }

  public String statement(Customer customer) {
    int frequentEnterPoints = new FrequentEnterPointsCalculator().getPoints(customer.getRentals().stream().map(r -> new MovieRental2(movieRepository.typeOfMovie(r.getMovieId()), r.getDays())));
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      final double thisAmount = priceCalculator.getPrice(r);

      //print figures for this rental
      result.append("\t").append(movieRepository.titleOfMovie(r.getMovieId())).append("\t").append(thisAmount).append("\n");
    }
    // add footer lines
    final double totalAmount = priceCalculator.getTotalPrice(customer.getRentals().stream());
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }
}
