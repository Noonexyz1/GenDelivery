package com.miempresa.entidades;

import com.miempresa.entidades.EnvioElectrodomestico;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-25T20:35:06", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Electrodomestico.class)
public class Electrodomestico_ { 

    public static volatile SingularAttribute<Electrodomestico, Integer> beneficio;
    public static volatile SingularAttribute<Electrodomestico, Integer> idElectrodomestico;
    public static volatile ListAttribute<Electrodomestico, EnvioElectrodomestico> envioElectrodomesticoList;
    public static volatile SingularAttribute<Electrodomestico, String> nombre;
    public static volatile SingularAttribute<Electrodomestico, BigDecimal> pesoKg;

}