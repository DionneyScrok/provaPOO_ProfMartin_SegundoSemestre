package com.ClienteTabela;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClienteTabela {
	
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty nome;
	private final SimpleStringProperty cpf;
	private final SimpleStringProperty profissao;
	private final SimpleStringProperty email;
	private final SimpleStringProperty endereco;
	private final SimpleStringProperty telefone;
	
	public ClienteTabela(int id, String nome, String cpf, String profissao, String email,String endereco, String telefone) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.nome = new SimpleStringProperty(nome);
		this.cpf = new SimpleStringProperty(cpf);
		this.profissao = new SimpleStringProperty(profissao);
		this.email = new SimpleStringProperty(email);
		this.endereco = new SimpleStringProperty(endereco);
		this.telefone = new SimpleStringProperty(telefone);
	}


	public int getId() {
		return id.get();
	}

	public String getNome() {
		return nome.get();
	}

	public String getCpf() {
		return cpf.get();
	}

	public String getProfissao() {
		return profissao.get();
	}

	public String getEmail() {
		return email.get();
	}

	public String getEndereco() {
		return endereco.get();
	}

	public String getTelefone() {
		return telefone.get();
	}

}
