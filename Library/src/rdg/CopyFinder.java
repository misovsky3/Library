package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CopyFinder {
    private static final CopyFinder INSTANCE = new CopyFinder();
    public static CopyFinder getINSTANCE() {
        return INSTANCE;
    }
    private CopyFinder(){}


    public Copy findById(int id) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM copies WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Copy rr = new Copy();
                    rr.setId(r.getInt("id"));
                    rr.setInLibrary(r.getBoolean("is_in_library"));
                    rr.setInWarehouse(r.getBoolean("is_in_warehouse"));
                    rr.setCopyState(r.getInt("copy_state"));
                    rr.setLendable(r.getBoolean("is_lendable"));
                    rr.setBookId(r.getInt("book_id"));
                    rr.setCategoryId(r.getInt("category_id"));
                    rr.setWarehouseId(r.getInt("warehouse_id"));

                    if (r.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return rr;
                } else {
                    return null;
                }
            }
        }
    }


    public List<Copy> findAll() throws SQLException {
        List<Copy> res = new ArrayList<>();
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM copies")) {
            try (ResultSet r = s.executeQuery()) {
                while(r.next()) {
                    Copy rr = new Copy();
                    rr.setId(r.getInt("id"));
                    rr.setInLibrary(r.getBoolean("is_in_library"));
                    rr.setInWarehouse(r.getBoolean("is_in_warehouse"));
                    rr.setCopyState(r.getInt("copy_state"));
                    rr.setLendable(r.getBoolean("is_lendable"));
                    rr.setBookId(r.getInt("book_id"));
                    rr.setCategoryId(r.getInt("category_id"));
                    rr.setWarehouseId(r.getInt("warehouse_id"));
                    res.add(rr);
                }

            }
        }
        return res;
    }


    public List<Copy> findCopiesOfBook(int bookId) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM copies WHERE book_id = ?"))
        {
            s.setInt(1, bookId);
            try (ResultSet r = s.executeQuery()) {

                List<Copy> res = new ArrayList<>();

                while (r.next()) {
                    Copy rr = new Copy();

                    rr.setId(r.getInt("id"));
                    rr.setInLibrary(r.getBoolean("is_in_library"));
                    rr.setInWarehouse(r.getBoolean("is_in_warehouse"));
                    rr.setCopyState(r.getInt("copy_state"));
                    rr.setLendable(r.getBoolean("is_lendable"));
                    rr.setBookId(r.getInt("book_id"));
                    rr.setCategoryId(r.getInt("category_id"));
                    rr.setWarehouseId(r.getInt("warehouse_id"));
                    res.add(rr);
                }

                return res;
            }
        }
    }


}
