/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphControlls;

/**
 *
 * @author irda2
 */

//Se crea la clase de graficas que  sera el objeto principal que se utilizara en los servlets 
public class Graficas {
    
    //Se crean sus atributos necesarios y principales para la creacion de objetos tipo Graficas
    public int id;
    private String ecuacion;
    private String nombre;
    
    //Los respectivos Getters and Setter para los atributos privados
    public String getEcuacion(){
        return this.ecuacion;
    }
    
    public String getNombre(){
        return this.nombre;
     }
    
    public void setEcuacion(String ecuacion){
        this.ecuacion=ecuacion;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }   
}
