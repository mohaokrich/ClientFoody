package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comentario")
public class Comentario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id_comentario;
    private String comentario;
    private Date fecha;


    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="id_comida")
    private Comida comida;
    
    public Comida getComida() {
		return comida;
	}
	public void setComida(Comida comida) {
		this.comida = comida;
	}
	
	//Muchos comentarios pueden ser hechos por un usuario!!!!
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    
    
    public Comentario(String comentario, Date fecha, Comida comida, Usuario usuario) {
		super();
		this.comentario = comentario;
		this.fecha = fecha;
		this.comida = comida;
		this.usuario = usuario;
	}
	public Comentario() {
    }
    public Comentario(Long id_comentario, String comentario, Date fecha) {
        this.id_comentario = id_comentario;

        this.comentario = comentario;
        this.fecha = fecha;
    }
    public Long getId_comentario() {
        return id_comentario;
    }
    public void setId_comentario(Long id_comentario) {
        this.id_comentario = id_comentario;
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
