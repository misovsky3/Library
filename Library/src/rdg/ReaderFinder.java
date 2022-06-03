package rdg;


import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderFinder {
    private static final ReaderFinder INSTANCE = new ReaderFinder();
    public static ReaderFinder getINSTANCE() {
        return INSTANCE;
    }
    private ReaderFinder(){

    }

    public List<Reader> findAll() throws SQLException {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM accounts WHERE is_admin IS FALSE")) {
            try (ResultSet r = s.executeQuery()) {

                List<Reader> elements = new ArrayList<>();
                while (r.next()) {
                    Reader rr = new Reader();
                    rr.setId(r.getInt("id"));
                    rr.setName(r.getString("name"));
                    rr.setSurname(r.getString("surname"));
                    rr.setValidUntil(r.getTimestamp("valid_until"));
                    elements.add(rr);
                }
                return elements;
            }
        }
    }

    public Reader findById(int id) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM accounts WHERE id = ? AND is_admin IS FALSE")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Reader rr = new Reader();

                    rr.setId(r.getInt("id"));
                    rr.setName(r.getString("name"));
                    rr.setSurname(r.getString("surname"));
                    rr.setValidUntil(r.getTimestamp("valid_until"));

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
}
