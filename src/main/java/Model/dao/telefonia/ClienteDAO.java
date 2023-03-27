package Model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.dao.Banco;
import Model.vo.telefonia.Cliente;
import Model.vo.telefonia.Endereco;

public class ClienteDAO {
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
