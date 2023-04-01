package Model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.dao.Banco;
import Model.vo.telefonia.Cliente;
import Model.vo.telefonia.Endereco;

public class ClienteDAO {
	
	public Cliente inserir(Cliente novoCliente) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, CPF, ID_ENDERECO, ATIVO) "
				+ " VALUES (?,?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getCpf());
			stmt.setInt(3, novoCliente.getEndereco().getId());
			stmt.setBoolean(4, novoCliente.isAtivo());
			//stmt.setDate(5, java.sql.Date.valueOf(novoCliente.getDataNascimento()));
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoCliente.setId(resultado.getInt(1));
			}
		}catch(SQLException e) {
			System.out.println("Erro ao inserir novo cliente!" + 
					"\nCausa: " + e.getMessage());
		}
		return novoCliente;
	}
	
	public boolean cpfJaUtilizado(String cpfBuscado) {
		boolean cpfJaUtilizado = false;
		Connection conexao = Banco.getConnection();
		String sql = "SELECT COUNT(*) FROM CLIENTE WHERE CPF = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, cpfBuscado);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;
			}
		}catch(SQLException e) {
			System.out.println("Erro ao verificar uso do CPF " + cpfBuscado 
					+ "\n Causa:" + e.getMessage());
		}
		return cpfJaUtilizado;
	}
	
	public Cliente consultarPorId(int id) {
		Cliente clienteBuscado = null;
		Connection conexao = Banco.getConnection();
		String sql = " select * from cliente "
				   + " where id = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				clienteBuscado = new Cliente();
				clienteBuscado.setId(resultado.getInt("id"));
				clienteBuscado.setNome(resultado.getString("nome"));
				clienteBuscado.setCpf(resultado.getString("cpf"));
				clienteBuscado.setAtivo(resultado.getBoolean("ativo"));

				int idEderecoDoCliente = resultado.getInt("idendereco");
				EnderecoDAO enderecoDAO = new EnderecoDAO();
				Endereco endereco = enderecoDAO.consultarEnderecoPorId(idEderecoDoCliente);
				clienteBuscado.setEndereco(endereco);

				TelefoneDAO telefoneDAO = new TelefoneDAO();
				clienteBuscado.setTelefones(telefoneDAO.consultarPorIdCliente(clienteBuscado.getId()));

			}

		}catch (Exception e) {
			System.out.println("Erro ao buscar cliente com id: " + id 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return clienteBuscado;
	}
}
