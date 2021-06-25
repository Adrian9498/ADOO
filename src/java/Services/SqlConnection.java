/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.*;
/**
 *
 * @author irda2
 */
public class SqlConnection {
    private static Connection cnx = null;
    
    public static Connection getSqlConnection() throws SQLException, ClassNotFoundException{
        if(cnx==null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                cnx = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/graficadora","root","1234"); 
            }catch(SQLException ex){
                throw new SQLException(ex);
            }catch(ClassNotFoundException ex){
                throw new ClassCastException(ex.getMessage());
            }
        }
        return cnx;
    }
    
    public static void closeSqlConnection() throws SQLException{
        if(cnx != null){
            cnx.close();
        }
        
    }
}
