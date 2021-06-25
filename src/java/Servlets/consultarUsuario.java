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
@WebServlet(name = "consultarUsuario", urlPatterns = {"/consultarUsuario"})
public class consultarUsuario extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String nombre;
        int id;
        ResultSet rs = readUser(cn,correo,contrasena);
        
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
    
    public ResultSet readUser(Connection cn,String correo, String contrasena) throws SQLException{
        ResultSet rs;
        try{
            PreparedStatement lectura;    
            lectura = cn.prepareStatement("SELECT * FROM usuario WHERE correo=? AND contrasena=?");
            lectura.setString(1,correo);
            lectura.setString(2,contrasena);
            rs = lectura.executeQuery();
        }catch(SQLException ex){
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
