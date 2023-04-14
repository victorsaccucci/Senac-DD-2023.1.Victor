package Model.vo.telefonia;

public class Endereco {
	private Integer id;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	

	public Endereco() {
		super();
	}

	public Endereco(String cep, String rua, String numero, String bairro, String cidade, String estado) {
		super();
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Endereco(Integer id, String cep, String rua, String numero, String bairro, String cidade, String estado) {
		super();
		this.id = id;
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Cep:" + this.getCep() 
			 + " Rua:" + this.getRua() 
			 + " Número: " + this.getNumero() 
			 + " Bairro:" + this.getBairro() 
			 + " Cidade:" + this.getCidade() 
			 + " Estado:" + this.getEstado();
	}
}
