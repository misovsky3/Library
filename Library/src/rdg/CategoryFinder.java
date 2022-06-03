package rdg;

import main.DBContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryFinder {


    private static final CategoryFinder INSTANCE = new CategoryFinder();
    public static CategoryFinder getINSTANCE() {
        return INSTANCE;
    }
    private CategoryFinder(){}


    public List<Category> findAll(int max) throws SQLException {
        List<Category> res = new ArrayList<>();
        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM categories LIMIT ?")) {
            s.setInt(1,max);
            try (ResultSet r = s.executeQuery()) {
                while(r.next()) {
                    Category rr = new Category();
                    rr.setId(r.getInt("id"));
                    rr.setCategory(r.getString("category"));
                    rr.setTerm(r.getInt("term"));
                    res.add(rr);
                }

            }
        }
        return res;
    }

    public Category findById(int id) throws SQLException {

        try (PreparedStatement s = DBContext.getConnection().prepareStatement("SELECT * FROM categories WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Category rr = new Category();
                    rr.setId(r.getInt("id"));
                    rr.setCategory(r.getString("category"));
                    rr.setTerm(r.getInt("term"));

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
