
package com.miempresa.algoritmo;

public class Gen {
    private int valor;
    private Producto producto;
    
    public Gen(){
        valor = (Math.random() * 100 > 50)? 1:0;
        producto = new Producto("Sin Nombre", 0, 0);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
        
}
