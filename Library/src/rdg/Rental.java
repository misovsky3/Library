package rdg;

import main.DBContext;

import java.sql.*;

public class Rental implements Rdg {
    private Integer id;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private boolean isReturned;
    private Integer accountId;
    private Integer copyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCopyId() {
        return copyId;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", isReturned=" + isReturned +
                ", accountId=" + accountId +
                ", copyId=" + copyId +
                '}';
    }

    @Override
    public void insert() throws SQLException {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("INSERT INTO rentals (date_from, date_to, is_returned, account_id, copy_id) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            s.setTimestamp(++i, dateFrom);
            s.setTimestamp(++i, dateTo);
            s.setBoolean(++i,isReturned);
            s.setInt(++i, accountId);
            s.setInt(++i, copyId);
            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                id = r.getInt(1);
            }
        }
    }

    @Override
    public void update() throws SQLException {
        if (id == null)
            throw new IllegalStateException("Id is null");

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("UPDATE rentals SET date_from = ?, date_to = ?, is_returned = ?, account_id = ?, copy_id = ?  WHERE id = ? ")) {
            int i = 0;
            s.setTimestamp(++i, dateFrom);
            s.setTimestamp(++i, dateTo);
            s.setBoolean(++i,isReturned);
            s.setInt(++i, accountId);
            s.setInt(++i, copyId);
            s.setInt(++i, id);
            s.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id is null");
        }

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("DELETE FROM rentals WHERE id = ? ")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }
}
