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
import GraphControlls.*;
import Services.SqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author irda2
 */
@WebServlet(name = "crearGrafica", urlPatterns = {"/crearGrafica"})
public class crearGrafica extends HttpServlet {
    Connection cn;
    int tipo;
    public crearGrafica() throws SQLException, ClassNotFoundException {
        this.cn = SqlConnection.getSqlConnection();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String ecuacion;
        String nombre = request.getParameter("nombre");
        float m = Float.parseFloat(request.getParameter("m"));
        float b = Float.parseFloat(request.getParameter("b"));
        tipo = Integer.parseInt(request.getParameter("tipo"));
        if(tipo == 1){
            SlopeY sy = new SlopeY();
            sy.setM(m);
            sy.setB(b);
            ecuacion = "y="+sy.getM()+"x+"+sy.getB();
            sy.setEcuacion(ecuacion);
            sy.setNombre(nombre);
            createGraph(cn,sy);
        }else{
            Estandar es = new Estandar();
            es.setM(m);
            es.setB(b);
            ecuacion = es.getM()+"x+"+es.getB();
            es.setEcuacion(ecuacion);
            es.setNombre(nombre);
            createGraph(cn,es);
        }
        
        /*try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet crearGrafica</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crearGrafica at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }
    
    public void createGraph(Connection cn,SlopeY sy) throws SQLException{
        try{
            PreparedStatement registro;
            registro = cn.prepareStatement("INSERT INTO graficas(ecuacion,nombre,m,b)VALUES(?,?,?,?)");
            registro.setString(1, sy.getEcuacion());
            registro.setString(2, sy.getNombre());
            registro.setString(3,String.valueOf(sy.getM()));
            registro.setString(4,String.valueOf(sy.getB()));
            registro.executeUpdate();
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
    }
    
    public void createGraph(Connection cn,Estandar sy) throws SQLException{
        try{
            PreparedStatement registro;
            registro = cn.prepareStatement("INSERT INTO graficas(ecuacion,nombre,m,b)VALUES(?,?,?,?)");
            registro.setString(1, sy.getEcuacion());
            registro.setString(2, sy.getNombre());
            registro.setString(3,String.valueOf(sy.getM()));
            registro.setString(4,String.valueOf(sy.getB()));
            registro.executeUpdate();
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
            Logger.getLogger(crearGrafica.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(crearGrafica.class.getName()).log(Level.SEVERE, null, ex);
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
