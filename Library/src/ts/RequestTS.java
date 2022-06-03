package ts;

import main.DBContext;
import org.postgresql.util.PSQLException;
import rdg.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RequestTS {
    public static Timestamp getReservedBooks(int readerId, int rId) throws SQLException, Exception {

        for(int i=0; i<10; i++)
        {
            try
            {
                DBContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                //aby nevzniklo viacero rentals z jednej requests
                DBContext.getConnection().setAutoCommit(false);
                Request req = RequestFinder.getINSTANCE().findById(rId);
                Copy c = CopyFinder.getINSTANCE().findById(req.getCopyId());
                //Finding out if copy is pickable
                if (!(c.isInLibrary() || c.isInWarehouse())){
                    throw new Exception("The copy is not in warehouse or library");
                }

                //Insert into rental
                Rental ren = new Rental();
                ren.setCopyId(c.getId());
                ren.setAccountId(readerId);
                ren.setReturned(false);
                ren.setDateFrom(new Timestamp(System.currentTimeMillis()));
                Category cat = CategoryFinder.getINSTANCE().findById(c.getCategoryId());
                ren.setDateTo(new Timestamp(System.currentTimeMillis() + cat.getTerm()*24*60*60*1000));
                ren.insert();

                //Update request
                req.setRented(true);
                req.update();

                //Update copy
                c.setInLibrary(false);
                c.update();
                return ren.getDateTo();



            }
            catch(PSQLException e)
            {
                System.err.println("Trying again");
            }
            catch (Exception p){
                p.printStackTrace();
            }
            finally
            {
                DBContext.getConnection().commit();
                DBContext.getConnection().setAutoCommit(true);
            }

        }
        return null;
    }



}
