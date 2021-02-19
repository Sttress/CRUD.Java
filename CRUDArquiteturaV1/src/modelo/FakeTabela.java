package modelo;

import java.util.ArrayList;
import java.util.List;

import conversores.IntegerConversor;
import conversores.StringConversor;
import interfaces.IConversor;
import util.Retorno;

public class FakeTabela extends Tabela {

	String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public String getTableName() {
		return "fake";
	}
	@Override
	public String getTableNameLegivel() {
		return "Tabela Fake";
	}

	@Override
	public String getTablePKName() {
		return "pk";
	}

	@Override
	public List<Object> getCamposValor() {
		ArrayList<Object> list = new ArrayList<>();

		list.add(this.getPk());
		list.add(this.getNome());

		return list;
	}

	@Override
	public List<String> getCamposNome() {
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("pk");
		listNomes.add("nome");
		return listNomes ;
	}

	@Override
	protected Retorno setCamposValor(List<Object> list) {
		Retorno ret = new Retorno(true, "OK");
		try{
			this.setPk((Integer) list.get(0));
			this.setNome((String) list.get(1) );
		}catch(Exception e){
			ret.setSucesso(false);
			ret.setMensagem("Erro ao configura campos, ERROR:"+e.getMessage());
		}
				
		return ret;
	}

	@Override
	public Tabela getNovoObjeto() {
		return new FakeTabela();
	}

	@Override
	protected List<String> getCamposObrigatorios() {
		List<String> list = new ArrayList<>();
		list.add("nome");
		return list;
	}

	@Override
	public List<IConversor> getCamposConversor() {
		ArrayList<IConversor> listNomes = new ArrayList<>();
		listNomes.add(new IntegerConversor());
		listNomes.add(new StringConversor());
		return listNomes ;
	}

}
