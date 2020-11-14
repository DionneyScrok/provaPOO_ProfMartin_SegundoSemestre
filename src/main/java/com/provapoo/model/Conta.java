package com.provapoo.model;

public abstract class Conta {
	private long id;
	private int agencia;
	private String numConta;
	private long idCliente;
	private Cliente titular;
	private String senha;
	private double saldo;

	
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public String getNumConta() {
		return numConta;
	}
	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public Cliente getTitular() {
		return titular;
	}
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	

	public Conta(long id, double saldo, int agencia, String numConta, long idCliente, Cliente titular, String senha) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.agencia = agencia;
		this.numConta = numConta;
		this.idCliente = idCliente;
		this.titular = titular;
		this.senha = senha;
	}
	public Conta() {
		super();
	}
	public Conta(int agencia, String numConta, long idCliente, Cliente titular, String senha, double saldo) {
		super();
		this.agencia = agencia;
		this.numConta = numConta;
		this.idCliente = idCliente;
		this.titular = titular;
		this.senha = senha;
		this.saldo = saldo;
	}



	

}
