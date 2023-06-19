/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author noone
 */
@Entity
@Table(name = "carga")
@NamedQueries({
    @NamedQuery(name = "Carga.findAll", query = "SELECT c FROM Carga c"),
    @NamedQuery(name = "Carga.findByIdCarga", query = "SELECT c FROM Carga c WHERE c.idCarga = :idCarga"),
    @NamedQuery(name = "Carga.findByFecha", query = "SELECT c FROM Carga c WHERE c.fecha = :fecha")})
public class Carga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_carga")
    private Integer idCarga;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_camion", referencedColumnName = "id_camion")
    @ManyToOne
    private Camion idCamion;
    @JoinColumn(name = "id_electrodomestico", referencedColumnName = "id_electrodomestico")
    @ManyToOne
    private Electrodomestico idElectrodomestico;

    public Carga() {
    }

    public Carga(Integer idCarga) {
        this.idCarga = idCarga;
    }

    public Integer getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(Integer idCarga) {
        this.idCarga = idCarga;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Camion getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Camion idCamion) {
        this.idCamion = idCamion;
    }

    public Electrodomestico getIdElectrodomestico() {
        return idElectrodomestico;
    }

    public void setIdElectrodomestico(Electrodomestico idElectrodomestico) {
        this.idElectrodomestico = idElectrodomestico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarga != null ? idCarga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carga)) {
            return false;
        }
        Carga other = (Carga) object;
        if ((this.idCarga == null && other.idCarga != null) || (this.idCarga != null && !this.idCarga.equals(other.idCarga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miempresa.entidades.Carga[ idCarga=" + idCarga + " ]";
    }
    
}
