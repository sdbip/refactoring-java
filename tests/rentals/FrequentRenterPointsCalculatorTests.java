package rentals;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static rentals.MovieType.*;

class FrequentRenterPointsCalculatorTests {

    private FrequentRenterPointsCalculator calculator = new FrequentRenterPointsCalculator();
    private MovieType typeOfRentedMovies;

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForRegularRentalRegardlessOfTime() {
        typeOfRentedMovies = regular;
        return Stream.of(1, 2, 10).map(this::assertOnePointRewardedForSingleRental);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForChildrensRentalsRegardlessOfTime() {
        typeOfRentedMovies = forChildren;
        return Stream.of(1, 2, 10).map(this::assertOnePointRewardedForSingleRental);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForNewReleasesUpToTwoDays() {
        typeOfRentedMovies = newRelease;
        return Stream.of(1, 2).map(this::assertOnePointRewardedForSingleRental);
    }

    private DynamicTest assertOnePointRewardedForSingleRental(Integer daysRented) {
        final Stream<MovieRental2> rentals = Stream.of(new MovieRental2(movieWithType(typeOfRentedMovies), daysRented));
        return dynamicTest(Integer.toString(daysRented), () ->
                assertEquals(1, calculator.getPoints(rentals)));
    }

    @Test
    void rewardsTwoPointForNewReleasesIfRentedMoreThanTwoDays() {
        final Stream<MovieRental2> rentals = Stream.of(new MovieRental2(movieWithType(newRelease), 10));
        assertEquals(2, calculator.getPoints(rentals));
    }

    private Movie movieWithType(MovieType type) {
        return new Movie("and id", "a " + type + " movie", type);
    }
}
