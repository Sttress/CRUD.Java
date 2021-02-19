package conversores;

import interfaces.IConversor;

public class StringConversor implements IConversor {

	@Override
	public Object converter(String valor) {
		return valor;
	}

}
