package rentals;

public enum MovieType {
    regular {
        private static final int STANDARD_DURATION_DAYS = 2;
        private static final double OVERDRAFT_FEE_PAER_DAY = 1.5;
        private static final double BASE_PRICE = 2.0;

        @Override
        double getPrice(int daysRented) {
            return daysRented <= STANDARD_DURATION_DAYS ? BASE_PRICE :
                    BASE_PRICE + ((daysRented - STANDARD_DURATION_DAYS) * OVERDRAFT_FEE_PAER_DAY);
        }
    },
    forChildren,
    newRelease;


    double getPrice(int daysRented) {
        return 0.0;
    }
}
