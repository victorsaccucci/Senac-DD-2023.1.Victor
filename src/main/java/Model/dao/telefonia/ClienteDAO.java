package Model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.dao.Banco;
import Model.vo.telefonia.Cliente;
import Model.vo.telefonia.Endereco;

public class ClienteDAO {

	public Cliente inserir(Cliente novoCliente) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, CPF, ID_ENDERECO, ATIVO) " + " VALUES (?,?,?,?) ";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getCpf());
			stmt.setInt(3, novoCliente.getEndereco().getId());
			stmt.setBoolean(4, novoCliente.isAtivo());
			// stmt.setDate(5, java.sql.Date.valueOf(novoCliente.getDataNascimento()));
			stmt.execute();

			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novoCliente.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente!" + "\nCausa: " + e.getMessage());
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

			if (resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar uso do CPF " + cpfBuscado + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return cpfJaUtilizado;
	}

	public Cliente consultarPorId(int id) {
		Cliente clienteBuscado = null;
		Connection conexao = Banco.getConnection();
		String sql = " select * from cliente " + " where id = ? ";
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

		} catch (Exception e) {
			System.out.println("Erro ao buscar cliente com id: " + id + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return clienteBuscado;
	}

	private Cliente montarClienteComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Cliente clienteBuscado = new Cliente();
		clienteBuscado.setId(resultado.getInt("id"));
		clienteBuscado.setNome(resultado.getString("nome"));
		clienteBuscado.setCpf(resultado.getString("cpf"));
		clienteBuscado.setAtivo(resultado.getBoolean("ativo"));

		int idEnderecoDoCliente = resultado.getInt("id_endereco");
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = enderecoDAO.consultarEnderecoPorId(idEnderecoDoCliente);
		clienteBuscado.setEndereco(endereco);

		TelefoneDAO telefoneDAO = new TelefoneDAO();
		clienteBuscado.setTelefones(telefoneDAO.consultarPorIdCliente(clienteBuscado.getId()));

		return clienteBuscado;
	}

	public List<Cliente> consultarTodos() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from cliente ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				Cliente clienteBuscado = montarClienteComResultadoDoBanco(resultado);
				clientes.add(clienteBuscado);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os clientes. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return clientes;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM CLIENTE WHERE ID= " + id;
		Statement stmt = Banco.getStatement(conn);

		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		boolean excluiu = quantidadeLinhasAfetadas > 0;

		if (excluiu) {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.desativarTelefones(id);
		}

		return excluiu;
	}

	public boolean atualizar(Cliente cliente) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE CLIENTE SET NOME=?, CPF=?, ID_ENDERECO=?, ATIVO=? " + " WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setInt(3, cliente.getEndereco().getId());
			stmt.setBoolean(4, cliente.isAtivo());
			stmt.setInt(5, cliente.getId());
			registrosAlterados = stmt.executeUpdate();

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.ativarTelefones(cliente.getId(), cliente.getTelefones());
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public int contarClientesQueResidemNoEndereco(Integer idEndereco) {
		int totalClientesDoEnderecoBuscado = 0;
		Connection conexao = Banco.getConnection();
		String slq = " SELECT COUNT(ID) FROM CLIENTE" + " WHERE ID_ENDERECO = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, slq);
		try {
			query.setInt(1, idEndereco);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				totalClientesDoEnderecoBuscado = resultado.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao contar os clientes que residem em um endere�o. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return totalClientesDoEnderecoBuscado;
	}

}
