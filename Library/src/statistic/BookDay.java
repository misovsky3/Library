package statistic;

public class BookDay {
    private int bookId;
    private double Days;

    public int getBookId() {
        return bookId;
    }

    @Override
    public String toString() {
        return
                "bookId = " + bookId +
                ": Days = " + Days ;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public double getDays() {
        return Days;
    }

    public void setDay(double days) {
        Days = days;
    }
}
