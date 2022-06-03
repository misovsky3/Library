package ui;

import rdg.*;
import statistic.BookDay;
import statistic.Statistic;
import statistic.ResultSet;
import ts.PenaltyTS;
import ts.RequestTS;
import ts.ReturnTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;

import static statistic.Statistic.returnBooksAvailability;


/**
 *
 * @author Jakub Mišovský
 */
public class MainMenu extends Menu {

    @Override
    public void print() {
        System.out.println("***************************************");
        System.out.println("==========CRUD OPERATIONS=============");
        System.out.println("* 1. Print all readers                *");
        System.out.println("* 2. Add new reader                   *");
        System.out.println("* 3. Update a reader                  *");
        System.out.println("* 4. Delete a reader                  *");
        System.out.println("* 5. Find book by title               *");
        System.out.println("* 6. Find book by author              *");
        System.out.println("* 7. Add new book                     *");
        System.out.println("* 8. Update a book                    *");
        System.out.println("* 9. Delete a book                    *");
        System.out.println("* 10. Add new category                *");
        System.out.println("* 11. Delete a category               *");
        System.out.println("* 12. Show copies of a book           *");
        System.out.println("* 13. Add a copy                      *");
        System.out.println("* 14. Update a copy                   *");
        System.out.println("* 15. Delete a copy                   *");
        System.out.println("* 16. List all warehouses             *");
        System.out.println("* 17. Add new warehouse               *");
        System.out.println("* 18. Update a warehouse              *");
        System.out.println("* 19. Delete a warehouse              *");
        System.out.println("===========DOMAIN OPERATIONS===========");
        System.out.println("* 20. Request a book                  *");
        System.out.println("* 21. Delivery of a book              *");
        System.out.println("* 22. Pick up a book                  *");
        System.out.println("* 23. Return rented book              *");
        System.out.println("* 24. Send penalty messages           *");
        System.out.println("* 25. Pay penalty                     *");
        System.out.println("===========STATISTICS==================");
        System.out.println("* 26. Show book availability statistic *");
        System.out.println("* 27. Show term delay statisctic       *");
        System.out.println("* 28. Exit                             *");
        System.out.println("***************************************");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1" -> listAllReaders();
                case "2" -> addAReader();
                case "3" -> updateReader();
                case "4" -> deleteReader();
                case "5" -> findBookByTitle();
                case "6" -> findBookByAuthor();
                case "7" -> addABook();
                case "8" -> updateBook();
                case "9" -> deleteBook();
                case "10" -> addACategory();
                case "11" -> deleteCategory();
                case "12" -> showCopiesOfBook();
                case "13" -> addACopy();
                case "14" -> updateCopy();
                case "15" -> deleteCopy();
                case "16" -> listAllWarehouses();
                case "17" -> addAWarehouse();
                case "18" -> updateAWarehouse();
                case "19" -> deleteAWarehouse();
                case "20" -> requestBook();
                case "21" -> deliverBook();
                case "22" -> pickReservedBook();
                case "23" -> returnRentedBook();
                case "24" -> sendPenaltyMessagges();
                case "25" -> payThePenalty();
                case "26" -> showBooksAvailability();
                case "27" -> showTermDelay();
                case "28" -> exit();
                default -> System.out.println("Unknown option");
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

    }




    //  ================= CRUD =====================

    private void listAllReaders() {
        try {
            ReaderFinder rf = ReaderFinder.getINSTANCE();
            List<Reader> lr = rf.findAll();

            for (Reader reader : lr) {
                System.out.println(reader);
            }

            if(lr.isEmpty())
                System.out.println("No readers found");
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }


    private void addAReader() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Reader r = new Reader();
            System.out.println("To add a reader please enter following data");
            System.out.println("Enter a name:");
            r.setName(br.readLine());
            System.out.println("Enter a surname:");
            r.setSurname(br.readLine());
            String line;
            do {
                System.out.println("Enter validity period in days:");
                line = br.readLine();
            } while (!Functions.isInteger(line));


            r.setValidUntil(new Timestamp(System.currentTimeMillis() + Integer.parseInt(line) * 86400000L));
            r.insert();
            System.out.println("The reader has been added with id");
            System.out.println(r.getId());

        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateReader() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a reader's id:");
            int rId = Integer.parseInt(br.readLine());
            Reader r = ReaderFinder.getINSTANCE().findById(rId);

            System.out.println("Enter a new name:");
            r.setName(br.readLine());
            System.out.println("Enter a new surname:");
            r.setSurname(br.readLine());
            String line;
            do {
                System.out.println("Enter new validity period in days:");
                line = br.readLine();
            } while (!Functions.isInteger(line));


            r.setValidUntil(new Timestamp(System.currentTimeMillis() + Integer.parseInt(line) * 86400000L));
            r.update();

            System.out.println("The reader has been updated");

        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }




    private void deleteReader() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a reader's id:");
            int rId = Integer.parseInt(br.readLine());
            Reader r = ReaderFinder.getINSTANCE().findById(rId);

            System.out.println("Write \"yes\" if you really want to delete reader with id :" + rId + " ?");
            if (br.readLine().equals("yes")){
                r.delete();
                System.out.println("The reader has been deleted");
            }
            else
                System.out.println("The reader has not been deleted");



        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }


    private void findBookByTitle() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a book's title:");
            String title = String.valueOf(br.readLine());
            Book b = BookFinder.getINSTANCE().findByTitle(title);

            if (b != null) {
                System.out.println("Book has been found:");
                System.out.println(b);
            }
            else {
                System.out.println("Book has not been found");
            }


        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }

    private void findBookByAuthor() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a author's name:");
            String name = String.valueOf(br.readLine());
            System.out.println("Enter a author's surname:");
            String surname = String.valueOf(br.readLine());
            Book b = BookFinder.getINSTANCE().findByAuthor(name,surname);

            if (b != null) {
                System.out.println("Book has been found:");
                System.out.println(b);
            }
            else {
                System.out.println("Book has not been found");
            }


        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }

    private void addABook() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Book b = new Book();
            System.out.println("To add a book please enter following data");
            System.out.println("Enter book title:");
            b.setTitle(br.readLine());
            System.out.println("Enter author's name:");
            b.setAuthorName(br.readLine());
            System.out.println("Enter author's surname:");
            b.setAuthorSurname(br.readLine());

            b.insert();
            System.out.println("The book has been added with id");
            System.out.println(b.getId());

        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void updateBook() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a book's id:");
            int rId = Integer.parseInt(br.readLine());
            Book b = BookFinder.getINSTANCE().findById(rId);

            System.out.println("Enter a new title:");
            b.setTitle(br.readLine());
            System.out.println("Enter a new author's name:");
            b.setAuthorName(br.readLine());
            System.out.println("Enter a new author's surname:");
            b.setAuthorSurname(br.readLine());
            b.update();

            System.out.println("The book has been updated");

        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }




    private void deleteBook() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a book's id:");
            int rId = Integer.parseInt(br.readLine());
            Book b = BookFinder.getINSTANCE().findById(rId);

            System.out.println("Write \"yes\" if you really want to delete book with id :" + rId + " ?");
            if (br.readLine().equals("yes")){
                b.delete();
                System.out.println("The book has been deleted");
            }
            else
                System.out.println("The book has not been deleted");



        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }



    private void addACategory() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Category c = new Category();
            System.out.println("To add a category please enter following data");
            System.out.println("Enter category name:");
            c.setCategory(br.readLine());
            String term;
            do {
                System.out.println("Enter a term in days");
                term = br.readLine();
            }
            while (!Functions.isInteger(term));
            c.setTerm(Integer.valueOf(term));
            c.insert();
            System.out.println("The category has been added with id");
            System.out.println(c.getId());

        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteCategory() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a category id:");
            int rId = Integer.parseInt(br.readLine());
            Category c = CategoryFinder.getINSTANCE().findById(rId);

            System.out.println("Write \"yes\" if you really want to delete category with id :" + rId + " ?");
            if (br.readLine().equals("yes")){
                c.delete();
                System.out.println("The category has been deleted");
            }
            else
                System.out.println("The category has not been deleted");



        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }

    private void showCopiesOfBook() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a book's id:");
            Integer id = Integer.parseInt(br.readLine());
            CopyFinder cf = CopyFinder.getINSTANCE();
            List<Copy> cr = cf.findCopiesOfBook(id);
            Book b = BookFinder.getINSTANCE().findById(cr.get(0).getBookId());
            System.out.println("The chosen book: " + b );
            System.out.println("And the copies: ");
            for (Copy copy : cr) {
                System.out.println(copy);
            }

            if(cr.isEmpty())
                System.out.println("No readers found");


        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }


    }

    private void deleteCopy() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a copy id:");
            int rId = Integer.parseInt(br.readLine());
            Copy c = CopyFinder.getINSTANCE().findById(rId);

            System.out.println("Write \"yes\" if you really want to delete copy with id :" + rId + " ?");
            if (br.readLine().equals("yes")){
                c.delete();
                System.out.println("The copy has been deleted");
            }
            else
                System.out.println("The copy has not been deleted");



        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }

    private void updateCopy() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a copy id:");
            int rId = Integer.parseInt(br.readLine());
            Copy c = CopyFinder.getINSTANCE().findById(rId);

            System.out.println("Enter yes or no if it is in library:");
            c.setInLibrary("yes".equals(br.readLine()));
            System.out.println("Enter yes or no if it is in warehouse:");
            c.setInWarehouse("yes".equals(br.readLine()));
            String state;
            do {
                System.out.println("Enter new copy state");
                state = br.readLine();
            }
            while (!Functions.isInteger(state));
            c.setCopyState(Integer.valueOf(state));

            System.out.println("Enter yes or no if it is lendable:");
            c.setLendable("yes".equals(br.readLine()));

            System.out.println("Enter a booky id:");
            c.setBookId(Integer.parseInt(br.readLine()));

            System.out.println("Enter a category id:");
            c.setCategoryId(Integer.parseInt(br.readLine()));

            if (c.isInWarehouse()){
                System.out.println("Enter a warehouse id:");
                c.setWarehouseId(Integer.parseInt(br.readLine()));
            }

            c.update();

            System.out.println("The copy has been updated");

        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }


    }

    private void addACopy() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Copy c = new Copy();
            System.out.println("To add a copy please enter following data");

            System.out.println("Enter yes or no if it is in library:");
            c.setInLibrary("yes".equals(br.readLine()));
            System.out.println("Enter yes or no if it is in warehouse:");
            c.setInWarehouse("yes".equals(br.readLine()));
            String state;
            do {
                System.out.println("Enter new copy state");
                state = br.readLine();
            }
            while (!Functions.isInteger(state));
            c.setCopyState(Integer.valueOf(state));

            System.out.println("Enter yes or no if it is lendable:");
            c.setLendable("yes".equals(br.readLine()));

            System.out.println("Enter a booky id:");
            c.setBookId(Integer.parseInt(br.readLine()));

            System.out.println("Enter a category id:");
            c.setCategoryId(Integer.parseInt(br.readLine()));

            if (c.isInWarehouse()){
                System.out.println("Enter a warehouse id:");
                c.setWarehouseId(Integer.parseInt(br.readLine()));
            }

            c.insert();

            System.out.println("The copy has been updated");

        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }


    }

    private void listAllWarehouses() {
        try {
            WarehouseFinder wf = WarehouseFinder.getINSTANCE();
            List<Warehouse> lw = wf.findAll();

            for (Warehouse warehouse : lw) {
                System.out.println(warehouse);
            }

            if(lw.isEmpty())
                System.out.println("No warehouses found");
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }


    private void deleteAWarehouse() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a warehouse id:");
            int rId = Integer.parseInt(br.readLine());
            Warehouse w = WarehouseFinder.getINSTANCE().findById(rId);

            System.out.println("Write \"yes\" if you really want to delete warehouse with id :" + rId + " ?");
            if (br.readLine().equals("yes")){
                w.delete();
                System.out.println("The warehouse has been deleted");
            }
            else
                System.out.println("The warehouse has not been deleted");



        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }

    private void updateAWarehouse() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a warehouse id:");
            int rId = Integer.parseInt(br.readLine());
            Warehouse w = WarehouseFinder.getINSTANCE().findById(rId);
            System.out.println("To add new warehouse please enter following data");
            System.out.println("Enter a warehouse name:");
            w.setName(br.readLine());
            System.out.println("Enter an address of the warehouse:");
            w.setAddress(br.readLine());

            w.update();

            System.out.println("The warehouse has been updated");

        }
        catch(SQLException | IOException e)
        {
            System.out.println(e);
        }

    }




    private void addAWarehouse() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Warehouse w = new Warehouse();
            System.out.println("To add new warehouse please enter following data");
            System.out.println("Enter a warehouse name:");
            w.setName(br.readLine());
            System.out.println("Enter an address of the warehouse:");
            w.setAddress(br.readLine());
            w.insert();
            System.out.println("The warehouse has been added with id");
            System.out.println(w.getId());

        }
        catch(SQLException e) {
            System.out.println(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void payThePenalty()
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter reader's id:");
            int rId = Integer.parseInt(br.readLine());

            List<Penalty> penalties = PenaltyFinder.getINSTANCE().findUnpaidPenaltyById(rId);
            if (penalties.isEmpty()) {
                System.out.println("Reader does not have penalties");
                return;

            }
            System.out.println(penalties);

            double sumPenalties = 0;
            for(Penalty penalty:penalties){
                sumPenalties += penalty.getAmount();
            }

            System.out.println("The sum of readers penalties is: " + sumPenalties);

            System.out.println("Please enter id's of penalties which will be paid (separated by comma):");
            Integer[] penaltiesToPay = Stream.of(br.readLine().split(",")).map(Integer::valueOf).toArray(Integer[]::new);
            List<Penalty> remainingPenalties = PenaltyTS.payPenalties(rId,penaltiesToPay);
            System.out.println("Penalties were paid");
            if(remainingPenalties.isEmpty()){
                System.out.println("There are no more penalties of reader");
            }
            else{
                System.out.print("There are some remaining penalties: ");
                System.out.println(remainingPenalties);
            }
            penalties = PenaltyFinder.getINSTANCE().findUnpaidPenaltyById(rId);
            sumPenalties = 0;
            for(Penalty penalty:penalties){
                sumPenalties += penalty.getAmount();
            }
            System.out.println("The new sum of reader penalties is: " + sumPenalties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    //  ================= DOMAIN OPERATIONS =====================
    private void requestBook() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("Enter reader's id:");
            int readerId = Integer.parseInt(br.readLine());
            Reader r = ReaderFinder.getINSTANCE().findById(readerId);
            if (!r.isValid()){
                Functions.alert("Reader does not have valid account");
                return;
            }

            if(r.hasUnpaidPenalty()){
                Functions.alert("Reader has an unpaid penalty");
                return;
            }
            System.out.println("Reader does have valid account and doesn't have unpaid penalties");


            System.out.println("Enter book id:");
            int bookId = Integer.parseInt(br.readLine());

            List<Copy> c = CopyFinder.getINSTANCE().findCopiesOfBook(bookId);

            if(c.isEmpty()){
                Functions.alert("There is not a free copy of the book");
                return;
            }

            Copy freeCopy = null;
            for (Copy copy:c) {
                if (copy.isFree()){
                    freeCopy = copy;
                    break;
                }
            }

            Request req = new Request();
            var actualTime = System.currentTimeMillis();
            req.setDateFrom(new Timestamp(actualTime));
            req.setDateTo(new Timestamp(actualTime + 3 * 259200000L));
            req.setRented(false);
            req.setAccountId(r.getId());
            assert freeCopy != null;
            req.setCopyId(freeCopy.getId());
            req.insert();



            System.out.println("Reservation of user with id " + r.getId() + " of the copy of id "
                    + freeCopy.getId() + "has been done");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deliverBook() throws SQLException, IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int rId = Integer.parseInt(br.readLine());
            List<Request> req = RequestFinder.getINSTANCE().getAllRequests();
            System.out.println(req);
            System.out.println("Books are ready for picking up");


        }
        catch (Exception e){
            e.printStackTrace();
        }


    }


    private void pickReservedBook()
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter reader's id:");
            int rId = Integer.parseInt(br.readLine());

            List<Request> req = RequestFinder.getINSTANCE().getCurrentRequests(rId);

            if(req.isEmpty()) {
                System.out.println("There are not requests of reader of id: " + rId);
                return;
            }
            else {
                System.out.println("These are current requests:");
                System.out.println(req);
            }

            System.out.println("Enter request id:");
            int reqId = Integer.parseInt(br.readLine());

            Timestamp date;

            try
            {
                date = RequestTS.getReservedBooks(rId, reqId);
            }

            catch(Exception e)
            {
                e.printStackTrace();
                return;
            }


            System.out.println("Book has been successfully rented until " + date);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println("Wrong input!");
        }
    }

    private void returnRentedBook() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter rented copy id");
            int cId = Integer.parseInt(br.readLine());
            Copy c = CopyFinder.getINSTANCE().findById(cId);
            System.out.println("Current copy state is " + c.getCopyState() + " ,enter new copy state");
            int cState = Integer.parseInt(br.readLine());
            Penalty penalty;
            try
            {
                penalty = ReturnTS.returnBook(cId,cState);
                System.out.println("Reader has penalty:");
                System.out.println(penalty);
            }


            catch(Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("The copy was succesfully returned");

        }
        catch (Exception e){
            e.printStackTrace();

        }

    }


    private void sendPenaltyMessagges() {
        try{
          var penalties = PenaltyTS.getPenaltiesFromDelayedCopies();
            for (Penalty penalty:penalties) {
                System.out.println(penalty);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Write \"yes\" if you really want to add penalties into database?");
            if (br.readLine().equals("yes")){
                PenaltyTS.insertPenalties(penalties);
                System.out.println("Penalties have been inserted!");
            }
            else
                System.out.println("Penalties have not been inserted");




        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //  ================= STATISTICS =====================
    private void showBooksAvailability() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("Enter how many books do you want to show");
            int number = Integer.parseInt(br.readLine());
            for (BookDay bd: returnBooksAvailability(number)) {
                System.out.println(bd);


            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void showTermDelay() {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter N");
            int N = Integer.parseInt(br.readLine());

            List<ResultSet> lines = Statistic.getTermDelay(N);
            System.out.println(lines);
        }
        catch(SQLException e)
        {
            System.out.println("Something went wrong, please try again.");
        }
        catch(IOException e)
        {
            System.out.println("Wrong input !");
        }
    }

}