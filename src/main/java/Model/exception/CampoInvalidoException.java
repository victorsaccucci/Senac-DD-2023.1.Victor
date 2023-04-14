package Model.exception;

public class CampoInvalidoException extends Exception{
	
	private static final long serialVersionUID = 3507543191105337427L;
	public CampoInvalidoException (String mensagem) {
		super(mensagem);
	}
}
