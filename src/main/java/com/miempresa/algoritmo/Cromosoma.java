
package com.miempresa.algoritmo;

import java.util.ArrayList;
import java.util.List;

public class Cromosoma {
    private List<Gen> cromosoma;
    private int fitness;
    
    
    public Cromosoma(){
        cromosoma = new ArrayList<>();
        
        Gen g1 = new Gen();
        Gen g2 = new Gen();
        Gen g3 = new Gen();
        Gen g4 = new Gen();
        Gen g5 = new Gen();
        
        Producto prod1 = new Producto("    Pan", 200, 10);
        Producto prod2 = new Producto(" Lentes", 100, 40);
        Producto prod3 = new Producto("Botella", 500, 20);
        Producto prod4 = new Producto("  Libro", 700, 30);
        Producto prod5 = new Producto("Celular", 100, 50);
        
        /*g1.setProducto(prod1);
        g2.setProducto(prod2);
        g3.setProducto(prod3);
        g4.setProducto(prod4);
        g5.setProducto(prod5);*/
        
        
        /*5 cromosomas*/
        cromosoma.add(g1);
        cromosoma.add(g2);
        cromosoma.add(g3);
        cromosoma.add(g4);
        cromosoma.add(g5);
        
    }

    public List<Gen> getCromosoma() {
        return cromosoma;
    }

    public void setCromosoma(List<Gen> cromosoma) {
        this.cromosoma = cromosoma;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
        
    
    
}
