package controle;

import modelo.Lugar;
import util.Retorno;

public class ControladorManterLugar extends ControleCRUD<Lugar> {
	

	@Override
	public Lugar getNewObjeto() {
		return new Lugar();
	}

	@Override
	public Retorno validar(Lugar tab, String acao) {
		Retorno ret = new Retorno(true, "");
		if( tab.getNome()!=null && 
			tab.getNome().length()<3
			) {
			ret.setSucesso(false);
			ret.setMensagem("Nome deve ter mais de 3 ou mais caracteres");
		}
		return ret;
	}
	
	public Retorno inativar(Lugar tab) {
		Retorno ret = new Retorno(false,"Já está inativo");
		if(tab.getAtivo().booleanValue()) {
			tab.setAtivo(false);
			ret = this.getDAO().alterar(tab);
			if(ret.isSucesso()) {
				ret.setMensagem("Inativado com Sucesso");
			}
		}
		return ret;
	}

}
