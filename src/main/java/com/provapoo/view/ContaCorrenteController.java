package com.provapoo.view;

import java.io.IOException;

import com.provapoo.DAO.ClienteDAO;
import com.provapoo.DAO.ContaCorrenteDAO;
import com.provapoo.DAO.ContaPoupancaDAO;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;
import com.provapoo.model.ContaPoupanca;
import com.provapoo.util.GeradorDeContas;
import com.provapoo.util.TextFieldFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ContaCorrenteController {

	// DEPOSITO CONTA CORRENTE
	@FXML
	private Button btnAbrirConta;

	@FXML
	private TextField txtNumConta;

	@FXML
	private TextField txtValorDep;

	@FXML
	private Button btnNovoDeposito;

	public void depositoContaCorrente(ActionEvent event) throws IOException {
		String conta = txtNumConta.getText();
		if(txtValorDep.getText().equals("")) {
			alerta("Error", "Preencha o campo valor!", "Campo nulos identificados!!!");
			return;
		}
		double valor = Double.parseDouble(txtValorDep.getText());
		
		if (conta.equals("") ) {
			alerta("Error", "Preencha os campos!", "Campo nulos identificados!!!");
		} else {
			ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
			ContaCorrente cc = ccDao.findByNumeroConta(conta);
			if (cc != null) {
				ccDao.depositaCC(cc, valor);
				alerta("Sucesso!!!", "Deposito!", "Deposito realizado com sucesso!!!");

				FXMLLoader fxmlLoader = new FXMLLoader(TelaPrincipalController.class.getResource("TelaPrincipal.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initStyle(StageStyle.UNDECORATED);
				stage.show();
				fecharTela(btnNovoDeposito);

			} else {
				alerta("Error!!!", "O deposito falhou!", "A conta informada não existe!!!");
			}
		}

	}

	// sacar
	@FXML
	private Button btnSacar;

	@FXML
	private TextField txtNumContaSacar;

	@FXML
	private TextField txtValorSaque;

	@FXML
	private PasswordField txtSenhaSaque;

	public void sacar(ActionEvent event) throws IOException {
		String conta = txtNumContaSacar.getText();
		String senha = txtSenhaSaque.getText();
		if(txtValorSaque.getText().equals("")) {
			alerta("Error", "Preencha o campo valor!", "Campo nulos identificados!!!");
			return;
		}
		double valor = Double.parseDouble(txtValorSaque.getText());
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		ContaCorrente cc = ccDao.findByNumeroConta(conta);
		if (conta.equals("")) {
			alerta("Error", "Preencha os campos!", "Campo nulos identificados!!!");
		} else {
			if(cc == null) {
				alerta("Atenção!!!", "A conta informada é invalida!", "Verifique a conta e tente novamente.");
				return;
			}
			if (ccDao.autentica(conta, senha)) {
				System.out.println("Autenticado com sucesso!!!");
				if (cc.getSaldo() < valor) {

					alerta("Atenção!!!", "Saque falhou!", "O saldo é insuficiente para o saque.");
				} else {
					ccDao.sacarCC(cc, ccDao.tarifaSaque(valor));
					alerta("Sucesso!!!", "Saque realizado!", "O dinheiro já foi debitado.");
					FXMLLoader fxmlLoader = new FXMLLoader(
							TelaPrincipalController.class.getResource("TelaPrincipal.fxml"));
					Parent root1 = fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));
					stage.initStyle(StageStyle.UNDECORATED);
					stage.show();
					fecharTela(btnSacar);

				}

			} else {
				alerta("Atenção!!!", "Autenticação falhou!", "Verifique a senha e tente novamente.");
			}
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
	private PasswordField txtSenhaTranferenciaCc;

	@FXML
	private Button btnTranferir;

	public void tranferir(ActionEvent event) throws IOException {
		String contaOrigem = txtcontaOrigemCc.getText();
		String contaDestino = txtcontaDestinoCc.getText();
		
		if(txtvalorOrigemCc.getText().equals("")) {
			alerta("Error", "Preencha o campo valor!", "Campo nulos identificados!!!");
			return;
		}
		Double valor = Double.parseDouble(txtvalorOrigemCc.getText());
		String senha = txtSenhaTranferenciaCc.getText();

		if (contaOrigem.equals("") || contaDestino.equals("")) {
			alerta("Error", "Preencha os campos!", "Campo nulos identificados!!!");
		} else {
			ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
			ContaCorrente cco = ccDao.findByNumeroConta(contaOrigem);
			ContaCorrente ccd = ccDao.findByNumeroConta(contaDestino);
			if (cco == null) {
				alerta("Error!!!", "Conta inexistente!", "A conta de origem não existe.");
			} else {
				if (ccDao.autentica(contaOrigem, senha)) {
					System.out.println("Autenticado com sucesso!!!");
					if (ccd == null) {
						alerta("Error!!!", "Tranferencia falhou!", "A conta destino não existe.");
					} else {
						if (cco.getSaldo() < valor) {
							alerta("Atenção!!!", "Tranferencia falhou!",
									"O saldo é insuficiente para a transferencia.");

						} else {
							ccDao.sacarCC(cco, ccDao.tarifaTransferencia(valor));
							ccDao.depositaCC(ccd, valor);
							alerta("Sucesso!!!", "Tranferencia realizada com sucesso!",
									"Tudo certo, o dinheiro está a caminho do destinatario!");
							FXMLLoader fxmlLoader = new FXMLLoader(
									TelaPrincipalController.class.getResource("TelaPrincipal.fxml"));
							Parent root1 = fxmlLoader.load();
							Stage stage = new Stage();
							stage.setScene(new Scene(root1));
							stage.initStyle(StageStyle.UNDECORATED);
							stage.show();
							fecharTela(btnTranferir);
						}

					}

				} else {
					alerta("Error!!!", "Autenticação falhou!", "A senha ou sua conta não existem.");
				}
			}

		}

	}

	// Nova ContaCorrente
	@FXML
	private Button btnCriarCc;

	@FXML
	private TextField txtCpfClienteNewCc;

	@FXML
	private PasswordField txtSenhaNewCc;

	@FXML
	private TextField txtDepInicialCc;

	@FXML
	void formatReal() {

	}

	@FXML
	void tfMaskCpf() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtCpfClienteNewCc);
		tff.formatter();

	}

	public void novaContaCorrente(ActionEvent event) throws IOException {
		String cpf = txtCpfClienteNewCc.getText();
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		if(txtDepInicialCc.getText().equals("")) {
			alerta("Error", "Preencha o campo valor!", "Campo nulos identificados!!!");
			return;
		}
		double valor = Double.parseDouble(txtDepInicialCc.getText());
		String senha = txtSenhaNewCc.getText();
		ClienteDAO cliDao = new ClienteDAO();

		if (cpf.equals("") || senha.equals("")) {
			alerta("Error", "Preencha os campos!", "Campo nulos identificados!!!");
		} else {
						
			Cliente cliente = cliDao.buscaClienteByCPF(cpf);
			ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
			if (!cpf.equals("")) {
				try {
					
					if (cliDao.buscaClienteByCPF(cpf) != null) {
						ContaCorrente cc = new ContaCorrente();
						cc.setAgencia(9929);
						cc.setNumConta(GeradorDeContas.geraCc());
						cc.setSaldo(valor);
						cc.setIdCliente(cliente.getId());
						cc.setSenha(senha);
						ccDao.inserirCC(cc);
						cliDao.alteraStatus(cpf);
						alerta("Sucesso!!!", "Parabéns!", "Conta aberta com sucesso!!!");

						FXMLLoader fxmlLoader = new FXMLLoader(
								TelaPrincipalController.class.getResource("TelaPrincipal.fxml"));
						Parent root1 = fxmlLoader.load();
						Stage stage = new Stage();
						stage.setScene(new Scene(root1));
						stage.initStyle(StageStyle.UNDECORATED);
						stage.show();
						fecharTela(btnCriarCc);
					} else {
						alerta("Erro!!!", "O cpf informado não é de um cliente!",
								"Antes de criar uma conta, crie um cliente!!!");
					}

				} catch (Exception e) {

				}
			}
		}

	}

	// TRANFERENTE DE CONTA CORRENTE PARA CONTA POUPANCA

	@FXML
	private TextField txtCcTranfereCp;

	@FXML
	private TextField tctCpRecebeCp;

	@FXML
	private TextField valorTranferenciaentreCcCp;

	@FXML
	private PasswordField txtPassCCcp;

	@FXML
	private Button btnTransfereCcCp;

	@FXML
	void transferenciaEntreContasCcParaCp(ActionEvent event) throws IOException {
		ContaCorrente cC = new ContaCorrente();
		ContaPoupanca cP = new ContaPoupanca();
		ContaPoupancaDAO cpDao = new ContaPoupancaDAO();
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		String cCorrente = txtCcTranfereCp.getText();
		String cPoupanca = tctCpRecebeCp.getText();

	
		if(valorTranferenciaentreCcCp.getText().equals("")) {
			alerta("Error", "Preencha o campo valor!", "Campo nulos identificados!!!");
			return;
		}
		double valor = Double.parseDouble(valorTranferenciaentreCcCp.getText());
		String senha = txtPassCCcp.getText();
		if (cCorrente.equals("") || cPoupanca.equals("") || senha.equals("")) {
			alerta("Error", "Preencha os campos!", "Campo(s) nulos identificados!!!");
		} else {
			cC = ccDao.findByNumeroConta(cCorrente);
			cP = cpDao.findByConta(cPoupanca);
			if (cC == null || cP == null) {

				alerta("Error!!!", "Tranferencia falhou!", "Uma das contas informada é invalida!");

			} else {
				if (ccDao.autentica(cCorrente, senha)) {
					System.out.println("Autenticado com sucesso!!!");
					if (cP == null) {
						alerta("Error!!!", "Tranferencia falhou!", "A conta destino não existe.");
					} else {
						if (cC.getSaldo() < valor) {
							alerta("Atenção!!!", "Tranferencia falhou!",
									"O saldo é insuficiente para a transferencia.");

						} else {
							ccDao.sacarCC(cC, ccDao.tarifaTransferencia(valor));
							cpDao.depositaCP(cP, valor);
							alerta("Sucesso!!!", "Tranferencia realizada com sucesso!",
									"Tudo certo, o dinheiro está a caminho do destinatario!");
							FXMLLoader fxmlLoader = new FXMLLoader(
									TelaPrincipalController.class.getResource("TelaPrincipal.fxml"));
							Parent root1 = fxmlLoader.load();
							Stage stage = new Stage();
							stage.setScene(new Scene(root1));
							stage.initStyle(StageStyle.UNDECORATED);
							stage.show();
							fecharTela(btnTransfereCcCp);

						}

					}

				} else {
					alerta("Error!!!", "Autenticação falhou!", "A senha ou sua conta não existem.");
				}
			}
		}

	}

	// SAIR
	@FXML
	private Button btnSair;

	@FXML
	void fecharSair(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(TelaPrincipalController.class.getResource("TelaPrincipal.fxml"));
			Parent root1 = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root1));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			fecharTela(btnSair);

		} catch (IOException e) {

		}

	}

	// UTIL

	private void alerta(String title, String text, String header) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();

	}

	public void fecharTela(Button btnFechar) {
		Stage stage = (Stage) btnFechar.getScene().getWindow();
		stage.close();
	}

}
