/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "trol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trol.findAll", query = "SELECT t FROM Trol t")
    , @NamedQuery(name = "Trol.findByIdtRol", query = "SELECT t FROM Trol t WHERE t.idtRol = :idtRol")
    , @NamedQuery(name = "Trol.findByNombre", query = "SELECT t FROM Trol t WHERE t.nombre = :nombre")})
public class Trol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtRol")
    private Integer idtRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "trolCollection")
    private Collection<Tpermiso> tpermisoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tRolidtRol")
    private Collection<Tusuario> tusuarioCollection;

    public Trol() {
    }

    public Trol(Integer idtRol) {
        this.idtRol = idtRol;
    }

    public Trol(Integer idtRol, String nombre) {
        this.idtRol = idtRol;
        this.nombre = nombre;
    }

    public Integer getIdtRol() {
        return idtRol;
    }

    public void setIdtRol(Integer idtRol) {
        this.idtRol = idtRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Tpermiso> getTpermisoCollection() {
        return tpermisoCollection;
    }

    public void setTpermisoCollection(Collection<Tpermiso> tpermisoCollection) {
        this.tpermisoCollection = tpermisoCollection;
    }

    @XmlTransient
    public Collection<Tusuario> getTusuarioCollection() {
        return tusuarioCollection;
    }

    public void setTusuarioCollection(Collection<Tusuario> tusuarioCollection) {
        this.tusuarioCollection = tusuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtRol != null ? idtRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trol)) {
            return false;
        }
        Trol other = (Trol) object;
        if ((this.idtRol == null && other.idtRol != null) || (this.idtRol != null && !this.idtRol.equals(other.idtRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stonebank.entity.Trol[ idtRol=" + idtRol + " ]";
    }
    
}
