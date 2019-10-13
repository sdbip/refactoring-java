import details.HardCodedMovieRepository;
import details.Customer;
import details.MovieRental;
import details.RentalInfo;

import java.util.Arrays;
import java.util.List;

class Main {
    public static void main(String[] args) {
        final String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

        final Customer customer = new Customer("C. U. Stomer");
        final List<MovieRental> rentals = Arrays.asList(new MovieRental("F001", 3), new MovieRental("F002", 1));
        final RentalInfo rentalInfo = new RentalInfo(new HardCodedMovieRepository());
        final String result = rentalInfo.statement(customer, rentals);

        if (!result.equals(expected)) {
            throw new AssertionError("Expected: " + System.lineSeparator() + expected + System.lineSeparator() + System.lineSeparator() + "Got: " + System.lineSeparator() + result);
        }

        System.out.println("Success");
    }
}
