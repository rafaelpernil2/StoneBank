 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stonebank.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
@Table(name = "tmovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tmovimiento.findAll", query = "SELECT t FROM Tmovimiento t")
    , @NamedQuery(name = "Tmovimiento.findByIdtmovimiento", query = "SELECT t FROM Tmovimiento t WHERE t.idtmovimiento = :idtmovimiento")
    , @NamedQuery(name = "Tmovimiento.findByConcepto", query = "SELECT t FROM Tmovimiento t WHERE t.concepto = :concepto")
    , @NamedQuery(name = "Tmovimiento.findByCantidad", query = "SELECT t FROM Tmovimiento t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "Tmovimiento.findByIbanEntidad", query = "SELECT t FROM Tmovimiento t WHERE t.ibanEntidad = :ibanEntidad")
    , @NamedQuery(name = "Tmovimiento.findByFecha", query = "SELECT t FROM Tmovimiento t WHERE t.fecha = :fecha")})
@Cacheable(false)
public class Tmovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtmovimiento")
    private Integer idtmovimiento;
    @Size(max = 200)
    @Column(name = "concepto")
    private String concepto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "ibanEntidad")
    private String ibanEntidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "tusuario_dniUsuario", referencedColumnName = "dniUsuario")
    @ManyToOne(optional = false)
    private Tusuario tusuariodniUsuario;

    public Tmovimiento() {
    }

    public Tmovimiento(Integer idtmovimiento) {
        this.idtmovimiento = idtmovimiento;
    }

    public Tmovimiento(Integer idtmovimiento, String ibanEntidad, Date fecha) {
        this.idtmovimiento = idtmovimiento;
        this.ibanEntidad = ibanEntidad;
        this.fecha = fecha;
    }

    public Integer getIdtmovimiento() {
        return idtmovimiento;
    }

    public void setIdtmovimiento(Integer idtmovimiento) {
        this.idtmovimiento = idtmovimiento;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getIbanEntidad() {
        return ibanEntidad;
    }

    public void setIbanEntidad(String ibanEntidad) {
        this.ibanEntidad = ibanEntidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tusuario getTusuariodniUsuario() {
        return tusuariodniUsuario;
    }

    public void setTusuariodniUsuario(Tusuario tusuariodniUsuario) {
        this.tusuariodniUsuario = tusuariodniUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtmovimiento != null ? idtmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tmovimiento)) {
            return false;
        }
        Tmovimiento other = (Tmovimiento) object;
        if ((this.idtmovimiento == null && other.idtmovimiento != null) || (this.idtmovimiento != null && !this.idtmovimiento.equals(other.idtmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return this.getTusuariodniUsuario().getNombre() + this.getTusuariodniUsuario().getApellidos();
        return "stonebank.entity.Tmovimiento[ idtmovimiento=" + idtmovimiento + " ]";
    }
    
}
