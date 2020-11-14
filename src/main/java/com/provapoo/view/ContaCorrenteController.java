package com.provapoo.view;

import com.provapoo.DAO.ContaCorrenteDAO;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;
import com.provapoo.model.GeradorDeContas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ContaCorrenteController {

	// DEPOSITO CONTA CORRENTE
	@FXML
	private Button btnAbrirConta;

	@FXML
	private TextField txtNumConta;

	@FXML
	private TextField txtValorDep;

	public void depositoContaCorrente(ActionEvent event) {
		String conta = txtNumConta.getText();
		double valor = Double.parseDouble(txtValorDep.getText());
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		ContaCorrente cc = ccDao.findByNumeroConta(conta);
		ccDao.depositaCC(cc, valor);

	}

	// sacar
	@FXML
	private Button btnSacar;

	@FXML
	private TextField txtNumContaSacar;

	@FXML
	private TextField txtValorSaque;
	
	@FXML
	private TextField txtSenhaSaque;

	public void sacar(ActionEvent event) {
		String conta = txtNumContaSacar.getText();
		String senha = txtSenhaSaque.getText();
		double valor = Double.parseDouble(txtValorSaque.getText());		
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		ContaCorrente cc = ccDao.findByNumeroConta(conta);
		if (ccDao.autentica(conta, senha)) {
			System.out.println("Autenticado com sucesso!!!");
			ccDao.sacarCC(cc, ccDao.tarifaSaque(valor));
			
		} else {
			System.out.println("Falha na autenticação!!!");
		}
		

	}

	// TRANFERENCIA
	@FXML
    private TextField txtcontaOrigemCc;

    @FXML
    private TextField txtvalorOrigemCc;

    @FXML
    private TextField txtcontaDestinoCc;


    @FXML
    private TextField txtSenhaTranferenciaCc;


	public void tranferir(ActionEvent event) {
		String contaOrigem = txtcontaOrigemCc.getText();
		String contaDestino = txtcontaDestinoCc.getText();
		Double valor = Double.parseDouble(txtvalorOrigemCc.getText());
		String senha = txtSenhaTranferenciaCc.getText();
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		ContaCorrente cco = ccDao.findByNumeroConta(contaOrigem);
		ContaCorrente ccd = ccDao.findByNumeroConta(contaDestino);
		
		if (ccDao.autentica(contaOrigem, senha)) {
			System.out.println("Autenticado com sucesso!!!");

			ccDao.sacarCC(cco, ccDao.tarifaTransferencia(valor));
			ccDao.depositaCC(ccd, valor);
			
		} else {
			System.out.println("Falha na autenticação!!!");
		}
		


	}

	// Nova ContaCorrente
	@FXML
	private Button btnCriarCp;

	@FXML
	private TextField txtCpfClienteNewCc;

	@FXML
	private TextField txtSenhaNewCc;

	@FXML
	private TextField txtDepInicialCc;

	public void novaContaCorrente(ActionEvent event) {
		String cpf = txtCpfClienteNewCc.getText();
		double valor = Double.parseDouble(txtDepInicialCc.getText());
		String senha = txtSenhaNewCc.getText();
		Cliente cliente = null;
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		if (!cpf.equals("")) {
			try {
				cliente = new ContaCorrenteDAO().buscaClienteCPF(cpf);
				ContaCorrente cc = new ContaCorrente();
				cc.setAgencia(9929);
				cc.setNumConta(Integer.toString(GeradorDeContas.geraConta()));
				cc.setSaldo(valor);
				cc.setIdCliente(cliente.getId());
				cc.setSenha(senha);
				ccDao.inserirCC(cc);
			} catch (Exception e) {

			}
		}
	}


}
