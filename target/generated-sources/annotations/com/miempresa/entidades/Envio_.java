package com.miempresa.entidades;

import com.miempresa.entidades.Camion;
import com.miempresa.entidades.EnvioElectrodomestico;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-25T15:25:20", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Envio.class)
public class Envio_ { 

    public static volatile SingularAttribute<Envio, Date> fechaEnvio;
    public static volatile SingularAttribute<Envio, Integer> idEnvio;
    public static volatile ListAttribute<Envio, EnvioElectrodomestico> envioElectrodomesticoList;
    public static volatile SingularAttribute<Envio, Camion> idCamion;

}