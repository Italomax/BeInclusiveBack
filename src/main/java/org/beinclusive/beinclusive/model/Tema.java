package org.beinclusive.beinclusive.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.beinclusive.beinclusive.utils.EnumAvaliacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_tema")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	private EnumAvaliacao avaliacao;
	
	@OneToMany(mappedBy = "tema", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("tema")
	private List<PostagemModel> postagem;  


	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id; 
	}
	
	public List<PostagemModel> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<PostagemModel> postagem) {
		this.postagem = postagem;
	}

	public EnumAvaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(EnumAvaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
