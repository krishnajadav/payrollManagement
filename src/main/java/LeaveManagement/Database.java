package LeaveManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class Database {

    private String host;
    private String port;
    private String pwd;
    private String username;
    private String dbname;

    public  Database(String host, String port, String pwd, String username, String DatabaseName){
    this.host=host;
    this .port=port;
    this.pwd=pwd;
    this.username=username;
    this.dbname=DatabaseName;
    }


    protected Connection dbconnection() {

        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.port+"/"+this.dbname,
                    this.username, this.pwd);

        }catch(Exception e){
         e.printStackTrace();
         return null;
        }
        return con;
    }

    protected void closeConnection(Connection con) throws SQLException {
        if(con!=null){
            con.close();
        }
    }

}
