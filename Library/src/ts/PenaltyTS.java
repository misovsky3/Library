package ts;

import main.DBContext;
import org.postgresql.util.PSQLException;
import rdg.Penalty;
import rdg.PenaltyFinder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PenaltyTS {
    public static List<Penalty> getPenaltiesFromDelayedCopies() throws SQLException, Exception{
        for(int i=0; i< 10; i++)
        {
            try
            {
                DBContext.getConnection().setAutoCommit(false);
                //s datami sa uz nemeni tak read committed
                DBContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);


                PenaltyFinder pf = PenaltyFinder.getINSTANCE();
                List<Penalty> penalties = pf.getAllFromRentals();


                return penalties;

            }
            catch(PSQLException e)
            {
                System.out.println("Repeating Transaction");
            }
            finally
            {
                DBContext.getConnection().commit();
                DBContext.getConnection().setAutoCommit(true);
            }
        }
        throw new Exception("Something went wrong, please try again.");

    }

    public static void insertPenalties(List<Penalty> penalties) throws Exception {
        try {
            for (Penalty penalty : penalties) {
                penalty.insert();
            }
        }
        catch (Exception e){
            throw new Exception("Insert error!");
        }

    }

    public static List<Penalty> payPenalties(int rId,Integer[] penaltiesToPay) throws SQLException, Exception{
        for(int i=0; i< 10; i++)
        {
            try
            {
                DBContext.getConnection().setAutoCommit(false);

                DBContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                //aby sa dvakrat nezaplatilo to iste

                //DBContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                for (int pId: penaltiesToPay) {
                    Penalty penalty = PenaltyFinder.getINSTANCE().findPenaltyById(pId);
                    penalty.makePaid();
                }

                PenaltyFinder pf = PenaltyFinder.getINSTANCE();
                List<Penalty> penalties = pf.findUnpaidPenaltyById(rId);


                return penalties;

            }
            catch(PSQLException e)
            {
                System.out.println("Repeating Transaction");
            }
            finally
            {
                DBContext.getConnection().commit();
                DBContext.getConnection().setAutoCommit(true);
            }
        }
        throw new Exception("Something went wrong, please try again.");
    }
}
