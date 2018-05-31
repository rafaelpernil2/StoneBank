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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tusuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tusuario.findAll", query = "SELECT t FROM Tusuario t")
    , @NamedQuery(name = "Tusuario.findByDniUsuario", query = "SELECT t FROM Tusuario t WHERE t.dniUsuario = :dniUsuario")
    , @NamedQuery(name = "Tusuario.findByNumCuenta", query = "SELECT t FROM Tusuario t WHERE t.numCuenta = :numCuenta")
    , @NamedQuery(name = "Tusuario.findByHashContrasena", query = "SELECT t FROM Tusuario t WHERE t.hashContrasena = :hashContrasena")
    , @NamedQuery(name = "Tusuario.findByNombre", query = "SELECT t FROM Tusuario t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tusuario.findByApellidos", query = "SELECT t FROM Tusuario t WHERE t.apellidos = :apellidos")
    , @NamedQuery(name = "Tusuario.findByTelefono", query = "SELECT t FROM Tusuario t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Tusuario.findByEmail", query = "SELECT t FROM Tusuario t WHERE t.email = :email")
    , @NamedQuery(name = "Tusuario.findByDomicilio", query = "SELECT t FROM Tusuario t WHERE t.domicilio = :domicilio")})
@Cacheable(false)
public class Tusuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dniUsuario")
    private Integer dniUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numCuenta")
    private int numCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "hashContrasena")
    private String hashContrasena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "telefono")
    private Integer telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 300)
    @Column(name = "email")
    private String email;
    @Size(max = 400)
    @Column(name = "domicilio")
    private String domicilio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dNIEmisor", fetch = FetchType.EAGER )
    private List<Ttransferencia> ttransferenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dNIReceptor",fetch = FetchType.EAGER)
    private List<Ttransferencia> ttransferenciaList1;
    @JoinColumn(name = "trol_idtrol", referencedColumnName = "idtrol")
    @ManyToOne(optional = false)
    private Trol trolIdtrol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tusuariodniUsuario", fetch = FetchType.EAGER)
    private List<Tmovimiento> tmovimientoList;
    

    public Tusuario() {
    }
    
    public Tusuario(Integer dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public Tusuario(Integer dniUsuario, int numCuenta, String hashContrasena, String nombre, String apellidos) {
        this.dniUsuario = dniUsuario;
        this.numCuenta = numCuenta;
        this.hashContrasena = hashContrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Integer getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(Integer dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getHashContrasena() {
        return hashContrasena;
    }

    public void setHashContrasena(String hashContrasena) {
        this.hashContrasena = hashContrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @XmlTransient
    public List<Ttransferencia> getTtransferenciaList() {
        return ttransferenciaList;
    }

    public void setTtransferenciaList(List<Ttransferencia> ttransferenciaList) {
        this.ttransferenciaList = ttransferenciaList;
    }

    @XmlTransient
    public List<Ttransferencia> getTtransferenciaList1() {
        return ttransferenciaList1;
    }

    public void setTtransferenciaList1(List<Ttransferencia> ttransferenciaList1) {
        this.ttransferenciaList1 = ttransferenciaList1;
    }

    public Trol getTrolIdtrol() {
        return trolIdtrol;
    }

    public void setTrolIdtrol(Trol trolIdtrol) {
        this.trolIdtrol = trolIdtrol;
    }

    @XmlTransient
    public List<Tmovimiento> getTmovimientoList() {
        return tmovimientoList;
    }

    public void setTmovimientoList(List<Tmovimiento> tmovimientoList) {
        this.tmovimientoList = tmovimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dniUsuario != null ? dniUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tusuario)) {
            return false;
        }
        Tusuario other = (Tusuario) object;
        if ((this.dniUsuario == null && other.dniUsuario != null) || (this.dniUsuario != null && !this.dniUsuario.equals(other.dniUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNombre() + " " + this.getApellidos();
        //return "stonebank.entity.Tusuario[ dniUsuario=" + dniUsuario + " ]";
    }
    
    /*
    * 
    *
    */
    public boolean puedeTransferir(double cantidad){
        double sumas, restas,total;
        for(Tmovimiento movimiento : tmovimientoList){
            
        }
        return false;
    }
    
}
