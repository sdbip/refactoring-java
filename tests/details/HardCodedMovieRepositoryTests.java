package details;

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
    Stream<DynamicTest> returnsTheCorrectTitleForEachMovie() {
        final Map<String, String> expecteds = Map.of(
                "F001", "You've Got Mail",
                "F002", "Matrix",
                "F003", "Cars",
                "F004", "Fast & Furious X");
        return expecteds.entrySet().stream()
                .map(entry -> {
                    final String movieId = entry.getKey();
                    final String title = entry.getValue();
                    return dynamicTest(movieId, () ->
                            assertEquals(title, hardCodedMovieRepository.getMovie(movieId).getTitle()));
                });
    }
}
