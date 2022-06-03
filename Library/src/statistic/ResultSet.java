package statistic;

public class ResultSet {
    private int penaltiesDifference;
    private int dayDelay;
    private double amountDifference;

    @Override
    public String toString() {
        return "penaltiesDifference=" + penaltiesDifference +
                ", dayDelay=" + dayDelay +
                ", amountDifference=" + amountDifference +
                '\n';
    }

    public int getPenaltiesDifference() {
        return penaltiesDifference;
    }

    public void setPenaltiesDifference(int penaltiesDifference) {
        this.penaltiesDifference = penaltiesDifference;
    }

    public int getDayDelay() {
        return dayDelay;
    }

    public void setDayDelay(int dayDelay) {
        this.dayDelay = dayDelay;
    }

    public double getAmountDifference() {
        return amountDifference;
    }

    public void setAmountDifference(double amountDifference) {
        this.amountDifference = amountDifference;
    }
}
