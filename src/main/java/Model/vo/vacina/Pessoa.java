package Model.vo.vacina;

import java.sql.Date;
import java.time.LocalDateTime;

public class Pessoa {
	private int id;
	private String nome;
	private String dtNascimento;
	private String sexo;
	private String cpf;
	private double notaVacina;
	private TipoPessoaVO tipoPessoa;

	public Pessoa(int id, String nome, String dtNascimento, String sexo, String cpf, double notaVacina,
			TipoPessoaVO tipoPessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.notaVacina = notaVacina;
		this.tipoPessoa = tipoPessoa;
	}

	public Pessoa() {
		super();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getNotaVacina() {
		return notaVacina;
	}

	public void setNotaVacina(double notaVacina) {
		this.notaVacina = notaVacina;
	}

	public TipoPessoaVO getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaVO tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public void imprimir() {
		System.out.printf("\n%3d  %-12s  %-20s  %-10s  %-10s  %-10s", 
		this.getId(),
		this.getNome(),
		this.getDtNascimento(),
		this.getSexo(),
		this.getCpf(),
		this.getNotaVacina());
	}
}
