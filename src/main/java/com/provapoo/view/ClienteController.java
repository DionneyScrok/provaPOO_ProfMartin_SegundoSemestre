package com.provapoo.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.provapoo.DAO.ClienteDAO;
import com.provapoo.DAO.ContaCorrenteDAO;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;
import com.sun.glass.events.KeyEvent;

import Util.TextFieldFormatter;
import Util.ValidaCpf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    @FXML
    private Button btnSair;
    

    @FXML
    void tfMaskCpf() {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("###.###.###-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(cliCpf);
    	tff.formatter();

    }
    @FXML
    void tfMaskTelefone() {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("(##)#####-####");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(cliTelefone);
    	tff.formatter();

    }
    
    private void alerta(String title, String text, String header) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
       
    }

	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	

	public void btnAdd(ActionEvent event) throws IOException  {				
		Cliente cliente = pegaCliente();
		ClienteDAO clientedao = new ClienteDAO();
		
		if(clientedao.buscaClienteByCPF(cliente.getCpf()) == null) {
			
			System.out.println("Não existe um cpf igual a esse no banco, prossiga pra inserção do cliente e valide o cpf!!!");
			
			if(ValidaCpf.isCPF(cliente.getCpf())) {	
				clientedao.inserirCliente(cliente);	
				alerta("Sucesso","Cliente cadastrado com sucesso!", "Parabéns, hora de abrir uma conta!!!");
				fecharTela(btnCriarCliente);
				FXMLLoader fxmlLoader = new FXMLLoader(TelaPrincipalController.class.getResource("TelaPrincipal.fxml"));
				Parent root1 = fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root1));
				stage.initStyle(StageStyle.UNDECORATED);
				stage.show();
				
				
			}
			else {
				alerta("Atenção","Cpf Invalido", "O CPF informado não existe!!!");
				
			}			
		}
		else {
			alerta("Atenção","Cliente existente!!!", "O CPF inserido já esta cadastrado!!!");
		}
						
	}
	
	private Cliente pegaCliente() {
		String cpf = cliCpf.getText();
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");

		return new Cliente(cliNome.getText(), cpf,cliProfissao.getText(),cliEmail.getText(),cliEndereco.getText(),cliTelefone.getText());
	}
	
    public void fecharTela(Button btnFechar) {
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }
    @FXML
    void voltarInicio(ActionEvent event) {
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(TelaPrincipalController.class.getResource("TelaPrincipal.fxml"));
    		Parent root1 = fxmlLoader.load();
    		Stage stage = new Stage();
    		stage.setScene(new Scene(root1));
    		stage.initStyle(StageStyle.UNDECORATED);
    		stage.show();
    		fecharTela(btnSair);
    		
   		
    	}catch(IOException e) {
    		
    	}

    }
	
			
	

}


