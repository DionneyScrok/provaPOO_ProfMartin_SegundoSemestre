package com.provapoo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.provapoo.db.ConexaoHSQLDB;
import com.provapoo.model.Cliente;

public class ClienteDAO extends ConexaoHSQLDB {
	final String Insert_SQL_Cliente = " INSERT INTO Cliente (nome, cpf, profissao, email, endereco, telefone, status ) VALUES (?, ?, ?, ?, ?, ?, ?) ";
	final String Insert_SQL_ContaCorrente = " INSERT INTO ContaCorrente (saldo, agencia, numConta) VALUES (?, ?, ? ) ";
	final String SQL_SELECT = " SELECT * FROM Cliente ";
	final String SQL_ALTERA_CLIENTE = " UPDATE Cliente SET NOME =?, CPF =?, profissao =?, email =?, endereco =?, telefone =?  WHERE cpf =? ";
	final String SQL_FIND_CPFCliente = "Select * FROM Cliente WHERE cpf =?";
	final String SQL_ALTERA_STATUSATIVO = "UPDATE Cliente Set status = 'ATIVO' where cpf = ?";
	final String SQL_ALTERA_STATUSINATIVO = "UPDATE Cliente Set status = 'INATIVO' where cpf = ?";

	public boolean inserirCliente(Cliente cliente) {

		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(Insert_SQL_Cliente);) {
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setNString(3, cliente.getProfissao());
			pst.setString(4, cliente.getEmail());
			pst.setNString(5, cliente.getEndereco());
			pst.setString(6, cliente.getTelefone());			
			pst.setNString(7, cliente.getStatus());
			pst.executeUpdate();
			System.out.println("Ok, Salvou!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Cliente buscaClienteByCPF(String cpf) {
		Cliente cli = null;
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_FIND_CPFCliente);) {

			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				cli = new Cliente();
				cli.setId(rs.getInt("id"));
				cli.setNome(rs.getString("nome"));
				cli.setCpf(rs.getString("cpf"));
				cli.setEmail(rs.getString("email"));
				cli.setEndereco(rs.getString("endereco"));
				cli.setTelefone(rs.getString("telefone"));
				cli.setProfissao(rs.getString("profissao"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cli;

	}
	


	public List<Cliente> listarClientes() {
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT);) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setProfissao(rs.getString("PROFISSAO"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setEndereco(rs.getString("ENDERECO"));
				cliente.setTelefone(rs.getString("TELEFONE"));
				cliente.setStatus(rs.getString("status"));
				listaCliente.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCliente;
	}
	public boolean alterarCliente(Cliente cliente) {
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_CLIENTE);){
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getProfissao());
			pst.setString(4, cliente.getEmail());
			pst.setString(5, cliente.getEndereco());
			pst.setString(6, cliente.getTelefone());			

			pst.setNString(7, cliente.getCpf());
			pst.executeUpdate();
			System.out.println("Editado com sucesso!");

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public void alteraStatus(String cpf) {
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_STATUSATIVO);) {
			pst.setString(1, cpf);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Deu ruim o alterar status");
		}

		System.out.println("Deu boa o alterar status");

			
	}

	public boolean removeCliente(String cpf) {
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_STATUSINATIVO);) {
			pst.setString(1, cpf);
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}



}
