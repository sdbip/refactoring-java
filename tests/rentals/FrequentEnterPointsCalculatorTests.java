package rentals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class FrequentEnterPointsCalculatorTests {

    private FrequentEnterPointsCalculator calculator;

    @BeforeEach
    void setup() {
        calculator = new FrequentEnterPointsCalculator();
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForRegularRentalRegardlessOfTime() {
        return Stream.of(1, 2, 10).map(days -> assertOnePointRewardedForSingleRental(MovieType.regular, days));
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForChildrensRentalsRegardlessOfTime() {
        return Stream.of(1, 2, 10).map(days -> assertOnePointRewardedForSingleRental(MovieType.forChildren, days));
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForNewReleasesUpToTwoDays() {
        return Stream.of(1, 2).map(days -> assertOnePointRewardedForSingleRental(MovieType.newRelease, days));
    }

    private DynamicTest assertOnePointRewardedForSingleRental(MovieType typeOfMovie, int daysRented) {
        return dynamicTest(Integer.toString(daysRented), () -> {
            final Stream<MovieRental2> rentals = Stream.of(new MovieRental2(typeOfMovie, daysRented));
            assertEquals(1, calculator.getPoints(rentals));
        });
    }

    @Test
    void rewardsTwoPointForNewReleasesIfRentedMoreThanTwoDays() {
        final Stream<MovieRental2> rentals = Stream.of(new MovieRental2(MovieType.newRelease, 10));
        assertEquals(2, calculator.getPoints(rentals));
    }
}
