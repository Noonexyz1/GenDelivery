
package com.miempresa.bean;

import com.miempresa.entidades.ModeloReporteEnvio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ReporteBean {

    private EntityManager entityManager;
   
    
    public ReporteBean(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenDelivery");
        entityManager = emf.createEntityManager();
    }
    
    
    public List<ModeloReporteEnvio> obtenerReporte() {
        List<ModeloReporteEnvio> resultados = new ArrayList<>();

        String sql = "SELECT camion.modelo, camion.capacidad_kg, envio.id_envio, envio.fecha_envio, electrodomestico.nombre, electrodomestico.peso_kg, electrodomestico.beneficio "
                + "FROM camion, envio, envio_electrodomestico, electrodomestico "
                + "WHERE camion.id_camion = envio.id_camion "
                + "AND envio.id_envio = envio_electrodomestico.id_envio "
                + "AND electrodomestico.id_electrodomestico = envio_electrodomestico.id_electrodomestico";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_transporte", "root", ""); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String modeloCamion = resultSet.getString("modelo");
                int capacidadKg = resultSet.getInt("capacidad_kg");
                int idEnvio = resultSet.getInt("id_envio");
                Date fechaEnvio = resultSet.getDate("fecha_envio");
                String nombreElectrodomestico = resultSet.getString("nombre");
                double pesoKg = resultSet.getDouble("peso_kg");
                double beneficio = resultSet.getDouble("beneficio");

                ModeloReporteEnvio modeloReporteEnvio = new ModeloReporteEnvio(modeloCamion, capacidadKg, idEnvio, fechaEnvio, nombreElectrodomestico, pesoKg, beneficio);
                resultados.add(modeloReporteEnvio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }


    Object traerEnvio(int parseInt) {
        return  null;
    }

    void eliminarEnvio(int parseInt) {
        
    }
    
}
