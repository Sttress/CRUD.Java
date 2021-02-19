package modelo;

import java.util.ArrayList;
import java.util.List;

import conversores.BooleanConversor;
import conversores.IntegerConversor;
import conversores.StringConversor;
import interfaces.IConversor;
import util.Retorno;

public class Lugar extends Tabela {
	String nome;
	Boolean ativo;

	public String getNome() {
		return nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String getTableName() {
		return "lugar";
	}
	
	@Override
	public String getTableNameLegivel() {
		return "Lugar";
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
		list.add(this.getAtivo());

		return list;
	}

	@Override
	public List<String> getCamposNome() {
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("pk");
		listNomes.add("nome");
		listNomes.add("ativo");
		return listNomes ;
	}

	@Override
	protected Retorno setCamposValor(List<Object> list) {
		Retorno ret = new Retorno(true, "OK");
		try{
			this.setPk((Integer) list.get(0));
			this.setNome((String) list.get(1) );
			this.setAtivo((Boolean) list.get(2) );
		}catch(Exception e){
			ret.setSucesso(false);
			ret.setMensagem("Erro ao configura campos, ERROR:"+e.getMessage());
		}
				
		return ret;
	}

	@Override
	public Tabela getNovoObjeto() {
		return new Lugar();
	}

	@Override
	protected List<String> getCamposObrigatorios() {
		List<String> list = new ArrayList<>();
		list.add("nome");
		return list;
	}

	@Override
	public List<IConversor> getCamposConversor() {
		List<IConversor> list = new ArrayList<>();
		list.add(new IntegerConversor());
		list.add(new StringConversor());
		list.add(new BooleanConversor());
		
		return list;
	}

}
