package com.provapoo.model;

public class Cliente {

	private int id;
	private String nome;
	private String cpf;
	private String profissao;
	private String email;
	private String endereco;
	private String telefone;

	public Cliente(String nome, String cpf, String profissao, String email, String endereco, String telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.profissao = profissao;
		this.email = email;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public Cliente() {

	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
