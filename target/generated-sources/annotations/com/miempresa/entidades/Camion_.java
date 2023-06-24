package com.miempresa.entidades;

import com.miempresa.entidades.Carga;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-24T17:04:11", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Camion.class)
public class Camion_ { 

    public static volatile ListAttribute<Camion, Carga> cargaList;
    public static volatile SingularAttribute<Camion, BigInteger> capacidadKg;
    public static volatile SingularAttribute<Camion, Integer> idCamion;
    public static volatile SingularAttribute<Camion, String> modelo;

}