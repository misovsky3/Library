package statistic;

import main.DBContext;
import rdg.Book;
import rdg.BookFinder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Statistic {



    public static BookDay bookAvailability(int id) throws SQLException
    {
        try (PreparedStatement s = DBContext.getConnection().prepareStatement(
                "SELECT AVG((365 - EXTRACT(day FROM (date_to - date_from)))/12) as days " +
                        "FROM rentals WHERE copy_id IN " +
                        "(SELECT id FROM copies WHERE book_id = ?);")){



                    s.setInt(1,id);
            try (java.sql.ResultSet r = s.executeQuery()) {
                BookDay rr = new BookDay();
                if (r.next()) {
                    rr.setBookId(id);
                    rr.setDay(r.getDouble("days"));
                }
                return rr;
            }
        }
    }

    public static List<BookDay> returnBooksAvailability(int max) throws SQLException {
        BookFinder b = BookFinder.getINSTANCE();
        List<Book> books = b.findAll(max);
        List <BookDay> res = new ArrayList<>();
        for (Book book:books) {
            res.add(bookAvailability(book.getId()));
        }
        return res;
    }

    public static List<ResultSet> getTermDelay(int N) throws SQLException
        {
            PreparedStatement p = DBContext.getConnection().prepareStatement(
            "SELECT i,COUNT(i), SUM(amount) FROM GENERATE_SERIES(1,?) AS seq(i) LEFT JOIN penalties ON delay IS NOT NULL AND" +
                    " delay > i GROUP BY i ORDER BY i ");
            //Používateľ zadá na vstupe N. Pre každé X od 1 po N vypočítajte, o koľko menej upomienok by čítatelia
            // dostali a koľko menej by knižnica zarobila na pokutách za oneskorené vrátenie, ak by sa všetky
            // výpožičné doby predĺžili o X dní.

            p.setInt(1, N);
            java.sql.ResultSet r = p.executeQuery();
            List<ResultSet> list = new ArrayList();
            while (r.next()) {
                ResultSet element = new ResultSet();
                element.setAmountDifference(r.getDouble("sum"));
                element.setPenaltiesDifference(r.getInt("count"));
                element.setDayDelay(r.getInt("i"));
                list.add(element);
            }

            return list;

        }


}
