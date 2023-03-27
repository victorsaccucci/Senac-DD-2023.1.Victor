package Model.vo.vacina;

public enum TipoPessoaVO {

	PESQUISADOR(1), VOLUNTARIOS(2), PUBLICO(3);

	private int valor;

	private TipoPessoaVO(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public static TipoPessoaVO getTipoUsuarioVOPorValor(int valor) {
		TipoPessoaVO tipoPessoaVO = null;
		for (TipoPessoaVO elemento : TipoPessoaVO.values()) {
			if (elemento.getValor() == valor) {
				tipoPessoaVO = elemento;
			}
		}
		return tipoPessoaVO;
	}

}
