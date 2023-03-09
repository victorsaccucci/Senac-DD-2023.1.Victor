package Model.vo.telefonia;

import java.util.List;

public class Cliente {
	private String nome;
	private String cpf;
	private List<Telefone> telefones;
	private boolean ativo;
	private Endereco endereco;

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, List<Telefone> telefones, boolean ativo, Endereco endereco) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefones = telefones;
		this.ativo = ativo;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return " nome: " + this.getNome() 
				+ ", cpf: " + this.getCpf() 
				+ ", telefones: " + this.getTelefones() 
				+ ", ativo: " + this.isAtivo()
				+ ", endereco: " + this.getEndereco();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
