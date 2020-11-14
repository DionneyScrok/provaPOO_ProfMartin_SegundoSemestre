package com.provapoo.view;

import com.provapoo.DAO.ClienteDAO;
import com.provapoo.DAO.ContaCorrenteDAO;
import com.provapoo.DAO.ContaPoupancaDAO;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;
import com.provapoo.model.ContaPoupanca;
import com.provapoo.model.GeradorDeContas;
import com.provapoo.model.Tarifas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ContaPoupancaController {

	//CRIA NOVA CONTA POUPANCA
	  @FXML
	    private Button btnCriarCp;

	    @FXML
	    private TextField txtCpfClienteNewCp;

	    @FXML
	    private TextField txtSenhaNewCp;

	    @FXML
	    private TextField txtDepInicialCp;

	    @FXML
	    void novaContaPoupanca(ActionEvent event) {
	    	GeradorDeContas gerador = new GeradorDeContas();
			String cpf = txtCpfClienteNewCp.getText();
			double valor = Double.parseDouble(txtDepInicialCp.getText());
			String senha = txtSenhaNewCp.getText();
			Cliente cliente = null;
			ContaPoupancaDAO ccDao = new ContaPoupancaDAO();
			ClienteDAO cliDao = new ClienteDAO();
			if (!cpf.equals("")) {
				try {
					cliente = new ClienteDAO().buscaClienteByCPF(cpf);
					ContaPoupanca cp = new ContaPoupanca();
					cp.setAgencia(9929);
					cp.setNumConta(Integer.toString(gerador.geraConta()));
					cp.setSaldo(valor);
					cp.setIdCliente(cliente.getId());
					cp.setSenha(senha);
					ccDao.inserirCp(cp);
				} catch (Exception e) {

				}
			}

		}

	    
	
	
	// DEPOSITO

	@FXML
	private TextField txtNumContaCp;

	@FXML
	private TextField txtValorCpDep;

	@FXML
	private TextField txtSenha;
	
	@FXML
	void depositaCp(ActionEvent event) {
		String conta = txtNumContaCp.getText();
		double valor = Double.parseDouble(txtValorCpDep.getText());
		ContaPoupancaDAO cpDao = new ContaPoupancaDAO();
		ContaPoupanca cp = cpDao.findByConta(conta);
		cpDao.depositaCP(cp, valor);

	}


	// SACAR
	@FXML
	private Button btnSacar;

	@FXML
	private TextField txtNumContaSacar;

	@FXML
	private TextField txtValorSaque;
	@FXML
	private TextField txtSenhaSaque;
	
	@FXML
	void sacarCp(ActionEvent event) {
		String conta = txtNumContaSacar.getText();
		double valor = Double.parseDouble(txtValorSaque.getText());
		String senha = txtSenhaSaque.getText();
		ContaPoupancaDAO cpDao = new ContaPoupancaDAO();
		ContaPoupanca cp = cpDao.findByConta(conta);
		if (cpDao.autentica(conta, senha)) {
			cpDao.sacarCP(cp, valor);
			System.out.println("Autenticado com sucesso!!!");
		} else {
			System.out.println("Falha na autenticação");
		}

	}
	//////////// TRANFERENCIA////////////
	@FXML
	private Button btnTranferir;

	@FXML
	private Button btnSair;

	@FXML
	private TextField txtcontaOrigem;

	@FXML
	private TextField txtvalorOrigem;

	@FXML
	private TextField txtcontaDestino;
	@FXML
	private TextField txtSenhaTranferencia;

	@FXML
	void tranferir(ActionEvent event) {
		String contaOrigem = txtcontaOrigem.getText();
		String contaDestino = txtcontaDestino.getText();
		Double valor = Double.parseDouble(txtvalorOrigem.getText());
		String senha = txtSenhaTranferencia.getText();
		ContaPoupancaDAO cpDao = new ContaPoupancaDAO();
		ContaPoupanca cpo = cpDao.findByConta(contaOrigem);
		ContaPoupanca cpd = cpDao.findByConta(contaDestino);
		if (cpDao.autentica(cpo.getNumConta(), senha)) {
			cpDao.sacarCP(cpo, valor);
			cpDao.depositaCP(cpd, valor);
		} else {
			System.out.println("Tranferencia falhou na autenticação!!!");
		}

	}





//	@FXML
//	void novaContaPoupanca(ActionEvent event) {
//		GeradorDeContas gerador = new GeradorDeContas();
//		String cpf = txtNumContaCp.getText();
//		double valor = Double.parseDouble(txtValorCpDep.getText());
//		String senha = txtSenha.getText();
//		Cliente cliente = null;
//		ContaPoupancaDAO ccDao = new ContaPoupancaDAO();
//		ClienteDAO cliDao = new ClienteDAO();
//		if (!cpf.equals("")) {
//			try {
//				cliente = new ClienteDAO().buscaClienteByCPF(cpf);
//				ContaPoupanca cp = new ContaPoupanca();
//				cp.setAgencia(9929);
//				cp.setNumConta(Integer.toString(gerador.geraConta()));
//				cp.setSaldo(valor);
//				cp.setIdCliente(cliente.getId());
//				cp.setSenha(senha);
//				ccDao.inserirCp(cp);
//			} catch (Exception e) {
//
//			}
//		}
//
//	}

}
