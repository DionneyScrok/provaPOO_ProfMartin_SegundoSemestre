package com.provapoo.view;

import java.io.IOException;
import java.util.List;

import com.ClienteTabela.ClienteTabela;
import com.provapoo.DAO.ClienteDAO;
import com.provapoo.model.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	void chamaTelaEdicaoCliente(ActionEvent event) {

	}

	@FXML
	void pesquisarCliente(ActionEvent event) {

	}

	private ClienteDAO cliDao = new ClienteDAO();
	private List<Cliente> clienteList = cliDao.listarClientes();
	private ObservableList<ClienteTabela> listTabelaCliente = FXCollections.observableArrayList();

	public void listarClientes() {
		if (!listTabelaCliente.isEmpty()) {
			listTabelaCliente.clear();
			System.out.println("Limpou a tabela");
		}
		for (Cliente cliente : clienteList) {
			ClienteTabela cli = new ClienteTabela(cliente.getId(), cliente.getNome(), cliente.getCpf(),
					cliente.getProfissao(), cliente.getEmail(), cliente.getEndereco(), cliente.getTelefone());
			listTabelaCliente.add(cli);
		}
		columnNome.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Nome"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Cpf"));
		columnProfissao.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Profissao"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Email"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("Endereco"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<ClienteTabela, String>("telefone"));

		tvClientes.setItems(listTabelaCliente);
	}

	public void chamaAlistaClientes() throws IOException, InterruptedException {

		listarClientes();
	}
	
	public void excluir() {
		ClienteTabela cliente = tvClientes.getSelectionModel().getSelectedItem();
		String cpf = cliente.getCpf();
		listTabelaCliente.remove(cliente);
		cliDao.removeCliente(cpf);
		clienteList = cliDao.listarClientes();
		listarClientes();
		
	}

}
