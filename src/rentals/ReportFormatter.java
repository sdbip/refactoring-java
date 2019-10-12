package rentals;

/**
 * Warning not thread-safe.
 */
class ReportFormatter {
    private StringBuilder resultBuilder;
    private final Customer customer;
    private final double totalAmount;
    private final int frequentEnterPoints;
    private final Iterable<LineItem> lineItems;

    ReportFormatter(Customer customer, double totalAmount, int frequentEnterPoints, Iterable<LineItem> lineItems) {
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.frequentEnterPoints = frequentEnterPoints;
        this.lineItems = lineItems;
    }

    String statement() {
        resultBuilder = new StringBuilder("Rental Record for " + customer.getName() + "\n");
        for (LineItem lineItem : lineItems) {
            addFiguresForRental(lineItem);
        }
        addFooterLines(totalAmount, frequentEnterPoints);
        return resultBuilder.toString();
    }

    private void addFiguresForRental(LineItem lineItem) {
        resultBuilder.append("\t")
                .append(lineItem.title)
                .append("\t")
                .append(lineItem.amount)
                .append("\n");
    }

    private void addFooterLines(double totalAmount, int frequentEnterPoints) {
        appendAmountOwed(totalAmount);
        appendFrequentEnterPoints(frequentEnterPoints);
    }

    private void appendAmountOwed(double totalAmount) {
        resultBuilder.append("Amount owed is ")
                .append(totalAmount)
                .append("\n");
    }

    private void appendFrequentEnterPoints(int frequentEnterPoints) {
        resultBuilder.append("You earned ")
                .append(frequentEnterPoints)
                .append(" frequent points\n");
    }

    static class LineItem {
        final String title;
        final double amount;

        LineItem(String title, double amount) {
            this.title = title;
            this.amount = amount;
        }
    }
}
