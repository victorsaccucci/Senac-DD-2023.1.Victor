package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.vo.vacina.Pessoa;

public class PessoaDAO {
	public Pessoa inserir(Pessoa novaPessoa) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO PESSOA (NOME, DTNASCIMENTO, SEXO, CPF, NOTAVACINA, TIPOPESSOA) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			query.setString(1, novaPessoa.getNome());
			query.setString(2, novaPessoa.getDtNascimento());
			query.setString(3, novaPessoa.getSexo());
			query.setString(4, novaPessoa.getCpf());
			query.setDouble(5, novaPessoa.getNotaVacina());
			query.setInt(6, novaPessoa.getTipoPessoa().getValor());
			query.execute();

			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir usuário!" + "\nCausa: " + e.getMessage());
		} finally {
			Banco.closeConnection(conexao);
			Banco.closePreparedStatement(query);
		}
		return novaPessoa;
	}
}
