package rentals;

import static rentals.MovieType.*;

public class RentalInfo {

  private final MovieRepository movieRepository;
  private final PriceCalculator priceCalculator;

  public RentalInfo(final MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
    priceCalculator = new PriceCalculator(movieRepository);
  }

  public String statement(Customer customer) {
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = priceCalculator.getPrice(r);

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if (movieRepository.typeOfMovie(r.getMovieId()) == newRelease && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      result.append("\t").append(movieRepository.titleOfMovie(r.getMovieId())).append("\t").append(thisAmount).append("\n");
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    result.append("Amount owed is ").append(totalAmount).append("\n");
    result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return result.toString();
  }
}
