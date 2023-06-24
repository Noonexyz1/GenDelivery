/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author noone
 */
@Entity
@Table(name = "camion")
@NamedQueries({
    @NamedQuery(name = "Camion.findAll", query = "SELECT c FROM Camion c"),
    @NamedQuery(name = "Camion.findByIdCamion", query = "SELECT c FROM Camion c WHERE c.idCamion = :idCamion"),
    @NamedQuery(name = "Camion.findByCapacidadKg", query = "SELECT c FROM Camion c WHERE c.capacidadKg = :capacidadKg"),
    @NamedQuery(name = "Camion.findByModelo", query = "SELECT c FROM Camion c WHERE c.modelo = :modelo")})
public class Camion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_camion")
    private Integer idCamion;
    @Column(name = "capacidad_kg")
    private BigInteger capacidadKg;
    @Size(max = 255)
    @Column(name = "modelo")
    private String modelo;
    @OneToMany(mappedBy = "idCamion")
    private List<Carga> cargaList;

    public Camion() {
    }

    public Camion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public BigInteger getCapacidadKg() {
        return capacidadKg;
    }

    public void setCapacidadKg(BigInteger capacidadKg) {
        this.capacidadKg = capacidadKg;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<Carga> getCargaList() {
        return cargaList;
    }

    public void setCargaList(List<Carga> cargaList) {
        this.cargaList = cargaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCamion != null ? idCamion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Camion)) {
            return false;
        }
        Camion other = (Camion) object;
        if ((this.idCamion == null && other.idCamion != null) || (this.idCamion != null && !this.idCamion.equals(other.idCamion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miempresa.entidades.Camion[ idCamion=" + idCamion + " ]";
    }
    
}
