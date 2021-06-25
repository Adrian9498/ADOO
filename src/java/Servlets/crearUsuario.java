/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Services.SqlConnection;
import UserControll.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irda2
 */

/*Se crea la clase consultarUsuario siendo una de las clases que se utilizan para modificar la tabla usuarios de la BD
  Esta clase crea registros de dicha tabla
*/
@WebServlet(name = "crearUsuario", urlPatterns = {"/crearUsuario"})
public class crearUsuario extends HttpServlet {
    //Se crea la conexion y se define dentro del constructor para que en la llamada del objeto cree la conexion a MySQL
    Connection cn;
    
    public crearUsuario() throws SQLException, ClassNotFoundException {
        this.cn = SqlConnection.getSqlConnection();
    }
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //Lineas de codigo que se ejecutan en el request del Servlet
        response.setContentType("text/html;charset=UTF-8");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
         //Metodo definido para crear usuarios definido abajo
        createUser(cn,nombre,correo,contrasena);
        /*try (PrintWriter out = response.getWriter()) {
            /*TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet crearUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crearUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }
    //Metodo utilizado para crear usuarios de la DB
    public void createUser(Connection cn,String nombre,String correo,String contrasena) throws SQLException{
        Usuario us = new Usuario(); //Se crea objeto usuario
        us.setNombre(nombre);//Se agregan sus atributos
        us.setCorreo(correo);
        us.setContrasena(contrasena);
        try{
            
            PreparedStatement registro;
            registro = cn.prepareStatement("INSERT INTO usuario(nombre,correo,contrasena)VALUES(?,?,?)");
            registro.setString(1,us.getNombre());
            registro.setString(2,us.getCorreo());
            registro.setString(3, us.getContrasena());
            registro.executeUpdate();//Se ejecuta el Query
        }catch(SQLException ex){//Manejo de excepciones en cualquier error dentro de la consulta
            throw new SQLException(ex);
        }
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
            Logger.getLogger(crearUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(crearUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
