package com.provapoo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.provapoo.db.ConexaoHSQLDB;
import com.provapoo.model.Autenticavel;
import com.provapoo.model.ContaPoupanca;
import com.provapoo.model.Tarifas;

public class ContaPoupancaDAO extends ConexaoHSQLDB implements Tarifas, Autenticavel {

	final String Insert_SQL = " INSERT INTO CONTACORRENTE (numcontacor, agencia, saldo, clienteid) VALUES (?, ?, ?, ?) ";
	final String SQL_FIND_CPF_CP = "Select * FROM CONTAPOUPANCA WHERE numcontacor =?";
	final String SQL_UPDATE_SALDO = "UPDATE CONTAPOUPANCA SET SALDO = ? WHERE numcontacor = ?";

	@Override
	public boolean autentica(String numConta, String senha) {
		ContaPoupanca cp = null;
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_FIND_CPF_CP);){

			
		}catch(SQLException e) {
			
		}
		return false;
	}

	@Override
	public void tarifaSaque() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tarifaTransferencia() {
		// TODO Auto-generated method stub

	}

	public ContaPoupanca findyByConta(String conta) {
		ContaPoupanca cp = null;
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_FIND_CPF_CP);) {
			pst.setString(1, conta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cp = new ContaPoupanca();
				cp.setId(rs.getInt("id"));
				cp.setNumConta(rs.getString("numcontacor"));
				cp.setAgencia(rs.getInt("agencia"));
				cp.setSaldo(rs.getDouble("saldo"));
			}

		} catch (SQLException e) {

		}
		return cp;

	}

	public void deposita(ContaPoupanca cp, double valor) {
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_SALDO);) {
			System.out.println("Entro no deposita");
			pst.setDouble(1, cp.getSaldo() + valor);
			pst.setNString(2, cp.getNumConta());
			pst.executeUpdate();
		} catch (SQLException e) {

		}
	}

	public void sacar(ContaPoupanca cp, double valor) {
		try (Connection connection = connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_SALDO);) {
			System.out.println("Entro no sacar");
			pst.setDouble(1, cp.getSaldo() - valor);
			pst.setNString(2, cp.getNumConta());
			pst.executeUpdate();
		} catch (SQLException e) {

		}

	}
}
