package details;

import invoicing.Customer;
import invoicing.StatementGenerator;
import rentals.MovieRental;
import rentals.MovieRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RentalInfo {

    private final MovieRepository movieRepository;
    private final StatementGenerator statementGenerator = new StatementGenerator();

    public RentalInfo(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String statement(Customer customer, List<details.MovieRental> inputRentals) {
        final Collection<MovieRental> rentals = convertRentals(inputRentals);
        return statementGenerator.getRentalStatement(customer, rentals).toString();
    }

    private Collection<MovieRental> convertRentals(Collection<details.MovieRental> rentals) {
        return rentals.stream()
                .map(r -> new MovieRental(movieRepository.get(r.movieId), r.days))
                .collect(Collectors.toUnmodifiableList());
    }
}
