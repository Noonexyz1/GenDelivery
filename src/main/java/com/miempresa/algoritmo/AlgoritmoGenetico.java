package com.miempresa.algoritmo;

import com.miempresa.bean.ElectrodomesticoBean;
import com.miempresa.entidades.Electrodomestico;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlgoritmoGenetico {

    public List<Electrodomestico> algoritmoGenetico(int capacidadMaximaCamion) {

        
        ElectrodomesticoBean electrodomesticoBean = new ElectrodomesticoBean();
        Poblacion poblacion = new Poblacion();
        List<Electrodomestico> listaElectrodomesticos = electrodomesticoBean.obtenerElectrodomesticos();
        
        
        /*Function<Electrodomestico, Gen> funcion1_1 = (electro) -> {
            Gen gen = new Gen();
            gen.setElectrodomestico(electro);
            return gen;
        };*/
        
        IntFunction< List<Gen> > funcion1 = i -> 
                listaElectrodomesticos.stream()
                    .map(electro -> {
                            Gen gen = new Gen();
                            gen.setElectrodomestico(electro);
                            return gen;
                    }).peek(ic -> 
                            System.out.println("paso 1.1: valor = " + ic.getValor() + " - NombreProducto = " + ic.getProducto().getNombre())
                    )
                .collect(Collectors.toList());
        
        Function<List<Gen>, Cromosoma> funcion2 = genes -> {
            Cromosoma cromosoma = new Cromosoma();
            cromosoma.setCromosoma(genes);
            return cromosoma;
        };
        
        List<Cromosoma> poblacionCromo = 
            IntStream
                    //.range(0, 6) HAce que ejecute todas los otros mas, 6 veces, como si fuera un for
                .range(0, 6).peek(ic -> 
                            System.out.println("IntStream Terminado: " + ic))
                    .mapToObj(funcion1).peek(ic -> 
                            System.out.println("mapToObj Terminado: " + ic.toString()))
                    .map(funcion2).peek(ic -> 
                            System.out.println("map Terminado: " + ic.toString()))
            .collect(Collectors.toList());
            
        //puedo comvertir cualquier tipo en un flujo
        poblacion.setPoblacion(poblacionCromo);

        
        
        
        /*se supone que hasta aqui ya tengo toda la poblacion con los cromosomas dentro y lo genes tambien*/
        List<Cromosoma> poblacionPrueba = poblacion.getPoblacion();
        int tamCromosoma = poblacionPrueba.get(0).getCromosoma().size();

        /*Mostrar poblacion binaria*/
        mostrarPoblacionBinario(poblacionPrueba, tamCromosoma);

        /*Mostramos los valores 0 y en lugar de 1, el nombre del producto*/
        mostrarPoblacionBinarioNombres(poblacionPrueba, tamCromosoma);

        /*Se ejecuta el algoritmo genetico*/
        metodoAlgoritnoGenetico(poblacionPrueba, tamCromosoma, capacidadMaximaCamion);

        /*Mostramos el resultado del algoritmo genetico*/
        System.out.println("--------------------RESULTADO-------------------------------");
        mostrarPoblacionBinarioNombres(poblacionPrueba, tamCromosoma);

        /*Extraccion de los datos a enviar*/
        //listaElecEnvio.stream().forEach(a -> System.out.print(a.getNombre()));
        return extraerDatosDeLaRespuesta(poblacionPrueba, tamCromosoma);
        /*HASTA AQUI FUNCIONA*/

    }

    public void mostrarPoblacionBinario(List<Cromosoma> poblacionPrueba, int tamCromosoma) {
        System.out.println("-----------------------------------------------------------------------");
        /*se muestra toda la poblacion con las activaciones de 1 o 0*/
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < tamCromosoma; j++) {
                int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                System.out.print(valor + ",");
            }
            System.out.println();
        }
    }

    private void mostrarPoblacionBinarioNombres(List<Cromosoma> poblacionPrueba, int tamCromosoma) {
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < tamCromosoma; j++) {
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

    private void metodoAlgoritnoGenetico(List<Cromosoma> poblacionPrueba, int tamCromosoma, int capacidadMaximaCamion) {
        /*aqui empieza el ALGORITMO GENETICO*/
        /*DEBERIA HACER UNA IMPLEMENTACION DONDE las iteraciones se detengan cuando todos los fitness sean iguales*/
        /*PORQUE 200 tambien parece demacido, la poblacion se atrofia o se matan entre si, o mata la solucion que se dio en la generacion 50 por ejemplo*/
        for (int generacion = 0; generacion < 25; generacion++) {

            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX GENERACION NUMERO: " + (generacion + 1) + " XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
            System.out.println("----------------PESO " + capacidadMaximaCamion + "-------------------------------------------------------");
            /*Hacemos la penalizacion*/
            int capacidadMaxima = capacidadMaximaCamion;
            int sumatoriaDePesos = 0;
            int sumatoriaDeBeneficios = 0;
            int penalizacion = 0;
            int funcionFiteness = 0;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < tamCromosoma; j++) {
                    int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();

                    if (valor == 1) {

                        sumatoriaDePesos = sumatoriaDePesos + poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getPesoKg().intValue();
                        sumatoriaDeBeneficios = sumatoriaDeBeneficios + poblacionPrueba.get(i).getCromosoma().get(j).getProducto().getBeneficio();

                        if (sumatoriaDePesos > capacidadMaxima) {
                            /*hallar la penalizacion*/
                            penalizacion = sumatoriaDePesos - capacidadMaxima;

                        } else {
                            /*no hay penalizacion = 0*/
                            penalizacion = 0;
                        }
                    }
                }

                funcionFiteness = sumatoriaDeBeneficios - penalizacion;

                poblacionPrueba.get(i).setFitness(funcionFiteness);
                sumatoriaDePesos = 0;
                sumatoriaDeBeneficios = 0;

            }

            /*mostramos el fitness*/
            mostrarFitnes(poblacionPrueba, tamCromosoma);
            

            /*Ordenamos la poblacion*/
            poblacionPrueba = poblacionPrueba.stream().sorted(Comparator.comparingInt(Cromosoma::getFitness).reversed()).collect(Collectors.toList());
            
            /*mostramos el fitness*/
            mostrarFitnes(poblacionPrueba, tamCromosoma);
            
            /*Cruzamientos*/
            cruzamiento(poblacionPrueba, tamCromosoma);

            
            
            System.out.println();
            System.out.println("-----------------------------------------------------------------------");
            if (generacion != 24) {
                System.out.println("MUTAMOS UN CROMOSOMA");

                int valorAletorioPoblacion = (int) (Math.random() * 6);
                int valorAleatorioCromosoma = (int) (Math.random() * tamCromosoma);
                int valorBinarioAleatorio = (Math.random() * 100 > 50) ? 1 : 0;

                System.out.println("Valor Aleatorio poblacion:" + valorAletorioPoblacion);
                System.out.println("Valor Aleatorio Cromosoma:" + valorAleatorioCromosoma);
                System.out.println("Valor Aleatorio Binario:" + valorBinarioAleatorio);

                /*ESTO NO FUNCIONA PORQUE AL MOMENTO DE MUTAR UN GEN, MUTA a TODOS DE LA COLUMNA DEL GEN*/
                /*SI HAGO ESTO, TODA LA LISTA SE MODIFICA CON EL VALOR DE LAS VARIABLES DECLARADOAS*/
                //poblacionPrueba.get(valorAletorioPoblacion).getCromosoma().get(valorAleatorioCromosoma).setValor(valorBinarioAleatorio);
                /*PARA CAMBIAR SOLAMENTE UN VAlo, DEBO DE IR SACANDO PASO POR PASO LOS OBJETOS*/
                //Hagamos la prueba trayendo el objeto de los valores aleatorios
                Gen genOriginal = poblacionPrueba.get(valorAletorioPoblacion).getCromosoma().get(valorAleatorioCromosoma);
                Gen genMutado = new Gen();
                genMutado.setElectrodomestico(genOriginal.getProducto());
                genMutado.setValor(valorBinarioAleatorio);
                poblacionPrueba.get(valorAletorioPoblacion).getCromosoma().set(valorAleatorioCromosoma, genMutado);

                /*TENGO INSERTAR CON EL VALOR CAMBIADO JUNTO CON EL OBJETO ELECTRODOMOESTICO ASOCIADO*/
                System.out.println("-----------------------------------------------------------------------");

                /*Mostrar*/
                
                mostrarFitnes(poblacionPrueba, tamCromosoma);

            }

        }
        
    }

    private List<Electrodomestico> extraerDatosDeLaRespuesta(List<Cromosoma> poblacionPrueba, int tamCromosoma) {
        List<Electrodomestico> listaElecEnvio = new ArrayList<>();

        for (int i = 0; i < tamCromosoma; i++) {
            if (poblacionPrueba.get(1).getCromosoma().get(i).getValor() == 1) {

                Electrodomestico electrodomestico = poblacionPrueba.get(1).getCromosoma().get(i).getProducto();
                listaElecEnvio.add(electrodomestico);

            }

        }

        //listaElecEnvio.stream().forEach(a -> System.out.print(a.getNombre()));
        return listaElecEnvio;
        /*HASTA AQUI FUNCIONA*/
    }

    private void mostrarFitnes(List<Cromosoma> poblacionPrueba, int tamCromosoma) {
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < tamCromosoma; j++) {
                int valor = poblacionPrueba.get(i).getCromosoma().get(j).getValor();
                System.out.print(valor + ",");
            }
            System.out.print(" Fit = " + poblacionPrueba.get(i).getFitness() + "\tz");
            System.out.println();
        }
    }

    private void cruzamiento(List<Cromosoma> poblacionPrueba, int tamCromosoma) {
        /*Hacemos los cruzes con los mejores, osea los dos primero de la lisa de la poblacion*/
        System.out.println("-----------------------------------------------------------------------");
        Cromosoma mejor1 = poblacionPrueba.get(0);
        Cromosoma mejor2 = poblacionPrueba.get(1);

        /*Mostramos los dos mejores de la poblacion*/
        System.out.println("MEJORES CROMOSOMAS");
        mejor1.getCromosoma().stream().forEach(m1 -> System.out.print(m1.getValor() + ","));
        System.out.println();
        mejor2.getCromosoma().stream().forEach(m2 -> System.out.print(m2.getValor() + ","));

        /*Ejemplo, mostrar los peores de la poblacion*/
        Cromosoma peor1 = poblacionPrueba.get(5);
        Cromosoma peor2 = poblacionPrueba.get(4);

        System.out.println();
        System.out.println("PEORES CROMOSOMAS");
        peor1.getCromosoma().stream().forEach(p1 -> System.out.print(p1.getValor() + ","));
        System.out.println();
        peor2.getCromosoma().stream().forEach(p2 -> System.out.print(p2.getValor() + ","));

        /* aqui debo hacer los cruzes*/
        Cromosoma hijo1 = new Cromosoma();
        Cromosoma hijo2 = new Cromosoma();

        int mitad = tamCromosoma / 2;

        List<Gen> genes = new ArrayList<>();
        for (int i = 0; i < tamCromosoma; i++) {
            if (i < mitad) {
                genes.add(mejor1.getCromosoma().get(i));
            } else {
                genes.add(mejor2.getCromosoma().get(i));
            }

        }
        hijo1.setCromosoma(genes);

        /*Para el hijo 2 faltaria otro for*/
        List<Gen> genes1 = new ArrayList<>();
        for (int i = 0; i < tamCromosoma; i++) {
            if (i < mitad) {
                genes1.add(mejor2.getCromosoma().get(i));
            } else {
                genes1.add(mejor1.getCromosoma().get(i));
            }

        }
        hijo2.setCromosoma(genes1);

        /*falta probar hasta aqui*/
        System.out.println();
        System.out.println("Hijos CROMOSOMAS");
        hijo1.getCromosoma().stream().forEach(p1 -> System.out.print(p1.getValor() + ","));
        System.out.println();
        hijo2.getCromosoma().stream().forEach(p2 -> System.out.print(p2.getValor() + ","));

        System.out.println();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("HIJOS INTRODUCIDOS");
        /*Solo me quueda reemplazar los hijos a los peores y mostrar*/
        poblacionPrueba.get(4).setCromosoma(hijo2.getCromosoma());
        poblacionPrueba.get(5).setCromosoma(hijo1.getCromosoma());

        /*ME PARECE TAMBIEN QUE NO ESTOY HACIENDO LA INSERCION DE LOS HIJOS DE MANERA ADECUADA*/
        /*Mostrar*/
        mostrarFitnes(poblacionPrueba, tamCromosoma);
        
    }

}
