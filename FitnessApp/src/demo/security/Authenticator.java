package demo.security;

import demo.connection.ClassConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Authenticator {
    private static final Map<String, String> USERS = new HashMap<String, String>();

    private static final String jdbcDriver  = "com.mysql.cj.jdbc.Driver";
    private static final String dbPath      = "jdbc:mysql://localhost:3307/";
    private static final String dbName      = "csis_231_db";
    private static final String user        = "root";
    private static final String pwd         = "TeXtMeSsAgE10_";
    
    
    public static boolean validate(String user, String password) {
            try {
                String authSQL = "SELECT * FROM user WHERE username = '" + user + "' and password = '" + password + "'";
                ClassConnection classConnection = new ClassConnection();
                Statement statement = classConnection.getStatement();
                ResultSet resultSet = statement.executeQuery(authSQL);
                resultSet.absolute(1);
                if (resultSet.absolute(1)) {
                    String uname = resultSet.getString("username");
                    String pwd = resultSet.getString("password");

                    USERS.put(pwd, uname);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String validUserPassword = USERS.get(user);
            return validUserPassword != null && validUserPassword.equals(password);
        }
    
}