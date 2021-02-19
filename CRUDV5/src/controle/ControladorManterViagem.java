package controle;

import modelo.Viagem;
import util.Retorno;

public class ControladorManterViagem extends ControleCRUD<Viagem> {

	@Override
	public Viagem getNewObjeto() {
		return new Viagem();
	}

	@Override
	public Retorno validar(Viagem tab, String acao) {
		// TODO Auto-generated method stub
		return null;
	}

}
