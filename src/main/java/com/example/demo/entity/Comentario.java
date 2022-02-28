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
@Table(name = "comentario")
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id_comentario;
    private Long id_comida;
    private Long id_usuario;
    private String comentario;
    private Date fecha;


    public Comentario() {
    }
    public Comentario(Long id_comentario, Long id_comida, Long id_usuario, String comentario, Date fecha) {
        this.id_comentario = id_comentario;
        this.id_comida = id_comida;
        this.id_usuario = id_usuario;
        this.comentario = comentario;
        this.fecha = fecha;
    }
    public Long getId_comentario() {
        return id_comentario;
    }
    public void setId_comentario(Long id_comentario) {
        this.id_comentario = id_comentario;
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
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

} 
