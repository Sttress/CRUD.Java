package conversores;

import interfaces.IConversor;

public class BooleanConversor implements IConversor {

	@Override
	public Object converter(String valor) {
		if(valor == null || valor.equals("")) return null;
		Boolean retorno ;
		try{
			if(valor.equalsIgnoreCase("verdadeiro")){
				retorno = new Boolean(true);
			}else{
				retorno = new Boolean(false);
			}
		}catch (Exception e) {
			throw new RuntimeException("Erro conversão:"+e.getMessage());
		}
		return retorno;
	}

}
