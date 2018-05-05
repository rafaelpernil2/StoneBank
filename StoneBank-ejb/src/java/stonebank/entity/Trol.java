/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
    , @NamedQuery(name = "Trol.findByIdtrol", query = "SELECT t FROM Trol t WHERE t.idtrol = :idtrol")
    , @NamedQuery(name = "Trol.findByNombre", query = "SELECT t FROM Trol t WHERE t.nombre = :nombre")})

@Cacheable(false)
public class Trol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtrol")
    private Integer idtrol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "trolList")
    private List<Tpermiso> tpermisoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trolIdtrol")
    private List<Tusuario> tusuarioList;

    public Trol() {
    }

    public Trol(Integer idtrol) {
        this.idtrol = idtrol;
    }

    public Trol(Integer idtrol, String nombre) {
        this.idtrol = idtrol;
        this.nombre = nombre;
    }

    public Integer getIdtrol() {
        return idtrol;
    }

    public void setIdtrol(Integer idtrol) {
        this.idtrol = idtrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Tpermiso> getTpermisoList() {
        return tpermisoList;
    }

    public void setTpermisoList(List<Tpermiso> tpermisoList) {
        this.tpermisoList = tpermisoList;
    }

    @XmlTransient
    public List<Tusuario> getTusuarioList() {
        return tusuarioList;
    }

    public void setTusuarioList(List<Tusuario> tusuarioList) {
        this.tusuarioList = tusuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtrol != null ? idtrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trol)) {
            return false;
        }
        Trol other = (Trol) object;
        if ((this.idtrol == null && other.idtrol != null) || (this.idtrol != null && !this.idtrol.equals(other.idtrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stonebank.entity.Trol[ idtrol=" + idtrol + " ]";
    }
    
}
