package Model.vo.vacina;

import java.time.LocalDateTime;

public class Vacina {
	private int idVacina;
	private String nome;
	private String paisOrigem;
	private String estagio;
	private LocalDateTime dataPesquisa;
	private Pessoa pesquisador;

	public Vacina(int idVacina, String nome, String paisOrigem, String estagio, LocalDateTime dataPesquisa,
			Pessoa pesquisador) {
		super();
		this.idVacina = idVacina;
		this.nome = nome;
		this.paisOrigem = paisOrigem;
		this.estagio = estagio;
		this.dataPesquisa = dataPesquisa;
		this.pesquisador = pesquisador;
	}

	public Vacina() {
		super();
	}

	public int getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public String getEstagio() {
		return estagio;
	}

	public void setEstagio(String estagio) {
		this.estagio = estagio;
	}

	public LocalDateTime getDataPesquisa() {
		return dataPesquisa;
	}

	public void setDataPesquisa(LocalDateTime dataPesquisa) {
		this.dataPesquisa = dataPesquisa;
	}

	public Pessoa getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pessoa pesquisador) {
		this.pesquisador = pesquisador;
	}

	@Override
	public String toString() {
		return "id Vacina: " + this.getIdVacina() 
				+ "\nNome: " + this.getNome() 
				+ "\nPaís Origem: " + this.getPaisOrigem() 
				+ "\nEstágio: " + this.getEstagio()
				+ "\nData Pesquisa:" + this.getDataPesquisa() 
				+ "\nPesquisador: " + this.getPesquisador();
	}
}
