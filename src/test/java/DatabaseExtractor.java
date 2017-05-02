import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * @author vhazrati
 *
 */
public class DatabaseExtractor {

	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		Class.forName("com.mysql.jdbc.Driver");

		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost/sakila", "root", "root");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		QueryDataSet partialDataSet = new QueryDataSet(connection);

		// Mention all the tables here for which you want data to be extracted
		// take note of the order to prevent FK constraint violation when re-inserting
		partialDataSet.addTable("salario");

		// XML file into which data needs to be extracted
		FlatXmlDataSet.write(partialDataSet, new FileOutputStream("src/test/resources/DbUnitXml/salario.xml"));
		System.out.println("Dataset written");
	}
}