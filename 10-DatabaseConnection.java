import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_shubh_patel_136291", "","");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
}