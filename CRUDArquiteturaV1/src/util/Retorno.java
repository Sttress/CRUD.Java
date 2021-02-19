package util;

public class Retorno {
	private boolean sucesso;
	private String mensagem;
	
	public Retorno(boolean sucesso, String mensagem){
		setSucesso(sucesso);
		setMensagem(mensagem);
	}
	
	public boolean isSucesso() {
		return sucesso;
	}
	public String getMensagem() {
		if(this.mensagem == null) this.mensagem = "";
		return mensagem;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
