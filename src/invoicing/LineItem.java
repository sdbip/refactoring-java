package invoicing;

public final class LineItem {
    final String title;
    final double amount;

    public LineItem(String title, double amount) {
        this.title = title;
        this.amount = amount;
    }
}
