package com.provapoo.view;

import com.provapoo.DAO.ContaCorrenteDAO;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ContaCorrenteController {

	@FXML
	private Button btnAbrirConta;

	@FXML
	private TextField txtNumConta;

	@FXML
	private TextField txtValorDep;
	@FXML
	private Button btnSacar;

	@FXML
	private TextField txtNumContaSacar;

	@FXML
	private TextField txtValorSaque;
	
	@FXML
	private TextField txtcontaOrigem;

	@FXML
	private TextField txtvalorOrigem;

	@FXML
	private TextField txtcontaDestino;


	public void novaContaCorrente(ActionEvent event) {
		String cpf = txtNumConta.getText();
		double valor = Double.parseDouble(txtValorDep.getText());
		Cliente cliente = null;
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		if (!cpf.equals("")) {
			try {
				cliente = new ContaCorrenteDAO().buscaClienteCPF(cpf);
				ContaCorrente cc = new ContaCorrente();
				cc.setAgencia(9929);
				cc.setNumConta(pegaCPF());
				cc.setSaldo(valor);
				cc.setIdCliente(cliente.getId());
				ccDao.inserirCC(cc);
			} catch (Exception e) {

			}
		}
	}

	public void depositoContaCorrente(ActionEvent event) {
		String conta = txtNumConta.getText();
		double valor = Double.parseDouble(txtValorDep.getText());
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		ContaCorrente cc = ccDao.findByNumeroConta(conta);
		ccDao.depositaCC(cc, valor);

	}

	public void sacar(ActionEvent event) {
		String conta = txtNumContaSacar.getText();
		double valor = Double.parseDouble(txtValorSaque.getText());
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		ContaCorrente cc = ccDao.findByNumeroConta(conta);
		ccDao.sacarCC(cc, valor);

	}
	public void tranferir (ActionEvent event) {
		String contaOrigem = txtcontaOrigem.getText();
		String contaDestino = txtcontaDestino.getText();
		Double valor = Double.parseDouble(txtvalorOrigem.getText());
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		ContaCorrente cco = ccDao.findByNumeroConta(contaOrigem);
		ContaCorrente ccd = ccDao.findByNumeroConta(contaDestino);
		ccDao.sacarCC(cco, valor);
		ccDao.depositaCC(ccd, valor);
		
	}

	private String pegaCPF() {
		String cpf = txtNumConta.getText();
		System.out.println("cpf " + cpf);
		return cpf;
	}

	private ContaCorrente findByCC(String conta) {
		ContaCorrente cc = new ContaCorrente();
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		cc = ccDao.findByNumeroConta(conta);
		return cc;
	}



}
