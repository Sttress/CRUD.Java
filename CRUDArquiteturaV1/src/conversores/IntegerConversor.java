package conversores;

import interfaces.IConversor;

public class IntegerConversor implements IConversor {

	@Override
	public Object converter(String valor) {
		if(valor == null || valor.equals("")) return null;
		Integer retorno ;
		try{
			retorno = Integer.parseInt(valor);
		}catch (NumberFormatException e) {
			throw new RuntimeException("Erro conversão:"+e.getMessage());
		}
		return retorno;
	}

}
