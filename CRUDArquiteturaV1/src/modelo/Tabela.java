package modelo;

import java.util.ArrayList;
import java.util.List;

import interfaces.IConversor;
import persistencia.DAOGeneric;
import util.Retorno;

public abstract class Tabela {
	private Integer pk;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tabela other = (Tabela) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}
	
	
	/** retorno o nome da tabela para utilizar na persitência
	 * @return nome da tabela
	 */
	public abstract String getTableName();
	
	public abstract String getTableNameLegivel();
	/** retorno o nome da coluna da chave primaria
	 * @return
	 */
	public abstract String getTablePKName();
	/** retorna a lista de valor dos campos
	 * @return
	 */
	public abstract List<Object> getCamposValor();
	
	/** retorna uma lista com o nome dos campos
	 * @return
	 */
	public abstract List<String> getCamposNome();

	protected abstract List<String> getCamposObrigatorios();

	public List<IConversor> getCamposConversorUtil(){
		List<IConversor> list = this.getCamposConversor();
		if(list.size()!=this.getNumerosCampos()){
			
			String msg = "O Método "+this.getClass().getName()+".getCamposConversor()"
					+ " deveria retornar uma lista com "+this.getNumerosCampos()
					+ " item/itens e retornou com "+list.size()+" item/itens!";
			throw new RuntimeException(msg);
			
		}
		return list;
	}

	public List<String> getCamposObrigatoriosUtil(){
		List<String> camposValidos = this.getCamposNome();
		List<String> camposObrigatorios = this.getCamposObrigatorios();
		
		for (String campoNome : camposObrigatorios) {
			boolean encontrou = false;
			for (String campoValido : camposValidos) {
				if(campoNome.equals(campoValido)){
					encontrou = true;
					break;
				}
			}
			if(encontrou== false){
				throw new RuntimeException(
						"Campo "+campoNome+
						" não é um nome válido para:"+
								this.getClass().getName());
			}
		}
		return camposObrigatorios;
				
	}

	/** retorna uma lista com objetos conversores para converter
	 * um string no tipo do atributo.
	 * @return
	 */
	public abstract List<IConversor> getCamposConversor();
	
	/** metodo para configurar os valores do objeto.
	 * ele só é chamado se a lista tiver o número correto de valores
	 * @param list
	 * @return
	 */
	protected abstract Retorno setCamposValor(List<Object> list);
	
	/** metodo utilizado para retornar um novo objeto da tabela implementada
	 * @return
	 */
	public abstract Tabela getNovoObjeto();
	
	public Retorno setCamposValorUtil(List<Object> list){
		Retorno ret = new Retorno(true,"");
		if(list==null){
			ret.setSucesso(false);
			ret.setMensagem("Necessário passar a lista de valores para configura objeto");
			return ret;
		}
		if(list.size()!=this.getNumerosCampos()){
			ret.setSucesso(false);
			ret.setMensagem("Necessário passar a lista de valores para configura objeto com " + this.getNumerosCampos()+" recebemos com "+list.size()+" campos.");
			return ret;
		}
		//metodo abstrato que realmente configura os valores

		List<IConversor> listaConversores = this.getCamposConversorUtil();
		List<String> listaCamposNomes = this.getCamposNome();
		for (int i = 0; i < list.size(); i++) {
			Object valor = list.get(i);
			if(valor instanceof String){
				try{
					valor = listaConversores.get(i).converter((String)valor);
				}catch(RuntimeException e){
					ret = new Retorno(false,
							"Erro de Conversão no campo:"+listaCamposNomes.get(i)+", ERRO:"+e.getMessage());
					return ret;
					
				}
				list.set(i, valor);
			}
		}
		
		ret = this.setCamposValor(list);
		return ret;
	}
	
	
	public int getNumerosCampos(){
		return this.getCamposValor().size();
	}
	

	
	public String toString(){
		List<String> camposNome = this.getCamposNome();
		List<Object> camposValor = this.getCamposValor();
		String resultado = "";
		for(int i=0;i<camposNome.size();i++){
			resultado =resultado + ","+camposNome.get(i)+"="+camposValor.get(i);
		}
		return resultado.substring(1);
	}
	
	public Tabela getDadosExtrangeiro(Tabela tab, Integer pk){
		if(tab==null || pk == null) return null;
		tab.setPk(pk);			
		tab = DAOGeneric.getIntancia().getObjeto(tab);
		return tab;
	}

	public int getColunaPK() {
		int indice = this.getIndiceColuna(this.getTablePKName());
		return indice;
	}

	private int getIndiceColuna(String nomeColuna) {
		int indice = 0;
		List<String> camposNome = this.getCamposNome();
		for (String coluna : camposNome) {
			if(coluna.equals(nomeColuna) == true){
				break;
			}
			indice++;
		}
		return indice;
		
	}
}
