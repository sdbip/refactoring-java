package rentals;

public enum MovieType {
    regular {
        private static final int STANDARD_DURATION_DAYS = 2;
        private static final double OVERDRAFT_FEE_PER_DAY = 1.5;
        private static final double BASE_PRICE = 2.0;

        @Override
        double getPrice(int daysRented) {
            return daysRented <= STANDARD_DURATION_DAYS ? BASE_PRICE :
                    BASE_PRICE + ((daysRented - STANDARD_DURATION_DAYS) * OVERDRAFT_FEE_PER_DAY);
        }
    },
    forChildren {
        private static final int STANDARD_DURATION_DAYS = 3;
        private static final double OVERDRAFT_FEE_PER_DAY = 1.5;
        private static final double BASE_PRICE = 1.5;

        @Override
        double getPrice(int daysRented) {
            return daysRented <= STANDARD_DURATION_DAYS ? BASE_PRICE :
                    BASE_PRICE + ((daysRented - STANDARD_DURATION_DAYS) * OVERDRAFT_FEE_PER_DAY);
        }
    },
    newRelease {
        private static final double FEE_PER_DAY = 3.0;

        @Override
        double getPrice(int daysRented) {
            return FEE_PER_DAY * daysRented;
        }

        @Override
        int getFrequentEnterPoints(int daysRented) {
            return daysRented > 2 ? 2 : 1;
        }
    };

    abstract double getPrice(int daysRented);

    int getFrequentEnterPoints(int daysRented) {
        return 1;
    }
}
