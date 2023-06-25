
package com.miempresa.entidades;

import java.math.BigDecimal;
import java.util.Date;

public class ModeloReporteEnvio {
    
    private String modelo;
    private int capacidadKg;
    
    private Integer idEnvio;
    private Date fechaEnvio;
    
    private String nombre;
    private double pesoKg;
    private double beneficio;

    public ModeloReporteEnvio(){
        
    }
    
    public ModeloReporteEnvio(String modelo, int capacidadKg, int idEnvio, Date fechaEnvio, String nombre, double pesoKg, double beneficio) {
        this.modelo = modelo;
        this.capacidadKg = capacidadKg;
        this.idEnvio = idEnvio;
        this.fechaEnvio = fechaEnvio;
        this.nombre = nombre;
        this.pesoKg = pesoKg;
        this.beneficio = beneficio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidadKg() {
        return capacidadKg;
    }

    public void setCapacidadKg(int capacidadKg) {
        this.capacidadKg = capacidadKg;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public double getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }

    
    
    
    
    
}
