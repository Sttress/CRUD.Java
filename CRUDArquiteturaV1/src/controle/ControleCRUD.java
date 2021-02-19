package controle;

import java.util.List;

import interfaces.IControladorCRUD;
import interfaces.IDAO;
import modelo.Tabela;
import persistencia.DAOGeneric;
import util.Retorno;

public abstract class ControleCRUD<TIPO extends Tabela> implements IControladorCRUD<TIPO> {
	private IDAO dao;
	
	public static final String ACAO_INCLUIR="ACAO_INCLUIR";
	public static final String ACAO_ALTERAR="ACAO_ALTERAR";
	

	public ControleCRUD() {
		dao = DAOGeneric.getIntancia();
	}
	
	public IDAO getDAO() {
		return this.dao;
	}
	
	@Override
	public List<TIPO> listar() {
		return dao.listar(this.getNewObjeto());
	}

	@Override
	public List<TIPO> procurar(TIPO tab) {
		List<TIPO> list = dao.procurar(tab);
		return list;
	}

	@Override
	public Retorno incluir(TIPO tab) {
		Retorno validarcao = this.validar(tab, ACAO_INCLUIR);
		if(validarcao!=null && !validarcao.isSucesso()) return validarcao;
		Retorno retorno = dao.incluir(tab);
		return retorno;
	}

	@Override
	public Retorno alterar(TIPO tab) {
		Retorno validacao = this.validar(tab, ACAO_ALTERAR);
		if(validacao!=null && !validacao.isSucesso()) return validacao;
		Retorno retorno = dao.alterar(tab);
		return retorno;
	}

	@Override
	public Retorno remover(TIPO objPk) {
		return dao.remover(objPk);
	}

	@Override
	public TIPO getObjeto(TIPO objPk) {
		return dao.getObjeto(objPk);
		
	}

	@Override
	public abstract TIPO getNewObjeto();
	
	public abstract Retorno validar(TIPO tab, String acao);

}
