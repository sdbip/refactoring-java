package rentals;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static rentals.MovieType.*;

class FrequentRenterPointsCalculatorTests {
    private MovieType typeOfRentedMovie;

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForRegularRentalRegardlessOfTime() {
        typeOfRentedMovie = regular;
        return Stream.of(1, 2, 10).map(this::assertOnePointRewardedForSingleRental);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForChildrensRentalsRegardlessOfTime() {
        typeOfRentedMovie = forChildren;
        return Stream.of(1, 2, 10).map(this::assertOnePointRewardedForSingleRental);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForNewReleasesUpToTwoDays() {
        typeOfRentedMovie = newRelease;
        return Stream.of(1, 2).map(this::assertOnePointRewardedForSingleRental);
    }

    private DynamicTest assertOnePointRewardedForSingleRental(Integer daysRented) {
        final MovieRental rental = new MovieRental(movieWithType(typeOfRentedMovie), daysRented);
        return dynamicTest(Integer.toString(daysRented), () ->
                assertEquals(1, rental.getFrequentRenterPoints()));
    }

    @Test
    void rewardsTwoPointForNewReleasesIfRentedMoreThanTwoDays() {
        final MovieRental rental = new MovieRental(movieWithType(newRelease), 10);
        assertEquals(2, rental.getFrequentRenterPoints());
    }

    private Movie movieWithType(MovieType type) {
        return new Movie("and id", "a " + type + " movie", type);
    }
}
