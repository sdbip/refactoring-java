package rentals;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class RentalPriceTests {
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
                        final double actualPrice = MovieType.regular.getPrice(days);
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
                        final double actualPrice = MovieType.forChildren.getPrice(days);
                        assertEquals(entry.getValue(), actualPrice);
                    });
                });
    }

    @TestFactory
    Stream<DynamicTest> newReleasesRentalPricesHaveNotChanged() {
        final Map<Integer, Double> expectedPricesByDaysOfRental = Map.of(
                1, 3.0,
                2, 6.0,
                10, 30.0);
        return expectedPricesByDaysOfRental.entrySet().stream()
                .map(entry -> {
                    final int days = entry.getKey();
                    return dynamicTest(Integer.toString(days), () -> {
                        final double actualPrice = MovieType.newRelease.getPrice(days);
                        assertEquals(entry.getValue(), actualPrice);
                    });
                });
    }
}
