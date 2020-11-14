package com.provapoo.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
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


public class TelaPrincipalController extends Application implements Initializable  {
	 @FXML
	    private Button btnNovaConta;

	    @FXML
	    private Button btnTransferir;

	    @FXML
	    private Button btnSacar;

	    @FXML
	    private Button btnDepositar;


	    @FXML
	    private Button btnSair;

	    @FXML
	    private Label nContas;
	    @FXML
	    private Button btnNovaContaPoupanca;

	    @FXML
	    private Button btnNovaContaCorrente;
	    @FXML
	    private TitledPane PainelContaCorrente;

    
    
    public void chamaTelaNovoCliente() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovoCliente.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    public void chamaTelaNovaContaPoupanca() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovaCP.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    public void chamaTelaNovaContaCorrente() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovaCc.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    
    public void chamaTelaDeposito() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaDeposito.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    public void chamaTelaPoupanca() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaContaPoupanca.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    public void chamaTelaTranferencia() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaTranferencia.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    public void chamaTelaTranferenciaPoupanca() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaTranferenciaPoupanca.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    public void chamaTelaDepositoPoupanca() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaDepositoCP.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    public void chamaTelaSacar() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaSaquePoupanca.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
    }
    public void chamaTelaSacarContaCorrente() throws IOException, InterruptedException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TelaSaque.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
 
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

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	public void execute() {
		launch();
	}




}
