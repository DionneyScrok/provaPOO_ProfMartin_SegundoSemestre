package com.provapoo.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.ClienteTabela.ClienteTabela;
import com.provapoo.DAO.ClienteDAO;
import com.provapoo.model.Cliente;
import com.provapoo.util.TextFieldFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ListaClientesController {

	@FXML
	private TableView<ClienteTabela> tvClientes;

	@FXML
	private Button btnEditarCliente;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnAtualizar;

	@FXML
	private TextField txtBuscarCPF;

	@FXML
	private TableColumn<ClienteTabela, String> columnNome;

	@FXML
	private TableColumn<ClienteTabela, String> columnCpf;

	@FXML
	private TableColumn<ClienteTabela, String> columnProfissao;

	@FXML
	private TableColumn<ClienteTabela, String> columnEmail;

	@FXML
	private TableColumn<ClienteTabela, String> columnEndereco;

	@FXML
	private TableColumn<ClienteTabela, String> columnTelefone;

	@FXML
	private TableColumn<ClienteTabela, String> columnStatus;

	@FXML
	private Button btnPesquisar;

	@FXML
	private TextField tfNome;

	@FXML
	private TextField tfCpf;

	@FXML
	private TextField tfProfissao;

	@FXML
	private TextField tfEmail;

	@FXML
	private TextField tfEndereco;

	@FXML
	private TextField tfTelefone;

	@FXML
	private Label lblNome;

	@FXML
	private Label lblcpf;

	@FXML
	private Label lblprofissao;

	@FXML
	private Label lblemail;

	@FXML
	private Label lblendreco;

	@FXML
	private Label lbltelefone;

	@FXML
	private Button btnConfimarEdicao;
    @FXML
    void tfMaskCpf() {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("###.###.###-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtBuscarCPF);
    	tff.formatter();

    }

	@FXML
	void tfMaskTelefone() {
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(tfTelefone);
		tff.formatter();
	}

	private ClienteDAO cliDao = new ClienteDAO();
	private List<Cliente> clienteList = cliDao.listarClientes();
	private ObservableList<ClienteTabela> listTabelaCliente = FXCollections.observableArrayList();

	@FXML
	public void pesquisarCliente(ActionEvent event) {
		String cpf = txtBuscarCPF.getText();
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		Cliente cliente = cliDao.buscaClienteByCPF(cpf);
		if (!listTabelaCliente.isEmpty()) {
			listTabelaCliente.clear();
		}
		ClienteTabela cli = new ClienteTabela(cliente.getId(), cliente.getNome(), cliente.getCpf(),
				cliente.getProfissao(), cliente.getEmail(), cliente.getEndereco(), cliente.getTelefone(),
				cliente.getStatus());
		listTabelaCliente.add(cli);

		columnNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Nome"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Cpf"));
		columnProfissao.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Profissao"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Email"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Endereco"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("telefone"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Status"));

		tvClientes.setItems(listTabelaCliente);

	}

	public void chamarEditar() {
		ClienteTabela clienteTabela = tvClientes.getSelectionModel().getSelectedItem();
		tfNome.setText(clienteTabela.getNome());
		tfCpf.setText(clienteTabela.getCpf());
		tfCpf.setEditable(false);
		tfEmail.setText(clienteTabela.getEmail());
		tfEndereco.setText(clienteTabela.getEndereco());
		tfProfissao.setText(clienteTabela.getProfissao());
		tfTelefone.setText(clienteTabela.getTelefone());

	}

	public void editar() {
		Cliente cliente = new Cliente();
		cliente.setNome(tfNome.getText());
		cliente.setCpf(tfCpf.getText());
		cliente.setTelefone(tfTelefone.getText());
		cliente.setEmail(tfEmail.getText());
		cliente.setProfissao(tfProfissao.getText());
		cliente.setEndereco(tfEndereco.getText());

		cliDao.alterarCliente(cliente);
		clienteList = cliDao.listarClientes();
		listarClientes();

	}

	public void listarClientes() {
		if (!listTabelaCliente.isEmpty()) {
			listTabelaCliente.clear();
		}
		for (Cliente cliente : clienteList) {
			ClienteTabela cli = new ClienteTabela(cliente.getId(), cliente.getNome(), cliente.getCpf(),
					cliente.getProfissao(), cliente.getEmail(), cliente.getEndereco(), cliente.getTelefone(),
					cliente.getStatus());
			if (cliente.getStatus().equals("ATIVO")) {
				listTabelaCliente.add(cli);
			}
		}

		columnNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Nome"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Cpf"));
		columnProfissao.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Profissao"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Email"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Endereco"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("telefone"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Status"));

		tvClientes.setItems(listTabelaCliente);
	}



	public void excluir() {
		ClienteTabela cliente = tvClientes.getSelectionModel().getSelectedItem();
		String cpf = cliente.getCpf();
		listTabelaCliente.remove(cliente);
		cliDao.removeCliente(cpf);
		clienteList = cliDao.listarClientes();
		listarClientes();

	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarClientes();

	}
	
	


}
