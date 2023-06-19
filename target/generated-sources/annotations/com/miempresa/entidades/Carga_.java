package com.miempresa.entidades;

import com.miempresa.entidades.Camion;
import com.miempresa.entidades.Electrodomestico;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-19T17:51:12", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Carga.class)
public class Carga_ { 

    public static volatile SingularAttribute<Carga, Date> fecha;
    public static volatile SingularAttribute<Carga, Integer> idCarga;
    public static volatile SingularAttribute<Carga, Electrodomestico> idElectrodomestico;
    public static volatile SingularAttribute<Carga, Camion> idCamion;

}