package Executavel;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.ClienteController;
import Controller.EnderecoController;
import Model.exception.CampoInvalidoException;
import Model.exception.CpfJaUtilizadoException;
import Model.exception.EnderecoInvalidoException;
import Model.vo.telefonia.Cliente;
import Model.vo.telefonia.Endereco;

public class ExecutavelTelefonia {

	public static void main(String[] args) {

		Endereco endereco1 = new EnderecoController().consultarPorId(1);
		Endereco endereco2 = new Endereco(1, null, null, null, null, null, null);

		ClienteController controladorDeClientes = new ClienteController();

		Cliente novoCliente = new Cliente();
		novoCliente.setNome("Mário");
		novoCliente.setCpf("19122233312");
		novoCliente.setEndereco(endereco2);
		novoCliente.setAtivo(true);
		novoCliente.setTelefones(new ArrayList());

		try {
			novoCliente = controladorDeClientes.inserir(novoCliente);

			JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso! Id gerado: " + novoCliente.getId(),
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (CpfJaUtilizadoException | EnderecoInvalidoException | CampoInvalidoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
