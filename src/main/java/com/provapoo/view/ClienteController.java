package com.provapoo.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.provapoo.DAO.ClienteDAO;
import com.provapoo.DAO.ContaCorrenteDAO;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ClienteController implements Initializable{

    @FXML
    private TextField cliNome;

    @FXML
    private TextField cliCpf;

    @FXML
    private TextField cliTelefone;

    @FXML
    private TextField cliEmail;

    @FXML
    private TextField cliProfissao;

    @FXML
    private TextField cliEndereco;

    @FXML
    private Button btnCriarCliente;
    


	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	

	public void btnAdd(ActionEvent event) {				
		Cliente cliente = pegaCliente();
		ClienteDAO clientedao = new ClienteDAO();
		clientedao.inserirCliente(cliente);								
	}
	public void depositoContaCorrente(ActionEvent event) {
		Cliente cliente = pegaCliente();
		
		
	}
	
	private Cliente pegaCliente() {

		return new Cliente(cliNome.getText(), cliCpf.getText(),cliTelefone.getText(),cliEmail.getText(),cliProfissao.getText(), cliEndereco.getText());
	}
	
	
	

}


