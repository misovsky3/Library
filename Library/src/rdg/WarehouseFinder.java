package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseFinder {


    private static final WarehouseFinder INSTANCE = new WarehouseFinder();

    public static WarehouseFinder getINSTANCE() {
        return INSTANCE;
    }

    private WarehouseFinder() {
    }

    public List<Warehouse> findAll() throws SQLException {
        List<Warehouse> res = new ArrayList<>();
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM warehouses")) {
            try (ResultSet r = s.executeQuery()) {
                while(r.next()) {
                    Warehouse rr = new Warehouse();
                    rr.setId(r.getInt("id"));
                    rr.setName(r.getString("name"));
                    rr.setAddress(r.getString("address"));
                    res.add(rr);
                }

            }
        }
        return res;
    }
    public Warehouse findById(int id) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM warehouses WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Warehouse rr = new Warehouse();
                    rr.setId(r.getInt("id"));
                    rr.setName(r.getString("name"));
                    rr.setAddress(r.getString("address"));
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