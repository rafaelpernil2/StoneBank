/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rafaelpernil
 */
@Entity
@Table(name = "tpermiso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tpermiso.findAll", query = "SELECT t FROM Tpermiso t")
    , @NamedQuery(name = "Tpermiso.findByIdtPermiso", query = "SELECT t FROM Tpermiso t WHERE t.idtPermiso = :idtPermiso")
    , @NamedQuery(name = "Tpermiso.findByNombre", query = "SELECT t FROM Tpermiso t WHERE t.nombre = :nombre")})
public class Tpermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtPermiso")
    private Integer idtPermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "trol_has_tpermiso", joinColumns = {
        @JoinColumn(name = "tPermiso_idtPermiso", referencedColumnName = "idtPermiso")}, inverseJoinColumns = {
        @JoinColumn(name = "tRol_idtRol", referencedColumnName = "idtRol")})
    @ManyToMany
    private Collection<Trol> trolCollection;

    public Tpermiso() {
    }

    public Tpermiso(Integer idtPermiso) {
        this.idtPermiso = idtPermiso;
    }

    public Tpermiso(Integer idtPermiso, String nombre) {
        this.idtPermiso = idtPermiso;
        this.nombre = nombre;
    }

    public Integer getIdtPermiso() {
        return idtPermiso;
    }

    public void setIdtPermiso(Integer idtPermiso) {
        this.idtPermiso = idtPermiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Trol> getTrolCollection() {
        return trolCollection;
    }

    public void setTrolCollection(Collection<Trol> trolCollection) {
        this.trolCollection = trolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtPermiso != null ? idtPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpermiso)) {
            return false;
        }
        Tpermiso other = (Tpermiso) object;
        if ((this.idtPermiso == null && other.idtPermiso != null) || (this.idtPermiso != null && !this.idtPermiso.equals(other.idtPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stonebank.entity.Tpermiso[ idtPermiso=" + idtPermiso + " ]";
    }
    
}
