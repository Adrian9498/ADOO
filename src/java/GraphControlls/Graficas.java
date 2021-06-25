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
public class Graficas {
    public int id;
    private String ecuacion;
    private String nombre;
    
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
