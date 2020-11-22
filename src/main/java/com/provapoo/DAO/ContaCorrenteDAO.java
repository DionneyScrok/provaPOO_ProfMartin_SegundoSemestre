package com.provapoo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.provapoo.db.ConexaoHSQLDB;
import com.provapoo.model.Autenticavel;
import com.provapoo.model.Cliente;
import com.provapoo.model.ContaCorrente;
import com.provapoo.model.Tarifas;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ContaCorrenteDAO extends ConexaoHSQLDB implements Tarifas, Autenticavel {

	final String Insert_SQL = " INSERT INTO CONTACORRENTE (numcontacor, agencia, saldo, clienteid, senha) VALUES (?, ?, ?, ?, ?)";
	final String SQL_SELECT = " SELECT * FROM CONTACORRENTE ";
	final String SQL_SELECT_Cc_ID = " SELECT * FROM CONTA WHERE ID =? ";
	final String SQL_ALTERA_Cc = " UPDATE CONTA SET NOME =?, CPF =?, EMAIL =?, ENDERECO =?, TELEFONE =?, PROFISSAO=? WHERE ID =? ";
	final String SQL_DELETA_Cc = "DELETE FROM CONTA WHERE ID = ?";
	final String SQL_FIND_CPFCliente = "Select * FROM Cliente WHERE cpf =?";
	final String SQL_FIND_CPF_CC = "Select * FROM ContaCorrente WHERE numcontacor =?";
	final String SQL_UPDATE_SALDO = "UPDATE CONTACORRENTE SET SALDO = ? WHERE numcontacor = ?";
	final String Select_FindByConta = "Select * from CONTACORRENTE WHERE numcontacor = ?";


	
	public ContaCorrente findByNumeroConta(String conta) {
		ContaCorrente cc = null;
		try (Connection connection = connectar(); PreparedStatement pst = connection.prepareStatement(SQL_FIND_CPF_CC);){
			pst.setString(1, conta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cc = new ContaCorrente();
				cc.setId(rs.getInt("id"));
				cc.setNumConta(rs.getString("numcontacor"));
				cc.setAgencia(rs.getInt("agencia"));
				cc.setSaldo(rs.getDouble("saldo"));
				cc.setSenha(rs.getNString("senha"));
			}
			
		}catch(SQLException e) {
			
		}
		return cc;
		
	}

	public void inserirCC(ContaCorrente cc) {
		try (Connection connection = connectar(); PreparedStatement pst = connection.prepareStatement(Insert_SQL);) {
			pst.setString(1, cc.getNumConta());
			pst.setInt(2, cc.getAgencia());
			pst.setDouble(3, cc.getSaldo());
			pst.setLong(4, cc.getIdCliente());
			pst.setNString(5, cc.getSenha());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void controlaStatus() {
		
		
		
	}

	public void depositaCC(ContaCorrente cc, double valor) {
		try (Connection connection = connectar(); PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_SALDO);){
			System.out.println("Entro no deposita");
			pst.setDouble(1, cc.getSaldo() + valor);
			pst.setNString(2, cc.getNumConta());
			pst.executeUpdate();						
		}
		catch(SQLException e) {
			
		}
	}

	public void sacarCC(ContaCorrente cc, double valor) {
		try (Connection connection = connectar(); PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_SALDO);){
			System.out.println("Entro no sacar");
			pst.setDouble(1, cc.getSaldo() - valor);
			pst.setNString(2, cc.getNumConta());
			pst.executeUpdate();						
		}
		catch(SQLException e) {
			
		}
		
	}

	@Override
	public boolean autentica(String conta, String senha) {
		ContaCorrente cc = new ContaCorrente();
		cc = findByNumeroConta(conta);
		System.out.println(cc.getSenha() + " / " + senha );
		if (cc.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public double tarifaSaque(double valor) {
		
		return valor + 8;
	}

	@Override
	public double tarifaTransferencia(double valor) {
		// TODO Auto-generated method stub
		return valor + 20;
	}

	public List<ContaCorrente> listarContaCorrente() {
		List<ContaCorrente> listaCc = new ArrayList<ContaCorrente>();
		try (Connection connection = this.connectar();
				PreparedStatement pst = connection.prepareStatement(SQL_SELECT);) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ContaCorrente Cc = new ContaCorrente();
				Cc.setId(rs.getInt("ID"));
				Cc.setAgencia(rs.getInt("AGENCIA"));
				Cc.setNumConta(rs.getString("NUMCONTACOR"));
				Cc.setSaldo(rs.getDouble("SALDO"));
				Cc.setIdCliente(rs.getInt("CLIENTEID"));
				Cc.setSenha(rs.getNString("SENHA"));
					
				listaCc.add(Cc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCc;
	}
	private void alerta(String title, String text, String header) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();

	}

}
