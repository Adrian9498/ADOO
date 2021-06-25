/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Services.SqlConnection;
import UserControll.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
@WebServlet(name = "updateUsuario", urlPatterns = {"/updateUsuario"})
public class updateUsuario extends HttpServlet {
Connection cn;
    
    public updateUsuario() throws SQLException, ClassNotFoundException {
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
        String nombreN = request.getParameter("nombreN");
        String correoN = request.getParameter("correoN");
        String contrasenaN = request.getParameter("contrasenaN");
        
        updateUser(cn,nombreN,correoN,contrasenaN,correo,contrasena);
        /*try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }
    
    public void updateUser(Connection cn,String nombreN,String correoN,String contrasenaN,String correo,String contrasena) throws SQLException{
        Usuario us = new Usuario();
        us.setNombre(nombreN);
        us.setCorreo(correoN);
        us.setContrasena(contrasenaN);
        
        try{
            PreparedStatement actualizacion;
            actualizacion = cn.prepareStatement("UPDATE usuario SET nombre=?, correo=?,contrasena=? WHERE correo=? AND contrasena=?");
            actualizacion.setString(1,us.getNombre());
            actualizacion.setString(2,us.getCorreo());
            actualizacion.setString(3,us.getContrasena());
            actualizacion.setString(4,correo);
            actualizacion.setString(5,contrasena);
            actualizacion.executeUpdate();
        }catch(SQLException ex){
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
        Logger.getLogger(updateUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(updateUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
