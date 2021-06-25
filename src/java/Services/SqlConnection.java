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

//Esta clase es la principal para realizar cada conexion a la base de datos para generar las consultas en cada Servlet
public class SqlConnection {
    
    //Se crea variable tipo Connection
    private static Connection cnx = null;
    
    //Se crea el metodo para mandar a llamar la Conexion, se necesita crear en cada servlet para poder acceder a la escitura de la DB
    public static Connection getSqlConnection() throws SQLException, ClassNotFoundException{
        //Este metodo solo se inicializa si no existe una conexion inicial en la clase 
        if(cnx==null){  
            try{
                Class.forName("com.mysql.jdbc.Driver"); // Se manda a llamar al driver
                cnx = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/graficadora","root","1234"); //Se crea la conexion con el link local, el usuario y la pass de la Mysql
            }catch(SQLException ex){ //Se manejan excepciones en caso de que la respuesta de Mysql no sea exitosa 
                throw new SQLException(ex);
            }catch(ClassNotFoundException ex){
                throw new ClassCastException(ex.getMessage());
            }
        }
        //Regresa la conexion para poder utilizarla en la clase donde sea instanciado el objeto SqlConnection
        return cnx;
    }
    
    public static void closeSqlConnection() throws SQLException{
        //Si existe una conexion simplemente la cierra para evitar filtradciones de consultas en el proceso
        if(cnx != null){
            cnx.close();
        }
        
    }
}
