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

/*Se crea la clase Slope Y que es una clase heredada de Graficas asi que cuenta con sus atributos pero esta disenada para
 las graficas de tipo y = mx+b   
*/
public class SlopeY extends Graficas{
    //Se crean sus atributos necesarios y principales para la creacion de objetos tipo Slope Y
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
    
    public void setB(){
        this.b=b;
    }
}
