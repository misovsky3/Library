package main;

import org.postgresql.ds.PGSimpleDataSource;
import ui.MainMenu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Jakub Mišovský
 */
public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setServerNames(new String[]{"localhost"});
        dataSource.setPortNumbers(new int[]{5432});
        dataSource.setDatabaseName("Kniznica");
        dataSource.setUser("postgres");
        dataSource.setPassword("Paralen500");



        try (Connection connection = dataSource.getConnection()) {
            DBContext.setConnection(connection);
            System.out.println(new Timestamp(System.currentTimeMillis()));
            MainMenu mainMenu = new MainMenu();
            mainMenu.run();



        } finally {
            DBContext.clear();
        }
    }
}
