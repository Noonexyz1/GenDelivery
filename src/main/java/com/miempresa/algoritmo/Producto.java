
package com.miempresa.algoritmo;

public class Producto {
    private String nombre;
    private int peso;
    private int beneficio;

    public Producto(String nombre, int peso, int beneficio) {
        this.nombre = nombre;
        this.peso = peso;
        this.beneficio = beneficio;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(int beneficio) {
        this.beneficio = beneficio;
    }
    
            
            
}
