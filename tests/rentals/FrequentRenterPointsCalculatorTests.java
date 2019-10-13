package rentals;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static rentals.MovieType.*;
import static rentals.MovieType.regular;

class FrequentRenterPointsCalculatorTests {
    private Movie rentedMovie;

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForRegularRentalRegardlessOfTime() {
        givenRentedMovieOfType(regular);
        return Stream.of(1, 2, 10).map(this::assertOnePointRewardedForSingleRental);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForChildrensRentalsRegardlessOfTime() {
        givenRentedMovieOfType(forChildren);
        return Stream.of(1, 2, 10).map(this::assertOnePointRewardedForSingleRental);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForNewReleasesUpToTwoDays() {
        givenRentedMovieOfType(newRelease);
        return Stream.of(1, 2).map(this::assertOnePointRewardedForSingleRental);
    }

    private DynamicTest assertOnePointRewardedForSingleRental(Integer daysRented) {
        final MovieRental rental = rentalForDays(daysRented);
        return dynamicTest(Integer.toString(daysRented), () ->
                assertEquals(1, rental.getFrequentRenterPoints()));
    }

    @Test
    void rewardsTwoPointForNewReleasesIfRentedMoreThanTwoDays() {
        givenRentedMovieOfType(newRelease);
        final MovieRental rental = rentalForDays(10);
        assertEquals(2, rental.getFrequentRenterPoints());
    }

    private void givenRentedMovieOfType(MovieType regular) {
        rentedMovie = new Movie("and id", "a " + regular + " movie", regular);
    }

    private MovieRental rentalForDays(Integer daysRented) {
        return new MovieRental(rentedMovie, daysRented);
    }
}
