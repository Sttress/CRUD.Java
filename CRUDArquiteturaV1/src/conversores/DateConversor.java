package conversores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.IConversor;

public class DateConversor implements IConversor {

	@Override
	public Object converter(String valor) {
		if(valor == null || valor.equals("")) return null;
		Date retorno ;
		try{
			retorno = new SimpleDateFormat("dd/MM/yyyy").parse(valor);
		}catch (Exception e) {
			throw new RuntimeException("Erro conversão:"+e.getMessage());
		}
		return retorno;
	}

}
