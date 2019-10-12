package rentals;

public enum MovieType {
    regular {
        @Override
        double getPrice(int daysRented) {
            double thisAmount = 2;
            if (daysRented > 2) {
                thisAmount = ((daysRented - 2) * 1.5) + thisAmount;
            }
            return thisAmount;
        }
    }, forChildren, newRelease;

    double getPrice(int daysRented) {
        return 0.0;
    }
}
