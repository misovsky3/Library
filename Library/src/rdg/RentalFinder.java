package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RentalFinder {
    private static final RentalFinder INSTANCE = new RentalFinder();

    public static RentalFinder getINSTANCE() {
        return INSTANCE;
    }

    private RentalFinder() {

    }

    public List<Rental> getCurrentRentals(int rId) throws SQLException {
        List<Rental> res = new ArrayList();
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM rentals WHERE account_id = ?")) {
            s.setInt(1, rId);
            s.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            try (ResultSet r = s.executeQuery()) {
                while (r.next()) {
                    Rental req = new Rental();
                    req.setId(r.getInt("id"));
                    req.setDateFrom(r.getTimestamp("date_from"));
                    req.setDateTo(r.getTimestamp("date_to"));
                    req.setCopyId(r.getInt("copy_id"));
                    req.setAccountId(r.getInt("account_id"));
                    req.setReturned(r.getBoolean("is_returned"));
                    res.add(req);
                }
            }

        }
        return res;
    }

    public Rental findById(int id) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM rentals WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Rental req = new Rental();
                    req.setId(r.getInt("id"));
                    req.setDateFrom(r.getTimestamp("date_from"));
                    req.setDateTo(r.getTimestamp("date_to"));
                    req.setCopyId(r.getInt("copy_id"));
                    req.setAccountId(r.getInt("account_id"));
                    req.setReturned(r.getBoolean("is_returned"));

                    if (r.next()) {
                        throw new RuntimeException("More than one row was returned");
                    }

                    return req;
                } else {
                    return null;
                }
            }
        }
    }

    public Rental findByCopyId(int cId) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM rentals WHERE copy_id = ?")) {
            s.setInt(1, cId);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Rental req = new Rental();
                    req.setId(r.getInt("id"));
                    req.setDateFrom(r.getTimestamp("date_from"));
                    req.setDateTo(r.getTimestamp("date_to"));
                    req.setCopyId(r.getInt("copy_id"));
                    req.setAccountId(r.getInt("account_id"));
                    req.setReturned(r.getBoolean("is_returned"));

                    if (r.next()) {
                        ;
                    }

                    return req;
                } else {
                    return null;
                }
            }
        }
    }

}
