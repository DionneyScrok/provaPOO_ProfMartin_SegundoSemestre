package com.provapoo.model;

public class ContaPoupanca extends Conta {

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(long id, double saldo, int agencia, String numConta, long idCliente, Cliente titular,
			String senha) {
		super(id, saldo, agencia, numConta, idCliente, titular, senha);		
	}

	public ContaPoupanca(int agencia, String numConta, long idCliente, Cliente titular, String senha, double saldo) {
		super(agencia, numConta, idCliente, titular, senha, saldo);
		// TODO Auto-generated constructor stub
	}



	
	
	


}
