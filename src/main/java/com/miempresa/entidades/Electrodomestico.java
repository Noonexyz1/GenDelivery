/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.miempresa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "electrodomestico")
@NamedQueries({
    @NamedQuery(name = "Electrodomestico.findAll", query = "SELECT e FROM Electrodomestico e"),
    @NamedQuery(name = "Electrodomestico.findByIdElectrodomestico", query = "SELECT e FROM Electrodomestico e WHERE e.idElectrodomestico = :idElectrodomestico"),
    @NamedQuery(name = "Electrodomestico.findByNombre", query = "SELECT e FROM Electrodomestico e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Electrodomestico.findByPesoKg", query = "SELECT e FROM Electrodomestico e WHERE e.pesoKg = :pesoKg"),
    @NamedQuery(name = "Electrodomestico.findByTipo", query = "SELECT e FROM Electrodomestico e WHERE e.tipo = :tipo")})
public class Electrodomestico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_electrodomestico")
    private Integer idElectrodomestico;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso_kg")
    private BigDecimal pesoKg;
    @Size(max = 50)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(mappedBy = "idElectrodomestico")
    private List<Carga> cargaList;

    public Electrodomestico() {
    }

    public Electrodomestico(Integer idElectrodomestico) {
        this.idElectrodomestico = idElectrodomestico;
    }

    public Integer getIdElectrodomestico() {
        return idElectrodomestico;
    }

    public void setIdElectrodomestico(Integer idElectrodomestico) {
        this.idElectrodomestico = idElectrodomestico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (idElectrodomestico != null ? idElectrodomestico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Electrodomestico)) {
            return false;
        }
        Electrodomestico other = (Electrodomestico) object;
        if ((this.idElectrodomestico == null && other.idElectrodomestico != null) || (this.idElectrodomestico != null && !this.idElectrodomestico.equals(other.idElectrodomestico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miempresa.entidades.Electrodomestico[ idElectrodomestico=" + idElectrodomestico + " ]";
    }
    
}
