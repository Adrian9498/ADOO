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
/*Se crea la clase Entrada que es una clase heredada de Graficas asi que cuenta con sus atributos pero esta disenada para
 las graficas de tipo mx+by = 0   
*/
public class Estandar extends Graficas{
    //Se crean sus atributos necesarios y principales para la creacion de objetos tipo Entrada
    private float m;
    private float b;
    
    //Los respectivos Getters and Setter para los atributos privados
    public float getM(){
        return this.m;
    }
    
    public float getB(){
        return this.b;
    }
    
    public void setM(float m){
        this.m = m;
    }
    
    public void setB(float b){
        this.b=b;
    }
}
