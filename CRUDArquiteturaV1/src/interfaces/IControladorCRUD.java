package interfaces;

import java.util.List;

import modelo.Tabela;
import util.Retorno;

public interface IControladorCRUD<TIPO extends Tabela> {
	public List<TIPO> listar();
	public List<TIPO> procurar(TIPO tab);
	public Retorno incluir(TIPO tab);
	public Retorno alterar(TIPO tab);
	public Retorno remover(TIPO objPk);
	public TIPO getObjeto(TIPO objPk);
	public TIPO getNewObjeto();
}
