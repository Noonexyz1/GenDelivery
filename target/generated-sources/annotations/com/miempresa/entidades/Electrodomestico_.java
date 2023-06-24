package com.miempresa.entidades;

import com.miempresa.entidades.Carga;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-22T20:45:07", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Electrodomestico.class)
public class Electrodomestico_ { 

    public static volatile ListAttribute<Electrodomestico, Carga> cargaList;
    public static volatile SingularAttribute<Electrodomestico, String> tipo;
    public static volatile SingularAttribute<Electrodomestico, Integer> idElectrodomestico;
    public static volatile SingularAttribute<Electrodomestico, String> nombre;
    public static volatile SingularAttribute<Electrodomestico, BigInteger> pesoKg;

}