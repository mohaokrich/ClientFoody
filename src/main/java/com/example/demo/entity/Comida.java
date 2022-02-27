package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comida")
public class Comida implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comida")
    private Long id_comida;
    private Long id_usuario;
    private String nombre;
    private String pais;
    private String hiperenlace;
    private String descripcion;
    private Date fecha;

    public Comida() {
        super();

    }

    public Comida(Long id_comida, Long id_usuario, String nombre, String pais, String hiperenlace, String descripcion,
            Date fecha) {
        super();
        this.id_comida = id_comida;
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.pais = pais;
        this.hiperenlace = hiperenlace;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Long getId_comida() {
        return id_comida;
    }

    public void setId_comida(Long id_comida) {
        this.id_comida = id_comida;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getHiperenlace() {
        return hiperenlace;
    }

    public void setHiperenlace(String hiperenlace) {
        this.hiperenlace = hiperenlace;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}