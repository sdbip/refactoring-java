package rentals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class FrequentEnterPointsCalculatorTests {

    private FrequentEnterPointsCalculator calculator;
    private MockRepository mockRepository;

    @BeforeEach
    void setup() {
        mockRepository = new MockRepository();
        calculator = new FrequentEnterPointsCalculator(mockRepository);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForRegularRentalRegardlessOfTime() {
        mockRepository.type = MovieType.regular;
        return Stream.of(1, 2, 10).map(this::assertOnePointRewardedForSingleRental);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForChildrensRentalsRegardlessOfTime() {
        mockRepository.type = MovieType.forChildren;
        return Stream.of(1, 2, 10).map(this::assertOnePointRewardedForSingleRental);
    }

    @TestFactory
    Stream<DynamicTest> rewardsOnePointForNewReleasesUpToTwoDays() {
        mockRepository.type = MovieType.newRelease;
        return Stream.of(1, 2).map(this::assertOnePointRewardedForSingleRental);
    }

    private DynamicTest assertOnePointRewardedForSingleRental(Integer daysRented) {
        final Stream<MovieRental> rentals = Stream.of(new MovieRental("", daysRented));
        return dynamicTest(Integer.toString(daysRented), () ->
                assertEquals(1, calculator.getPoints(rentals)));
    }

    @Test
    void rewardsTwoPointForNewReleasesIfRentedMoreThanTwoDays() {
        mockRepository.type = MovieType.newRelease;
        final Stream<MovieRental> rentals = Stream.of(new MovieRental("", 10));
        assertEquals(2, calculator.getPoints(rentals));
    }

    private static class MockRepository implements MovieRepository {
        private MovieType type;

        @Override
        public MovieType typeOfMovie(String id) {
            return type;
        }

        @Override
        public String titleOfMovie(String id) {
            return null;
        }
    }
}
