package nc.univ.edt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class EdtApplication implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EdtApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(Config.class);

		DispatcherServlet servlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("app",servlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/edt/*");
	}

	/**
	 * Detail des tables en base
	 */
	private static void printDatabaseDetail(ApplicationContext applicationContext) throws SQLException {

		// Recuperation de la dataSource
		DataSource dataSource = (DataSource)applicationContext.getBean("dataSource");
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();

		// Recuperation et affichage du detail
		ResultSet resultSetTables = statement.executeQuery("SHOW TABLES");
		while(resultSetTables.next()) {

			// Recuperation du nom de la table
			String tableName = resultSetTables.getString(1);
			System.out.println("============ Detail de la table " + tableName + " ==================");

			Statement tableStatement = connection.createStatement();
			ResultSet resultSet = tableStatement.executeQuery("select column_name, type_name from information_schema.columns where table_name='" + tableName + "'");
			while(resultSet.next())
				System.out.println("Colonne " + resultSet.getString(1) + " de type " + resultSet.getString(2));
		}
	}
}
