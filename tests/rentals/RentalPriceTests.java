package rentals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class RentalPriceTests {
    private PriceCalculator calculator;
    private MockRepository mockRepository;

    @BeforeEach
    void setup() {
        mockRepository = new MockRepository();
        calculator = new PriceCalculator(mockRepository);
    }

    @TestFactory
    Stream<DynamicTest> regularRentalPricesHaveNotChanged() {
        final Map<Integer, Double> expectedPricesByDaysOfRental = Map.of(
                1, 2.0,
                2, 2.0,
                3, 3.5,
                4, 5.0,
                10, 14.0);
        return expectedPricesByDaysOfRental.entrySet().stream()
                .map(entry -> {
                    final int days = entry.getKey();
                    return dynamicTest(Integer.toString(days), () -> {
                        mockRepository.types.put("this id", MovieType.regular);

                        final double actualPrice = calculator.getPrice(new MovieRental("this id", days));

                        assertEquals(entry.getValue(), actualPrice);
                    });
                });
    }

    @TestFactory
    Stream<DynamicTest> forChildrenRentalPricesHaveNotChanged() {
        final Map<Integer, Double> expectedPricesByDaysOfRental = Map.of(
                1, 1.5,
                2, 1.5,
                3, 1.5,
                4, 3.0,
                10, 12.0);
        return expectedPricesByDaysOfRental.entrySet().stream()
                .map(entry -> {
                    final int days = entry.getKey();
                    return dynamicTest(Integer.toString(days), () -> {
                        mockRepository.types.put("this id", MovieType.forChildren);

                        final double actualPrice = calculator.getPrice(new MovieRental("this id", days));

                        assertEquals(entry.getValue(), actualPrice);
                    });
                });
    }

    private static class MockRepository implements MovieRepository {
        private HashMap<String, MovieType> types = new HashMap<>();

        @Override
        public MovieType typeOfMovie(String id) {
            return types.get(id);
        }

        @Override
        public String titleOfMovie(String id) {
            return null;
        }
    }
}
