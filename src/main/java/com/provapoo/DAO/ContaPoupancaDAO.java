package com.provapoo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.provapoo.db.ConexaoHSQLDB;
import com.provapoo.model.Autenticavel;
import com.provapoo.model.ContaPoupanca;
import com.provapoo.model.GeradorDeContas;
import com.provapoo.model.Tarifas;

public class ContaPoupancaDAO extends ConexaoHSQLDB implements Autenticavel, Tarifas {

	final String Insert_SQL = " INSERT INTO CONTAPOUPANCA (numcontapoup, agencia, saldo, clienteid, senha) VALUES (?, ?, ?, ?, ?) ";
	final String Select_FindByConta = "Select * from CONTAPOUPANCA WHERE numcontapoup = ?";
	final String SQL_UPDATE_SALDO = "UPDATE CONTAPOUPANCA SET SALDO = ? WHERE numcontapoup = ?";

	@Override
	public double tarifaSaque(double valor) {
		// TODO Auto-generated method stub
		return valor - 5;
	}

	@Override
	public double tarifaTransferencia(double valor) {
		// TODO Auto-generated method stub
		return valor - 15;
	}

	@Override
	public boolean autentica(String conta, String senha) {
		ContaPoupanca cp = new ContaPoupanca();
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(Select_FindByConta);) {
			pst.setString(1, conta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cp.setId(rs.getInt("id"));
				cp.setNumConta(rs.getString("numcontapoup"));
				cp.setAgencia(rs.getInt("agencia"));
				cp.setIdCliente(rs.getLong("clienteid"));
				cp.setSaldo(rs.getDouble("saldo"));
				cp.setSenha(rs.getString("senha"));

			}

		} catch (SQLException e) {

		}

		if (cp.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}
	}

	public void inserirCp(ContaPoupanca cp) {
		try (Connection connection = connectar(); PreparedStatement pst = connection.prepareStatement(Insert_SQL);) {
			pst.setString(1, cp.getNumConta());
			pst.setInt(2, cp.getAgencia());
			pst.setDouble(3, cp.getSaldo());
			pst.setLong(4, cp.getIdCliente());
			pst.setNString(5, cp.getSenha());
			pst.executeUpdate();
			GeradorDeContas geraConta = new GeradorDeContas();
			geraConta.geraConta();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ContaPoupanca findByConta(String conta) {
		ContaPoupanca cp = null;
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(Select_FindByConta);) {
			pst.setString(1, conta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cp = new ContaPoupanca();
				cp.setId(rs.getInt("id"));
				cp.setNumConta(rs.getString("numcontapoup"));
				cp.setAgencia(rs.getInt("agencia"));
				cp.setIdCliente(rs.getLong("clienteid"));
				cp.setSaldo(rs.getDouble("saldo"));
			}

		} catch (SQLException e) {

		}
		return cp;

	}

	public void depositaCP(ContaPoupanca cp, double valor) {
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_SALDO);) {
			System.out.println("Entro no deposita");
			pst.setDouble(1, cp.getSaldo() + valor);
			pst.setNString(2, cp.getNumConta());
			pst.executeUpdate();
		} catch (SQLException e) {

		}
	}

	public void sacarCP(ContaPoupanca cp, double valor) {
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_SALDO);) {
			System.out.println("Entro no saque");
			pst.setDouble(1, cp.getSaldo() - tarifaSaque(valor));
			pst.setNString(2, cp.getNumConta());
			pst.executeUpdate();
		} catch (SQLException e) {

		}
	}

}
