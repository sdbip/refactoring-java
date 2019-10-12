package rentals;

import static rentals.MovieType.*;

public class RentalInfo {

  private final MovieRepository movieRepository;

  public RentalInfo(final MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public String statement(Customer customer) {
    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");
    for (MovieRental r : customer.getRentals()) {
      double thisAmount = getThisAmount(r);

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

  private double getThisAmount(MovieRental r) {
    double thisAmount = 0;

    // determine amount for each movie
    if (movieRepository.typeOfMovie(r.getMovieId()) == regular) {
      thisAmount = 2;
      if (r.getDays() > 2) {
        thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
      }
    }
    if (movieRepository.typeOfMovie(r.getMovieId()) == newRelease) {
      thisAmount = r.getDays() * 3;
    }
    if (movieRepository.typeOfMovie(r.getMovieId()) == forChildren) {
      thisAmount = 1.5;
      if (r.getDays() > 3) {
        thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
      }
    }
    return thisAmount;
  }
}
