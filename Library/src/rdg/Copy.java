package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Copy implements Rdg{
    private Integer id;
    private boolean isInLibrary;
    private boolean isInWarehouse;
    private Integer copyState;
    private boolean isLendable;
    private Integer bookId;
    private Integer categoryId;
    private Integer warehouseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isInLibrary() {
        return isInLibrary;
    }

    public void setInLibrary(boolean inLibrary) {
        isInLibrary = inLibrary;
    }

    public boolean isInWarehouse() {
        return isInWarehouse;
    }

    public void setInWarehouse(boolean inWarehouse) {
        isInWarehouse = inWarehouse;
    }

    public boolean isLendable() {
        return isLendable;
    }

    public void setLendable(boolean lendable) {
        isLendable = lendable;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getCopyState() {
        return copyState;
    }

    public void setCopyState(Integer copyState) {
        this.copyState = copyState;
    }




    public boolean isFree() throws SQLException
    {
        int res = 0;
        try (PreparedStatement s = DBContext.getConnection().prepareStatement(
                "SELECT count(id) FROM copies WHERE id = ? " +
                        "AND id NOT IN (SELECT copy_id FROM rentals WHERE is_returned IS FALSE) " +
                        "AND id NOT IN (SELECT copy_id FROM requests WHERE is_rented IS TRUE)"+
                        "AND is_lendable = TRUE "+
                        "AND copies.copy_state > 3 "))  // Copies with state worse than 30% are not lendable

        {
            s.setInt(1, id);
            try (ResultSet r = s.executeQuery())
            {
                r.next();
                res = r.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res > 0;
    }

    @Override
    public void insert() throws SQLException {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("INSERT INTO copies (is_in_library,is_in_warehouse,copy_state,is_lendable,book_id,category_id,warehouse_id) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            int i = 0;
            s.setBoolean(++i,isInLibrary);
            s.setBoolean(++i,isInWarehouse);
            s.setInt(++i, copyState);
            s.setBoolean(++i, isLendable);
            s.setInt(++i, bookId);
            s.setInt(++i, categoryId);
            s.setInt(++i, warehouseId);
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

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("UPDATE copies SET is_in_library = ?, is_in_warehouse = ?, copy_state = ?, is_lendable = ?, book_id = ?, category_id = ?, warehouse_id = ? WHERE id = ?")) {
            int i = 0;

            s.setBoolean(++i,isInLibrary);
            s.setBoolean(++i,isInWarehouse);
            s.setInt(++i, copyState);
            s.setBoolean(++i, isLendable);
            s.setInt(++i, bookId);
            s.setInt(++i, categoryId);
            s.setInt(++i, warehouseId);
            s.setInt(++i, id);
            s.executeUpdate();
        }

    }

    @Override
    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id is null");
        }

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("DELETE FROM copies WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }

    }

    @Override
    public String toString() {
        return "Copy{" +
                "id=" + id +
                ", isInLibrary=" + isInLibrary +
                ", isInWarehouse=" + isInWarehouse +
                ", copyState=" + copyState +
                ", isLendable=" + isLendable +
                ", bookId=" + bookId +
                ", categoryId=" + categoryId +
                ", warehouseId=" + warehouseId +
                '}';
    }
}




