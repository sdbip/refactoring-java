package rentals;

import java.util.stream.Stream;

class PriceCalculator {
    double getTotalPrice(Stream<MovieRental2> rentals) {
        return rentals.map(this::getPrice)
                .reduce(0.0, Double::sum);
    }

    private double getPrice(MovieRental2 rental) {
        return rental.getTypeOfMovie().getPrice(rental.getDaysRented());
    }

    double getPrice(MovieType typeOfMovie, int daysRented){
        return typeOfMovie.getPrice(daysRented);
    }
}
