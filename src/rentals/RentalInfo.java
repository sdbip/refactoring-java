package rentals;

import java.util.stream.Stream;

public class RentalInfo {

  private final MovieRepository movieRepository;
  private final PriceCalculator priceCalculator;

  public RentalInfo(final MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
    priceCalculator = new PriceCalculator();
  }

  public String statement(Customer customer) {
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      //print figures for this rental
      final double thisAmount = priceCalculator.getPrice(movieRepository.typeOfMovie(r.getMovieId()), r.getDays());
      result.append("\t").append(movieRepository.titleOfMovie(r.getMovieId())).append("\t").append(thisAmount).append("\n");
    }

    // add footer lines
    final double totalAmount = priceCalculator.getTotalPrice(getMovieRental2s(customer));
    result.append("Amount owed is ").append(totalAmount).append("\n");

    int frequentEnterPoints = new FrequentEnterPointsCalculator().getPoints(getMovieRental2s(customer));
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }

  private Stream<MovieRental2> getMovieRental2s(Customer customer) {
    return customer.getRentals().stream()
            .map(r -> new MovieRental2(movieRepository.typeOfMovie(r.getMovieId()), r.getDays()));
  }
}
