/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ligoh
 */
//clase padre
public class Movimientos {
    private String descripcion;
    private float cantidad;
    private String fecha;
    
    public Movimientos(String descripcion, float cantidad, String fecha){
        
        //hacemos las validaciones de que no este vacia 
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        //validamos de que no haya numeros negativos
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        //validamos de que la fecha no este vacia
        if (fecha == null || fecha.isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        //inicializamos
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fecha = fecha;   
    }
    //Getters
    public String getDescripcion(){
        return descripcion;
    }
    
    public float getCantidad(){
        return cantidad;
    }
    
    public String getFecha(){
        return fecha;
    }
    //Setters
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public void setCantidad(float cantidad){
        this.cantidad = cantidad;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
}
