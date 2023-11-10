/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PizzaApp.DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author joeau
 */
public class ConnectionFactory {
    // ------------------Métodos:------------------ //
    public Connection getConnection() {
        try {
            // Carregaremos as credenciais do arquivo BD.properties:
            Properties prop = new Properties();
            prop.load(getClass().getResourceAsStream("BD.properties"));
            String dbUrl = prop.getProperty("db.url");
            String dbUser = prop.getProperty("db.user");
            String dbPwd = prop.getProperty("db.pwd");
            
            return DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }  
    }
    // ---------------------------------------------- //
}
