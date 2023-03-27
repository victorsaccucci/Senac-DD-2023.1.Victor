package Model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.dao.Banco;
import Model.vo.telefonia.Endereco;
import Model.vo.telefonia.Telefone;

public class TelefoneDAO {
	public Telefone inserir(Telefone novoTelefone) {
		
		//Concetar ao banco
		Connection conexao = Banco.getConnection();
		String sql = "INSERT INTO TELEFONE (DDD, NUMERO, ATIVO, MOVEL)"
				+ "VALUES(?, ?, ?, ?)";
		
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		//Executar o insert
		try {
			query.setString(1, novoTelefone.getDdd());
			query.setString(2, novoTelefone.getNumero());
			query.setBoolean(3, novoTelefone.isAtivo());
			query.setBoolean(4, novoTelefone.isMovel());
			query.execute();
			
		//Preencher o id
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoTelefone.setId(resultado.getInt(1));
			}
			
		}catch(SQLException e){
			System.out.println("Erro ao inserir o novo telefone!" + "\nCausa:" + e);
		}finally {
		//Fechar conexão
			Banco.closeConnection(conexao);
			Banco.closePreparedStatement(query);
		}
		return novoTelefone;
	}
	
	public boolean atualizar(Telefone telefoneEditado) {
		
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE TELEFONE "
				+ " SET DDD = ?, NUMERO = ?, ATIVO = ?, MOVEL = ? "
				+ " WHERE ID = ? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setObject(1, telefoneEditado.getDdd());
			query.setObject(2, telefoneEditado.getNumero());
			query.setObject(3, telefoneEditado.isAtivo());
			query.setObject(4, telefoneEditado.isMovel());
			query.setInt(5, telefoneEditado.getId());
			
			int quantidadeDeLinhasAtualizadas = query.executeUpdate();
			atualizou = quantidadeDeLinhasAtualizadas > 0;
			
		}catch(SQLException e) {
			System.out.println("Erro ao atualizar o telefone!"
					+ "\nCausa: " + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
	
	return atualizou;
	}

	public Telefone consultaTelefonePorId(int id) {
		Telefone telefoneConsultado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE "
				   + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				telefoneConsultado = new Telefone();
				telefoneConsultado.setId(resultado.getInt("Ddd"));
				telefoneConsultado.setNumero(resultado.getString("Numero"));
				telefoneConsultado.setNumero(resultado.getString("Ativo"));
				telefoneConsultado.setNumero(resultado.getString("Movel"));
				telefoneConsultado.setIdCliente(resultado.getInt("idCliente"));
				}	

		}catch(SQLException e) {
			System.out.println("Erro ao executar a consultar do telefone!"
					+ "\nCausa: " + e.getMessage());
		}finally{
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
	return telefoneConsultado;
	}
	
	public boolean excluir(int id) {
		boolean excluiu = false;
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM TELEFONE "
				   + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			int quantidadeDeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeDeLinhasAtualizadas > 0;
			
		}catch(SQLException excecao) {
			System.out.println("Erro ao atualizar telefone!"
					+ "\n Causa: " + excecao.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return excluiu;
	}

	public List<Telefone> consultarPorIdCliente(Integer id) {
		List<Telefone> telefones = new ArrayList<Telefone>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				Telefone telefoneConsultado = conververterDeResultSetParaEntidade(resultado);
				telefones.add(telefoneConsultado);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultuar todos endereços." + "\nCausa: " + e.getMessage());
		}
		return telefones;
	}
}
