/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.entity;

import java.io.Serializable;
import java.util.List;
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
    , @NamedQuery(name = "Tpermiso.findByIdtpermiso", query = "SELECT t FROM Tpermiso t WHERE t.idtpermiso = :idtpermiso")
    , @NamedQuery(name = "Tpermiso.findByNombre", query = "SELECT t FROM Tpermiso t WHERE t.nombre = :nombre")})
public class Tpermiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtpermiso")
    private Integer idtpermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "trol_has_tpermiso", joinColumns = {
        @JoinColumn(name = "tpermiso_idtpermiso", referencedColumnName = "idtpermiso")}, inverseJoinColumns = {
        @JoinColumn(name = "trol_idtrol", referencedColumnName = "idtrol")})
    @ManyToMany
    private List<Trol> trolList;

    public Tpermiso() {
    }

    public Tpermiso(Integer idtpermiso) {
        this.idtpermiso = idtpermiso;
    }

    public Tpermiso(Integer idtpermiso, String nombre) {
        this.idtpermiso = idtpermiso;
        this.nombre = nombre;
    }

    public Integer getIdtpermiso() {
        return idtpermiso;
    }

    public void setIdtpermiso(Integer idtpermiso) {
        this.idtpermiso = idtpermiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Trol> getTrolList() {
        return trolList;
    }

    public void setTrolList(List<Trol> trolList) {
        this.trolList = trolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtpermiso != null ? idtpermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tpermiso)) {
            return false;
        }
        Tpermiso other = (Tpermiso) object;
        if ((this.idtpermiso == null && other.idtpermiso != null) || (this.idtpermiso != null && !this.idtpermiso.equals(other.idtpermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stonebank.entity.Tpermiso[ idtpermiso=" + idtpermiso + " ]";
    }
    
}
