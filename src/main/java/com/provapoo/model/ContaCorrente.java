package com.provapoo.model;

public class ContaCorrente extends Conta {

	public void addimpostos() {

	}

	public ContaCorrente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContaCorrente(double saldo, int agencia, String numConta, Cliente titular) {
		super(saldo, agencia, numConta, titular);

	}

	public ContaCorrente(long id, double saldo, int agencia, String numConta, Cliente titular) {
		super(id, saldo, agencia, numConta, titular);
	}

}
