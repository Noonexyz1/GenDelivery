
package com.miempresa.algoritmo;


import com.miempresa.bean.ElectrodomesticoBean;
import com.miempresa.entidades.Electrodomestico;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AlgoritmoGenetico {
    
    public void algoritmoGenetico() {

        ElectrodomesticoBean electrodomesticoBean = new ElectrodomesticoBean();
        
        Poblacion poblacion = new Poblacion();
        poblacion.getPoblacion();

        Gen gen = new Gen();
            
        
        for (Electrodomestico electro : electrodomesticoBean.obtenerElectrodomesticos()) {
            gen.setElectrodomestico(electro);
            
        }
        
        
        List<Cromosoma> poblacionPrueba = poblacion.getPoblacion();

        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                System.out.print(valor + ",");
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                if (valor == 1) {
                    System.out.print(poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getNombre() + ", \t");
                } else {
                    System.out.print("      " + valor + ", \t");

                }
            }
            System.out.println();
        }

        for (int generacion = 0; generacion < 200; generacion++) {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX GENERACION NUMERO: " + (generacion + 1) + " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");

            System.out.println("-----------------------------------------------------------------------");
            /*Hacemos la penalizacion*/
            int capacidadMaxima = 1000;
            int sumatoriaDePesos = 0;
            int sumatoriaDeBeneficios = 0;
            int penalizacion = 0;
            int funcionFiteness = 0;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    if (valor == 1) {

                        sumatoriaDePesos = sumatoriaDePesos + poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getPeso();
                        sumatoriaDeBeneficios = sumatoriaDeBeneficios + poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getBeneficio();
                        if (sumatoriaDePesos > capacidadMaxima) {
                            /*hallar la penalizacion*/
                            penalizacion = sumatoriaDePesos - capacidadMaxima;

                        } else {
                            /*no hay penalizacion = 0*/
                            penalizacion = 0;
                        }

                    }

                    funcionFiteness = sumatoriaDeBeneficios - penalizacion;

                }

                poblacionPrueba.get(i).setFitness(funcionFiteness);
                sumatoriaDePesos = 0;
                sumatoriaDeBeneficios = 0;

            }

            System.out.println("-----------------------------------------------------------------------");
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    System.out.print(valor + ",");
                }
                System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
                System.out.println();
            }

            System.out.println("-----------------------------------------------------------------------");
            /*volvemos a signarle al mismo listadePolblacion la misma lista, pero esta vez ordenada*/
            poblacionPrueba = poblacionPrueba.stream().sorted(Comparator.comparingInt(Cromosoma::getFitness).reversed()).collect(Collectors.toList());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    System.out.print(valor + ",");
                }
                System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
                System.out.println();
            }

            /*Hacemos los cruzes con los mejores, osea los dos primero de la lisa de la poblacion*/
            System.out.println("-----------------------------------------------------------------------");
            Cromosoma mejor1 = poblacionPrueba.get(0);
            Cromosoma mejor2 = poblacionPrueba.get(1);

            int valor1 = 0;
            int valor2 = 0;
            int valor3 = 0;

            int valor4 = 0;
            int valor5 = 0;

            /*del mejor1 sacamos los tres valores*/
            valor1 = mejor1.getCromosoma().get(0).getValor();
            valor2 = mejor1.getCromosoma().get(1).getValor();
            valor3 = mejor1.getCromosoma().get(2).getValor();
            /*del mejor2 sacamos los dos ultimos valores*/
            valor4 = mejor2.getCromosoma().get(3).getValor();
            valor5 = mejor2.getCromosoma().get(4).getValor();

            /*Cruzados*/
            Cromosoma hijo1 = new Cromosoma();
            hijo1.getCromosoma().get(0).setValor(valor1);
            hijo1.getCromosoma().get(1).setValor(valor2);
            hijo1.getCromosoma().get(2).setValor(valor3);

            hijo1.getCromosoma().get(3).setValor(valor4);
            hijo1.getCromosoma().get(4).setValor(valor5);

            /*del mejor2 sacamos los tres valores*/
            valor1 = mejor2.getCromosoma().get(0).getValor();
            valor2 = mejor2.getCromosoma().get(1).getValor();
            valor3 = mejor2.getCromosoma().get(2).getValor();
            /*del mejor1 sacamos los dos ultimos valores*/
            valor4 = mejor1.getCromosoma().get(3).getValor();
            valor5 = mejor1.getCromosoma().get(4).getValor();

            Cromosoma hijo2 = new Cromosoma();
            hijo2.getCromosoma().get(0).setValor(valor1);
            hijo2.getCromosoma().get(1).setValor(valor2);
            hijo2.getCromosoma().get(2).setValor(valor3);

            hijo2.getCromosoma().get(3).setValor(valor4);
            hijo2.getCromosoma().get(4).setValor(valor5);

            /*Mostramos los dos nuevos hjos*/
            System.out.println("MEJORES CROMOSOMAS");
            mejor1.getCromosoma().stream().forEach(m1 -> System.out.print(m1.getValor() + ","));
            System.out.println();
            mejor2.getCromosoma().stream().forEach(m2 -> System.out.print(m2.getValor() + ","));

            System.out.println();
            System.out.println("LOS HIJOS");

            hijo1.getCromosoma().stream().forEach(h1 -> System.out.print(h1.getValor() + ","));
            System.out.println();
            hijo2.getCromosoma().stream().forEach(h2 -> System.out.print(h2.getValor() + ","));


            /*Ejemplo, mostrar los peores de la poblacion*/
            Cromosoma peor1 = poblacionPrueba.get(5);
            Cromosoma peor2 = poblacionPrueba.get(4);

            System.out.println();
            System.out.println("PEORES CROMOSOMAS");
            peor1.getCromosoma().stream().forEach(p1 -> System.out.print(p1.getValor() + ","));
            System.out.println();
            peor2.getCromosoma().stream().forEach(p2 -> System.out.print(p2.getValor() + ","));

            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("HIJOS INTRODUCIDOS");
            /*Solo me quueda reemplazar los hijos a los peores y mostrar*/
            poblacionPrueba.get(4).setCromosoma(hijo2.getCromosoma());
            poblacionPrueba.get(5).setCromosoma(hijo1.getCromosoma());
            /*Mostrar*/
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    System.out.print(valor + ",");
                }
                System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
                System.out.println();
            }

            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("MUTAMOS UN CROMOSOMA");
            Cromosoma cromo = poblacionPrueba.get((int) Math.random() * 6);
            int valorAleatorio = (int) (Math.random() * 5);
            System.out.println("VALOR ALEATORIO " + valorAleatorio);

            cromo.getCromosoma().set(valorAleatorio, new Gen());

            /*Mostrar*/
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                    System.out.print(valor + ",");
                }
                System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
                System.out.println();
            }

        }

        System.out.println("--------------------RESULTADO-------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                if (valor == 1) {
                    System.out.print(poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getNombre() + ", \t");
                } else {
                    System.out.print("      " + valor + ", \t");

                }
            }
            System.out.println();
        }

    }
    
}
