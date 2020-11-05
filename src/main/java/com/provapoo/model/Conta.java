package com.provapoo.model;

public abstract class Conta {

	private long id;
	protected double saldo;
	private int agencia;
	private String numConta;
	private long idCliente;
	Cliente cliente;

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	private Cliente titular;
	private static int total = 0;

	public Conta(long id, double saldo, int agencia, String numConta, Cliente titular) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.agencia = agencia;
		this.numConta = numConta;
		this.titular = titular;
	}

	public Conta(double saldo, int agencia, String numConta, Cliente titular) {
		super();
		this.saldo = saldo;
		this.agencia = agencia;
		this.numConta = numConta;
		this.titular = titular;
	}

	public Conta() {

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

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numero) {
		this.numConta = numero;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public static int getTotal() {
		return total;
	}

	public static void setTotal(int total) {
		Conta.total = total;
	}

}
