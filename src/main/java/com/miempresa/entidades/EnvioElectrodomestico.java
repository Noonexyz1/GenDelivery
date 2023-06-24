/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author noone
 */
@Entity
@Table(name = "envio_electrodomestico")
@NamedQueries({
    @NamedQuery(name = "EnvioElectrodomestico.findAll", query = "SELECT e FROM EnvioElectrodomestico e"),
    @NamedQuery(name = "EnvioElectrodomestico.findByIdEnvioElectrodomestico", query = "SELECT e FROM EnvioElectrodomestico e WHERE e.idEnvioElectrodomestico = :idEnvioElectrodomestico")})
public class EnvioElectrodomestico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_envio_electrodomestico")
    private Integer idEnvioElectrodomestico;
    @JoinColumn(name = "id_envio", referencedColumnName = "id_envio")
    @ManyToOne
    private Envio idEnvio;
    @JoinColumn(name = "id_electrodomestico", referencedColumnName = "id_electrodomestico")
    @ManyToOne
    private Electrodomestico idElectrodomestico;

    public EnvioElectrodomestico() {
    }

    public EnvioElectrodomestico(Integer idEnvioElectrodomestico) {
        this.idEnvioElectrodomestico = idEnvioElectrodomestico;
    }

    public Integer getIdEnvioElectrodomestico() {
        return idEnvioElectrodomestico;
    }

    public void setIdEnvioElectrodomestico(Integer idEnvioElectrodomestico) {
        this.idEnvioElectrodomestico = idEnvioElectrodomestico;
    }

    public Envio getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Envio idEnvio) {
        this.idEnvio = idEnvio;
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
        hash += (idEnvioElectrodomestico != null ? idEnvioElectrodomestico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnvioElectrodomestico)) {
            return false;
        }
        EnvioElectrodomestico other = (EnvioElectrodomestico) object;
        if ((this.idEnvioElectrodomestico == null && other.idEnvioElectrodomestico != null) || (this.idEnvioElectrodomestico != null && !this.idEnvioElectrodomestico.equals(other.idEnvioElectrodomestico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miempresa.entidades.EnvioElectrodomestico[ idEnvioElectrodomestico=" + idEnvioElectrodomestico + " ]";
    }
    
}
