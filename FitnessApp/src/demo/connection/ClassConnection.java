/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.connection;

import java.sql.*;

/**
 *
 * @author Main
 */
public class ClassConnection {

    private static final String jdbcDriver  = "com.mysql.cj.jdbc.Driver";
    private static final String dbPath      = "jdbc:mysql://localhost:3307/";
    private static final String dbName      = "csis_231_db";
    private static final String user        = "root";
    private static final String pwd         = "TeXtMeSsAgE10_";
    
    private Connection conn;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    public ClassConnection()
    {
        connect();
    }
    
    private void connect()
    {
        try{
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(dbPath+dbName, user, pwd);
            statement = conn.createStatement();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public ResultSet executeQuery(String sql)
    {
        ResultSet resultSet = null;
        try{
            resultSet = statement.executeQuery(sql);
        }catch(Exception e)
        {
            
        }
        return resultSet;
        
    }
    
    public PreparedStatement loadPreparedStatement(String sql)
    {
        try{
            preparedStatement = conn.prepareStatement(sql);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            return preparedStatement;
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection connection) {
        this.conn = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
    
    
}
