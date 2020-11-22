package com.provapoo.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.provapoo.DAO.ClienteDAO;
import com.provapoo.DAO.ContaCorrenteDAO;
import com.provapoo.DAO.ContaPoupancaDAO;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;
import com.provapoo.model.ContaPoupanca;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaPrincipalController extends Application implements Initializable {
	@FXML
	private Button btnNovaConta;

	@FXML
	private Button btnTransferir;

	@FXML
	private Button btnSacar;

	@FXML
	private Button btnDepositar;


	@FXML
	private Label nContas;
	@FXML
	private Button btnNovaContaPoupanca;

	@FXML
	private Button btnNovaContaCorrente;

	@FXML
	private TitledPane PainelContaCorrente;

	@FXML
	private Label labelQtdClientes;

	@FXML
	private Label labelQtdPoupanca;

	@FXML
	private Label labelQtdCCorrente;

	@FXML
	private Button btnTransferiCPparaCC;

	@FXML
	private Button btnTransferiCCparaCP;

	@FXML
	private Button btnTransferirPoupanca;
	
	@FXML
	private Button btnDepositarPoupanca;
		
	@FXML
	private Button btnSacarPoupanca;

	public void chamaTelaTranferirPoupanca() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransferenciaCcorrenteCpoupanca.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnTransferiCCparaCP);
	}

	public void chamaTelaTranferirParaCorrente() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransferenciaCpoupancaCcorrente.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnTransferiCPparaCC);

	}

	public void chamaTelaNovoCliente() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovoCliente.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnNovaConta);

	}

	public void chamaTelaNovaContaPoupanca() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovaCP.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnNovaContaPoupanca);

	}

	public void chamaTelaNovaContaCorrente() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovaCc.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnNovaContaCorrente);

	}

	public void chamaTelaDeposito() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaDeposito.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnDepositar);

	}

//    public void chamaTelaPoupanca() throws IOException, InterruptedException {
//    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaContaPoupanca.fxml"));
//        Parent root1 = fxmlLoader.load();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root1));
//        stage.show();
//        fecharTelaPrincipal(btnDepositar); 
// 
//    }
	public void chamaTelaTranferencia() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaTranferencia.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnTransferir);

	}

	public void chamaTelaTranferenciaPoupanca() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaTranferenciaPoupanca.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnTransferirPoupanca);

	}

	public void chamaTelaDepositoPoupanca() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaDepositoCP.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnDepositarPoupanca);
	}

	public void chamaTelaSacar() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaSaquePoupanca.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnSacarPoupanca);

	}

	public void chamaTelaSacarContaCorrente() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaSaque.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
		fecharTelaPrincipal(btnSacar);

	}

	public void chamaTelaListClientes() throws IOException, InterruptedException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaGridClientes.fxml"));
		Parent root1 = fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();

	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
			Scene sc = new Scene(pane);
			stage.setScene(sc);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void contador() {
		ClienteDAO cliDao = new ClienteDAO();
		ContaCorrenteDAO ccDao = new ContaCorrenteDAO();
		ContaPoupancaDAO cpDao = new ContaPoupancaDAO();
		List<Cliente> listCliente = cliDao.listarClientes();
		List<ContaCorrente> listCorrente = ccDao.listarContaCorrente();
		List<ContaPoupanca> listPoupanca = cpDao.listarContaPoupanca();

		labelQtdCCorrente.setText(listCorrente.size() + "");
		labelQtdClientes.setText(listCliente.size() + "");
		labelQtdPoupanca.setText(listPoupanca.size() + "");

	}
	
	@FXML
	private Button btnSair;
    @FXML
    void sairFechar(ActionEvent event) {
    	fecharTelaPrincipal(btnSair);

    }

	public void initialize(URL arg0, ResourceBundle arg1) {
		contador();

	}

	public void execute() {
		launch();
	}

	public void fecharTelaPrincipal(Button btnFexar) {
		Stage stage = (Stage) btnFexar.getScene().getWindow();
		stage.close();
	}

}
