package rdg;


import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Penalty implements Rdg {
    private final double DAMAGE_PENALTY = 2.3;
    private final double DELAY_PENALTY = 0.5;
    private Integer id;
    private Integer delay;
    private Boolean isDamaged;
    private Double amount;
    private Boolean isPaid;
    private Integer accountId;

    public double getDAMAGE_PENALTY() {
        return DAMAGE_PENALTY;
    }

    public double getDELAY_PENALTY() {
        return DELAY_PENALTY;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Boolean getDamaged() {
        return isDamaged;
    }

    public void setDamaged(Boolean damaged) {
        isDamaged = damaged;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }


    @Override
    public String toString() {
        return "Penalty{" +
                "id=" + id +
                ", delay=" + delay +
                ", isDamaged=" + isDamaged +
                ", amount=" + amount +
                ", isPaid=" + isPaid +
                ", accountId=" + accountId +
                '}';
    }

    @Override
    public void insert() throws SQLException {


        try (PreparedStatement s = DBContext.getConnection().prepareStatement("INSERT INTO penalties (delay, is_damaged, amount, is_paid, account_id) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;

            s.setInt(++i, delay);
            s.setBoolean(++i,isDamaged);
            s.setDouble(++i,amount);
            s.setBoolean(++i,isPaid);
            s.setInt(++i,accountId);
            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                id = r.getInt(1);
            }
        }
    }

    public void makePaid() throws SQLException{
        if (id == null)
            throw new IllegalStateException("Id is null");

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("UPDATE penalties SET is_paid = TRUE WHERE id = ? ")) {
            int i = 0;
            s.setInt(++i, id);
            s.executeUpdate();
        }


    }


    @Override
    public void update() throws SQLException {
        if (id == null)
            throw new IllegalStateException("Id is null");

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("UPDATE penalties SET delay = ?, is_damaged = ?, amount = ?, is_paid = ?, account_id = ?  WHERE id = ? ")) {
            int i = 0;
            s.setInt(++i, delay);
            s.setBoolean(++i,isDamaged);
            s.setDouble(++i,amount);
            s.setBoolean(++i,isPaid);
            s.setInt(++i,accountId);
            s.setInt(++i, id);
            s.executeUpdate();
        }
    }

    @Override
    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id is null");
        }

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("DELETE FROM penalties WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }
}