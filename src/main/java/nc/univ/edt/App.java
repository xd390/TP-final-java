package nc.univ.edt;

package nc.univ.springdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import nc.univ.springdata.dao.AvatarRepository;
import nc.univ.springdata.dao.CiviliteRepository;
import nc.univ.springdata.dao.UtilisateurRepository;
import nc.univ.springdata.model.*;
import nc.univ.springdata.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Table;
import javax.sql.DataSource;

/**
 * Classe de test de Spring JPA et Spring DATA
 *
 * @author mathieu.fabre
 */
public class App {

    /**
     * Point d entrée de l'application
     *
     * @param args
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

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
