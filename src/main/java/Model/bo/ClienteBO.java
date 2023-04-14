package Model.bo;

import java.util.List;

import Model.dao.telefonia.ClienteDAO;
import Model.exception.CpfAlteradoException;
import Model.exception.CpfJaUtilizadoException;
import Model.exception.EnderecoInvalidoException;
import Model.exception.TelefoneAssociadoAoClienteException;
import Model.vo.telefonia.Cliente;

public class ClienteBO {

	private ClienteDAO dao = new ClienteDAO();

	public Cliente inserir(Cliente novoCliente) throws CpfJaUtilizadoException, EnderecoInvalidoException {
		if (dao.cpfJaUtilizado(novoCliente.getCpf())) {
			throw new CpfJaUtilizadoException("CPF informado já foi utilizado");
		}

		validarEndereco(novoCliente);

		return dao.inserir(novoCliente);
	}

	public boolean atualizar(Cliente clienteAlterado) throws EnderecoInvalidoException, CpfAlteradoException {
		Cliente clienteOriginal = dao.consultarPorId(clienteAlterado.getId());

		if (clienteAlterado.getCpf() != clienteOriginal.getCpf()) {
			throw new CpfAlteradoException("CPF não pode ser alterado");
		}

		validarEndereco(clienteAlterado);

		return dao.atualizar(clienteAlterado);
	}

	public boolean excluir(int id) throws TelefoneAssociadoAoClienteException {
		Cliente cliente = dao.consultarPorId(id);
		if (cliente.getTelefones() != null && !cliente.getTelefones().isEmpty()) {
			throw new TelefoneAssociadoAoClienteException("Cliente possui algum telefone associado");
		}
		return dao.excluir(id);
	}

	public Cliente consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Cliente> consultarTodos() {
		return dao.consultarTodos();
	}

	private void validarEndereco(Cliente cliente) throws EnderecoInvalidoException {
		if (cliente.getEndereco() == null || cliente.getEndereco().getId() == null) {
			throw new EnderecoInvalidoException("Endereço não informado");
		}
	}
}
