package rentals;

import java.util.Objects;

public final class Movie {
    private final String id;
    private final String title;
    private final MovieType type;

    public Movie(String id, String title, MovieType type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    double getRentalPrice(int daysRented) {
        return type.getPrice(daysRented);
    }

    int getFrequentRenterPoints(int daysRented) {
        return type.getFrequentRenterPoints(daysRented);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
