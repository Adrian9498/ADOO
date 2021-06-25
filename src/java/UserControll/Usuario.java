/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserControll;

/**
 *
 * @author irda2
 */

//Se crea la clase de usuario que  sera el objeto principal que se utilizara en los servlets
public class Usuario {
    
    //Se crean sus atributos necesarios y principales para la creacion de objetos tipo Usuario
    public int id;
    private String nombre;
    private String correo;
    private String contrasena;
    
    
    //Los respectivos Getters and Setter para los atributos privados
    public String getNombre(){
        return this.nombre;
    }
    
    public String getCorreo(){
        return this.correo;
    }
    
    public String getContrasena(){
        return this.contrasena;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
   
}
