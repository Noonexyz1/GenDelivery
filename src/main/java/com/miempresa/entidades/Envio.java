/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author noone
 */
@Entity
@Table(name = "envio")
@NamedQueries({
    @NamedQuery(name = "Envio.findAll", query = "SELECT e FROM Envio e"),
    @NamedQuery(name = "Envio.findByIdEnvio", query = "SELECT e FROM Envio e WHERE e.idEnvio = :idEnvio"),
    @NamedQuery(name = "Envio.findByFechaEnvio", query = "SELECT e FROM Envio e WHERE e.fechaEnvio = :fechaEnvio")})
public class Envio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_envio")
    private Integer idEnvio;
    @Column(name = "fecha_envio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @JoinColumn(name = "id_camion", referencedColumnName = "id_camion")
    @ManyToOne
    private Camion idCamion;
    @OneToMany(mappedBy = "idEnvio")
    private List<EnvioElectrodomestico> envioElectrodomesticoList;

    public Envio() {
    }

    public Envio(Integer idEnvio) {
        this.idEnvio = idEnvio;
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

    public Camion getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Camion idCamion) {
        this.idCamion = idCamion;
    }

    public List<EnvioElectrodomestico> getEnvioElectrodomesticoList() {
        return envioElectrodomesticoList;
    }

    public void setEnvioElectrodomesticoList(List<EnvioElectrodomestico> envioElectrodomesticoList) {
        this.envioElectrodomesticoList = envioElectrodomesticoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnvio != null ? idEnvio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Envio)) {
            return false;
        }
        Envio other = (Envio) object;
        if ((this.idEnvio == null && other.idEnvio != null) || (this.idEnvio != null && !this.idEnvio.equals(other.idEnvio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miempresa.entidades.Envio[ idEnvio=" + idEnvio + " ]";
    }
    
}
