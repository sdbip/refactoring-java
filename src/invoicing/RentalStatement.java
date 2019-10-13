package invoicing;

/**
 * Warning not thread-safe.
 */
public class RentalStatement {
    private StringBuilder resultBuilder;
    private final Customer customer;
    private final double totalAmount;
    private final int frequentRenterPoints;
    private final Iterable<LineItem> lineItems;

    public RentalStatement(Customer customer, double totalAmount, int frequentRenterPoints, Iterable<LineItem> lineItems) {
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.frequentRenterPoints = frequentRenterPoints;
        this.lineItems = lineItems;
    }

    @Override
    public String toString() {
        resultBuilder = new StringBuilder("Rental Record for " + customer.name + "\n");
        for (LineItem lineItem : lineItems) {
            addFiguresForRental(lineItem);
        }
        addFooterLines(totalAmount, frequentRenterPoints);
        return resultBuilder.toString();
    }

    private void addFiguresForRental(LineItem lineItem) {
        resultBuilder.append("\t")
                .append(lineItem.title)
                .append("\t")
                .append(lineItem.amount)
                .append("\n");
    }

    private void addFooterLines(double totalAmount, int frequentRenterPoints) {
        appendAmountOwed(totalAmount);
        appendFrequentRenterPoints(frequentRenterPoints);
    }

    private void appendAmountOwed(double totalAmount) {
        resultBuilder.append("Amount owed is ")
                .append(totalAmount)
                .append("\n");
    }

    private void appendFrequentRenterPoints(int points) {
        resultBuilder.append("You earned ")
                .append(points)
                .append(" frequent points\n");
    }
}
