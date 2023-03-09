package Executavel;

import java.util.ArrayList;
import java.util.List;

import Model.vo.telefonia.Cliente;
import Model.vo.telefonia.Endereco;
import Model.vo.telefonia.Telefone;

public class ExecutavelTelefonia {

	public static void main(String[] args) {

		Endereco endereco1 = new Endereco("8800123", "Mauro Ramos", "10", "Centro", "Florianópolis", "Santa Catarina");

		List<Telefone> telefoneDoSocrates = new ArrayList<Telefone>();
		List<Telefone> telefoneDoPele = new ArrayList<Telefone>();
		List<Cliente> clientes = new ArrayList<Cliente>();

		Telefone telefone1 = new Telefone("48", "32328888", true, false);
		telefoneDoSocrates.add(new Telefone("48", "9888123", true, true));

		Cliente cliente1 = new Cliente("Edson arantes", "11111143434", null, true, endereco1);
		Cliente cliente2 = new Cliente("sócrates Brasileiro", "3333222266", telefoneDoSocrates, true, null);

		clientes.add(cliente1);
		clientes.add(cliente2);

		System.out.println("----------Clientes da Firma----------");
		for (Cliente c : clientes) {
			System.out.println(c.toString());
		}

	}

}
