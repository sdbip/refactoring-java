package rentals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class HardCodedMovieRepositoryTests {

    private HardCodedMovieRepository hardCodedMovieRepository;

    @BeforeEach
    void setup() {
        hardCodedMovieRepository = new HardCodedMovieRepository();
    }

    @TestFactory
    Stream<DynamicTest> returnsTheCorrectTypeForEachMovie() {
        final Map<String, MovieType> expecteds = Map.of(
                "F001", MovieType.regular,
                "F002", MovieType.regular,
                "F003", MovieType.forChildren,
                "F004", MovieType.newRelease);
        return expecteds.entrySet().stream()
                .map(entry -> {
                    final String movieId = entry.getKey();
                    final MovieType type = entry.getValue();
                    return dynamicTest(movieId, () ->
                            assertEquals(type, hardCodedMovieRepository.typeOfMovie(movieId)));
                });
    }
}
