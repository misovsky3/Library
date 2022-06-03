package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RequestFinder {
    private static final RequestFinder INSTANCE = new RequestFinder();

    public static RequestFinder getINSTANCE() {
        return INSTANCE;
    }

    private RequestFinder() {
    }


    public List<Request> getCurrentRequests(int rId) throws SQLException {
        List<Request> res = new ArrayList();
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM requests WHERE account_id = ? and is_rented IS false AND date_to >= ?")) {
            s.setInt(1, rId);
            s.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            try (ResultSet r = s.executeQuery()) {
                while (r.next()) {
                    Request req = new Request();
                    req.setId(r.getInt("id"));
                    req.setDateFrom(r.getTimestamp("date_from"));
                    req.setDateTo(r.getTimestamp("date_to"));
                    req.setCopyId(r.getInt("copy_id"));
                    req.setAccountId(r.getInt("account_id"));
                    req.setRented(r.getBoolean("is_rented"));
                    res.add(req);
                }
            }

        }
        return res;
    }

    public List<Request> getAllRequests() throws SQLException {
        List<Request> res = new ArrayList();
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM requests WHERE is_rented IS false AND date_to >= ?")) {
            s.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            try (ResultSet r = s.executeQuery()) {
                while (r.next()) {
                    Request req = new Request();
                    req.setId(r.getInt("id"));
                    req.setDateFrom(r.getTimestamp("date_from"));
                    req.setDateTo(r.getTimestamp("date_to"));
                    req.setCopyId(r.getInt("copy_id"));
                    req.setAccountId(r.getInt("account_id"));
                    req.setRented(r.getBoolean("is_rented"));
                    res.add(req);
                }
            }

        }
        return res;
    }


    public Request findById(int id) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM requests WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Request req = new Request();
                    req.setId(r.getInt("id"));
                    req.setDateFrom(r.getTimestamp("date_from"));
                    req.setDateTo(r.getTimestamp("date_to"));
                    req.setCopyId(r.getInt("copy_id"));
                    req.setAccountId(r.getInt("account_id"));
                    req.setRented(r.getBoolean("is_rented"));

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

}