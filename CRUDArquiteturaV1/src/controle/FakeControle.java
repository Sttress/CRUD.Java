package controle;

import modelo.FakeTabela;
import modelo.Tabela;
import util.Retorno;

public class FakeControle extends ControleCRUD {

	@Override
	public Tabela getNewObjeto() {
		return new FakeTabela();
	}

	@Override
	public Retorno validar(Tabela tab, String acao) {
		// TODO Auto-generated method stub
		return null;
	}

}
