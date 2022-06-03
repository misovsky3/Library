package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class PenaltyFinder {
    private static final PenaltyFinder INSTANCE = new PenaltyFinder();

    public static PenaltyFinder getINSTANCE() {
        return INSTANCE;
    }

    private PenaltyFinder() {

    }



    /**
     * return unpaid penalties of reader
     * @param rId - id of a reader
     * @return whether list of reader's penalties
     * @throws java.sql.SQLException  - incorrect query
     */

    public List<Penalty> findUnpaidPenaltyById(int rId) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM penalties WHERE account_id = ? and is_paid IS FALSE")) {
            s.setInt(1, rId);

            try (ResultSet r = s.executeQuery()) {

                List<Penalty> elements = new ArrayList<>();
                while (r.next()) {
                    Penalty rr = new Penalty();
                    rr.setId(r.getInt("id"));
                    rr.setDelay(r.getInt("delay"));
                    rr.setDamaged(r.getBoolean("is_damaged"));
                    rr.setAmount(r.getDouble("amount"));
                    rr.setPaid(r.getBoolean("is_paid"));
                    rr.setAccountId(r.getInt("account_id"));
                    elements.add(rr);

                }
                return elements;
            }
        }
    }

    /**
     * return penalties of id
     * @param Id - id of a penalty
     * @return list of reader's penalties
     * @throws java.sql.SQLException  - incorrect query
     */
    public Penalty findPenaltyById(int Id) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM penalties WHERE id = ?")) {
            s.setInt(1, Id);

            try (ResultSet r = s.executeQuery()) {

                List<Penalty> elements = new ArrayList<>();
                if (r.next()) {
                    Penalty rr = new Penalty();
                    rr.setId(r.getInt("id"));
                    return rr;

                }
                return null;
            }
        }
    }


    public List<Penalty> getAllFromRentals() throws SQLException {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("" +
                "(SELECT account_id,copy_id,20 as penalty, extract(DAY from (CURRENT_TIMESTAMP - date_to)) as delay  FROM rentals WHERE is_returned IS FALSE AND (extract(DAY from (CURRENT_TIMESTAMP - date_to)) = 30) )\n" +
                "UNION \n" +
                "(SELECT account_id,copy_id,10 as penalty, extract(DAY from (CURRENT_TIMESTAMP - date_to)) as delay  FROM rentals WHERE is_returned IS FALSE AND (extract(DAY from (CURRENT_TIMESTAMP - date_to)) = 7) )\n" +
                "UNION \n" +
                "(SELECT account_id,copy_id,5 as penalty, extract(DAY from (CURRENT_TIMESTAMP - date_to)) as delay  FROM rentals WHERE is_returned IS FALSE AND (extract(DAY from (CURRENT_TIMESTAMP - date_to)) = 1)  );")) {
            try (ResultSet r = s.executeQuery()) {

                List<Penalty> elements = new ArrayList<>();
                while (r.next()) {
                    Penalty rr = new Penalty();
                    rr.setDelay(r.getInt("delay"));
                    rr.setDamaged(false);
                    rr.setAmount(r.getDouble("penalty"));
                    rr.setPaid(false);
                    rr.setAccountId(r.getInt("account_id"));
                    elements.add(rr);
                }
                return elements;
            }
        }
    }


}