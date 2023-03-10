package Verarbeitung;

import java.sql.*;
import java.util.ArrayList;

public class Datenbank {
	
    private static Connection connection;
    private static String URL = "jdbc:mysql://localhost:3306/";
    private static String USERNAME = "root";
    private static final String PASSWORD = "";
    private String db_username;
    
    public Datenbank(String db_username) {
    	
        this.db_username = db_username;
        
    }
    
    /**
     * Versucht Verbindung zu Datenbank aufzubauen
     */
    public void erstelleVerbindung() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }
    
    /**
     * Erstellt Datenbank anhand eines Usernames
     */
    public void erstelleDatenbank() {
        String sql = "CREATE DATABASE "+db_username;
        try {
        	
        	Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Datenbank "+db_username+" wurde erstellt");
            
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }
    
    /**
     * Erstellt Lernset in Datenbank
     */
    public void erstelleLernSet(String name) {
    	
    	String sql = "CREATE TABLE "+name+" ("           
                + "Englisch VARCHAR(50) NOT NULL,"
                + "Deutsch VARCHAR(50) NOT NULL,"
                + ");";
    	try {
    		
    		Statement stmt = connection.createStatement();
    		stmt.execute(sql);
    		System.out.println("Lerset "+name+"wurde erstellt");
    		
    	} catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }
    
    public void gibLernsetAus() {
    	
    }
    
    /**
     * Stellt Datenverbindung her
     */
    public static Connection getConnection() {
        return connection;
    }
    /**
     * Versucht Verbindung zu Datenbank zu trennen
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection to database closed");
        } catch (SQLException e) {
            System.err.println("Error closing connection to database: " + e.getMessage());
        }
    }
    /**
     * Schlie??t Datenbankverbindung
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Erstellt Tabelle in der Datenbank
     */
    //Values m??ssen in seperater Methode erstellt werden
    public void insert(String table, String column1, String column2, String values) {
        String sql = "INSERT INTO " + table + "(" + column1 + column2 + ") VALUES(" + values + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Gibt ein Datensatz anhand eines sql Befehls zur??ck
     */
    public ResultSet select(String table, String columns, String where) {
        String sql = "SELECT " + columns + " FROM " + table;
        if (where != null) {
            sql += " WHERE " + where;
        }
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * F??gt Datens??tze in eine Arraylist ein
     */
    public ArrayList<ArrayList<String>> executeQuery(String query) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * Updated Datens??tze in der Datenbank anhand von Variablen, welche der Methoden ??bergeben werden
     */
    public void update(String table, String set, String where) {
        String sql = "UPDATE " + table + " SET " + set;
        if (where != null) {
            sql += " WHERE " + where;
        }
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * L??scht Daten aus einer Datenbank anhand von Variablen, welche der Methoden ??bergeben werden
     */
    public void delete(String table, String where) {
        String sql = "DELETE FROM " + table;
        if (where != null) {
            sql += " WHERE " + where;
        }
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * F??hrt Update aus
     */
    public void executeUpdate(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * ??berpr??ft anhand einers Tabellennamens, ob eine Tabelle existiert
     */
    public boolean tableExists(String tableName) {
        try (ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, null)) {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
