package com.miempresa.entidades;

import com.miempresa.entidades.Envio;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-25T17:44:22", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Camion.class)
public class Camion_ { 

    public static volatile SingularAttribute<Camion, BigDecimal> capacidadKg;
    public static volatile ListAttribute<Camion, Envio> envioList;
    public static volatile SingularAttribute<Camion, Integer> idCamion;
    public static volatile SingularAttribute<Camion, String> modelo;

}