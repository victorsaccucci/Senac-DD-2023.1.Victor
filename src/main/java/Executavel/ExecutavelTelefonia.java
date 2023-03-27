package Executavel;

import java.util.ArrayList;
import java.util.List;

import Model.dao.PessoaDAO;
import Model.dao.telefonia.EnderecoDAO;
import Model.dao.telefonia.TelefoneDAO;
import Model.vo.telefonia.Cliente;
import Model.vo.telefonia.Endereco;
import Model.vo.telefonia.Telefone;
import Model.vo.vacina.Pessoa;
import Model.vo.vacina.TipoPessoaVO;

public class ExecutavelTelefonia {

	public static void main(String[] args) {

//		// Endereço
//		Endereco endereco1 = new Endereco("8800123", "Nereu Ramos", "10", "Centro", "Florianópolis", "SC");
//		EnderecoDAO dbaEnderecos = new EnderecoDAO();
//		dbaEnderecos.inserir(endereco1);
		
		

		
		// inserir pessoa
		Pessoa pessoa1 = new Pessoa(1, "victor", "fsf", "sdfsdf", "null", 5, TipoPessoaVO.getTipoUsuarioVOPorValor(1));
		PessoaDAO dbaPessoas = new PessoaDAO();
		dbaPessoas.inserir(pessoa1);
		
		if (pessoa1.getId() != 0) {
		System.out.println("Nova pessoa cadastrada!");
		} else {
		System.out.println("Erro ao cadastrar pessoa.");
		}

//		if (endereco1.getId() != null) {
//			System.out.println("Novo endereço cadastrado!");
//		} else {
//			System.out.println("Erro ao cadastrar endereço.");
//		}
//
//		// consultar endereço por id
//		Endereco enderecoQueJaExiste = dbaEnderecos.consultarEnderecoPorId(7);
//		enderecoQueJaExiste.setRua("Nereu Ramos");
//		System.out.println(enderecoQueJaExiste);
//
//		// Excluir endereco
//		boolean excluiu = dbaEnderecos.excluir(11);
//		if (excluiu) {
//			System.out.println("Endereço excluiudo!");
//		} else {
////			System.out.println("Erro ao cadastrar endereço!");
//		}
//
//		// Atualizar endereço
//		boolean atualizou = dbaEnderecos.atualizar(enderecoQueJaExiste);
//
//		if (atualizou) {
//			System.out.println("Endereço cadastrado!");
//		} else {
//			System.out.println("Erro ao cadastrar endereço!");
//		}
//		
//		//consultar todos telefones
//		System.out.println(dbaEnderecos.consultarTodos());
//
//		// Telefone
//		Telefone telefone1 = new Telefone(5, null, "065", "988282328", true, false);
//		Telefone telefone2 = new Telefone(6, null, "042", "988282328", true, false);
//		TelefoneDAO dbaTelefone = new TelefoneDAO();
//
//		dbaTelefone.inserir(telefone2);
//		if (telefone1.getId() != null) {
//			System.out.println("Telefone cadastrado!");
//		} else {
//			System.out.println("Erro ao cadastrar telefone!");
//		}
//
//		// consultar telefone por id
//		Telefone telefoneQueJaExiste = dbaTelefone.consultaTelefonePorId(54);
//		telefoneQueJaExiste.setDdd("999");
//		System.out.println(telefoneQueJaExiste);
//
//		// atualizar telefone
//		boolean atualizou1 = dbaTelefone.atualizar(telefoneQueJaExiste);
//		if (atualizou1) {
//			System.out.println("Telefone atualizado!");
//		} else {
//			System.out.println("Erro ao atualizar telefone!");
//		}
//
//		// Excluir telefones
//		boolean excluiu2 = dbaEnderecos.excluir(10);
//		if (excluiu2) {
//			System.out.println("Telefone excluiudo!");
//		} else {
//			System.out.println("Erro ao excluir telefone!");
//		}
//
//		// List<Telefone> telefoneDoSocrates = new ArrayList<Telefone>();
//		// List<Telefone> telefoneDoPele = new ArrayList<Telefone>();
//		// List<Cliente> clientes = new ArrayList<Cliente>();
//
//		// Telefone telefone1 = new Telefone("48", "32328888", true, false, null, null);
//		// telefoneDoSocrates.add(new Telefone("48", "9888123", true, true, null,
//		// null));
//
//		// Cliente cliente1 = new Cliente("Edson arantes", "11111143434", null, true,
//		// endereco1);
//		// Cliente cliente2 = new Cliente("sócrates Brasileiro", "3333222266",
//		// telefoneDoSocrates, true, null);
//
//		// clientes.add(cliente1);
//		// clientes.add(cliente2);
//
//		// System.out.println("----------Clientes da Firma----------");
//		// for (Cliente c : clientes) {
//		// System.out.println(c.toString());
//		// }
//
//	}
//
	}
}
