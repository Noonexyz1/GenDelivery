
package com.miempresa.algoritmo;

import com.miempresa.entidades.Electrodomestico;

public class Gen {
    private int valor;
    private Electrodomestico producto;
    
    public Gen(){
        valor = (Math.random() * 100 > 50)? 1:0;
        
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Electrodomestico getProducto() {
        return producto;
    }

    public void setElectrodomestico(Electrodomestico producto) {
        this.producto = producto;
    }
    
    
        
}
