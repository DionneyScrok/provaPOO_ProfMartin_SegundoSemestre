package com.provapoo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.provapoo.db.ConexaoHSQLDB;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;

public class ClienteDAO extends ConexaoHSQLDB {
	final String Insert_SQL_Cliente = " INSERT INTO Cliente (nome, cpf, email, telefone, endereco, profissao ) VALUES (?, ?, ?, ?, ?, ? ) ";
	final String Insert_SQL_ContaCorrente = " INSERT INTO ContaCorrente (saldo, agencia, numConta) VALUES (?, ?, ? ) ";
	final String SQL_SELECT = " SELECT * FROM Cliente ";
	final String SQL_SELECT_CLIENTE_ID = " SELECT * FROM Cliente WHERE ID =? ";
	final String SQL_ALTERA_CLIENTE = " UPDATE Cliente SET NOME =?, CPF =?, EMAIL =?, ENDERECO =?, TELEFONE =?, PROFISSAO=? WHERE ID =? ";
	final String SQL_DELETA_CLIENTE = "DELETE FROM Cliente WHERE ID = ?";
	final String SQL_FIND_CPFCliente = "Select * FROM Cliente WHERE cpf =?";

	public boolean inserirCliente(Cliente cliente) {

		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(Insert_SQL_Cliente);) {
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getEmail());
			pst.setString(4, cliente.getTelefone());
			pst.setNString(5, cliente.getEndereco());
			pst.setNString(6, cliente.getProfissao());
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
		try (Connection connection = connectar(); PreparedStatement pst = connection.prepareStatement(SQL_FIND_CPFCliente);) {

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

}
