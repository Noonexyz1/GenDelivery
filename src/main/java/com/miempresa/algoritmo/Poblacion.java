
package com.miempresa.algoritmo;

import java.util.ArrayList;
import java.util.List;

public class Poblacion {
    private List<Cromosoma> poblacion;
    
    public Poblacion(){
        poblacion = new ArrayList<>();
        /*cantidad de la poblacion 8 */
        poblacion.add(new Cromosoma());
        poblacion.add(new Cromosoma());
        poblacion.add(new Cromosoma());
        poblacion.add(new Cromosoma());
        poblacion.add(new Cromosoma());
        poblacion.add(new Cromosoma());
        
    }

    public List<Cromosoma> getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(List<Cromosoma> poblacion) {
        this.poblacion = poblacion;
    }
    
    
    
    
}
