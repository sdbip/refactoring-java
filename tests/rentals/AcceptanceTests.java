package rentals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceTests {
    @Test
    void mainJSWillRun() {
        final List<MovieRental> rentals = Arrays.asList(
                new MovieRental("F001", 3),
                new MovieRental("F002", 1));

        final String result = new RentalInfo().statement(
                new Customer("C. U. Stomer", rentals));

        final String expected = "Rental Record for C. U. Stomer\n" +
                "\tYou've Got Mail\t3.5\n" +
                "\tMatrix\t2.0\nAmount owed is 5.5\n" +
                "You earned 2 frequent points\n";
        assertEquals(expected, result);
    }
}
