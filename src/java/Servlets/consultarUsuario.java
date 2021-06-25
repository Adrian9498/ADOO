/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Services.SqlConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author irda2
 */

/*Se crea la clase consultarUsuario siendo una de las clases que se utilizan para modificar la tabla usuarios de la BD
  Esta clase consulta registros de dicha tabla
*/
@WebServlet(name = "consultarUsuario", urlPatterns = {"/consultarUsuario"})
public class consultarUsuario extends HttpServlet {
    //Se crea la conexion y se define dentro del constructor para que en la llamada del objeto cree la conexion a MySQL
    Connection cn;
    
    public consultarUsuario() throws SQLException, ClassNotFoundException {
        this.cn = SqlConnection.getSqlConnection();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //Lineas de codigo que se ejecutan en el request del Servlet
        response.setContentType("text/html;charset=UTF-8");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String nombre;
        int id;
        ResultSet rs = readUser(cn,correo,contrasena);//Metodo definido para leer usuarios definido abajo
        
        //Se obtienen todos los datos del result set
        while(rs.next()){
            id = rs.getInt("id");
            nombre = rs.getString("nombre");
            correo = rs.getString("correo");
            contrasena = rs.getString("contrasena");
        }
        
        /*try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet consultarUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet consultarUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }
    //Metodo utilizado para leer usuarios de la DB
    public ResultSet readUser(Connection cn,String correo, String contrasena) throws SQLException{
        ResultSet rs; //Se crea un result set que sera la respuesta del metodo
        try{
            PreparedStatement lectura; //Se utilizan Statements para realizar las consultas
            lectura = cn.prepareStatement("SELECT * FROM usuario WHERE correo=? AND contrasena=?");//En las siguientes lineas se crea el query a ejecutar
            lectura.setString(1,correo);
            lectura.setString(2,contrasena);
            rs = lectura.executeQuery();
        }catch(SQLException ex){ //Manejo de excepciones en cualquier error dentro de la consulta
            throw new SQLException(ex);
        }
        return rs;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(consultarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(consultarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
