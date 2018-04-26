/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.entity;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rafaelpernil
 */
@Entity
@Table(name = "ttranferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ttranferencia.findAll", query = "SELECT t FROM Ttranferencia t")
    , @NamedQuery(name = "Ttranferencia.findById", query = "SELECT t FROM Ttranferencia t WHERE t.id = :id")
    , @NamedQuery(name = "Ttranferencia.findByCantidad", query = "SELECT t FROM Ttranferencia t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "Ttranferencia.findByConcepto", query = "SELECT t FROM Ttranferencia t WHERE t.concepto = :concepto")
    , @NamedQuery(name = "Ttranferencia.findByFecha", query = "SELECT t FROM Ttranferencia t WHERE t.fecha = :fecha")})
public class Ttranferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Size(max = 200)
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "DNIEmisor", referencedColumnName = "dniUsuario")
    @ManyToOne(optional = false)
    private Tusuario dNIEmisor;
    @JoinColumn(name = "DNIReceptor", referencedColumnName = "dniUsuario")
    @ManyToOne(optional = false)
    private Tusuario dNIReceptor;

    public Ttranferencia() {
    }

    public Ttranferencia(Integer id) {
        this.id = id;
    }

    public Ttranferencia(Integer id, int cantidad, Date fecha) {
        this.id = id;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tusuario getDNIEmisor() {
        return dNIEmisor;
    }

    public void setDNIEmisor(Tusuario dNIEmisor) {
        this.dNIEmisor = dNIEmisor;
    }

    public Tusuario getDNIReceptor() {
        return dNIReceptor;
    }

    public void setDNIReceptor(Tusuario dNIReceptor) {
        this.dNIReceptor = dNIReceptor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ttranferencia)) {
            return false;
        }
        Ttranferencia other = (Ttranferencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stonebank.entity.Ttranferencia[ id=" + id + " ]";
    }
    
}
