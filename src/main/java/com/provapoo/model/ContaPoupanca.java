package com.provapoo.model;

public class ContaPoupanca extends Conta {
	String senha;

	public ContaPoupanca(long id, double saldo, int agencia, String numero, Cliente titular, String senha) {
		super(id, saldo, agencia, numero, titular);
		this.senha = senha;
	}

	public ContaPoupanca() {
	}

	public String getSenha() {
		return null;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
