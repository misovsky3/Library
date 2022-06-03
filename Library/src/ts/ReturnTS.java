package ts;

import main.DBContext;
import org.postgresql.util.PSQLException;
import rdg.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;






public class ReturnTS {
    public static Penalty returnBook(int cId, int state) throws SQLException, Exception {
        for(int i=0; i<10; i++)
        {
            try
            {
                DBContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                //ak by bola kniha poskodena a vratil by ju, aby sa nestalo ze sa mu to zauctuje viackrat
                DBContext.getConnection().setAutoCommit(false);


                Rental ren = RentalFinder.getINSTANCE().findByCopyId(cId);

                if(ren.isReturned()) {
                    throw new Exception("Book is returned.");
                }

                Copy c = CopyFinder.getINSTANCE().findById(cId);
                Penalty penalty = new Penalty();

                if(state < c.getCopyState()) {
                    Double amount = (c.getCopyState() - state) * penalty.getDAMAGE_PENALTY();


                    penalty.setAccountId(ren.getAccountId());
                    penalty.setAmount(amount);
                    penalty.setDamaged(true);
                    penalty.setPaid(false);
                    penalty.setDelay(0);
                    penalty.insert();

                }

                //update rental
                ren.setReturned(true);
                ren.setDateTo(new Timestamp(System.currentTimeMillis()));

                ren.update();

                // update copy
                c.setInLibrary(true);
                c.setCopyState(state);
                c.update();
                return penalty;

            }
            catch(PSQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                DBContext.getConnection().commit();
                DBContext.getConnection().setAutoCommit(true);
            }
        }
        throw new Error("Something went wrong, please try again");
    }

}

