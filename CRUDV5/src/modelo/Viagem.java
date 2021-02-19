package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import conversores.DateConversor;
import conversores.IntegerConversor;
import conversores.StringConversor;
import interfaces.IConversor;
import persistencia.DAOGeneric;
import util.EstatusViagem;
import util.Retorno;

public class Viagem extends Tabela {
	private Date dataSolicitacao;
	private Date dataAprovacaoSolicitacao;
	private Date dataPartida;
	private Date dataRetorno;
	private Date dataAcerto;
	private Date dataAprovacaoAcerto;
	private EstatusViagem estatus;
	private String motivo;
	private Integer dias;
	//dados extrangeiros
	private Tipo tipo;


	@Override
	public List<Object> getCamposValor() {
		ArrayList<Object> list = new ArrayList<>();

		list.add(this.getPk());
		list.add(this.getDataSolicitacao());
		list.add(this.getDataAprovacaoSolicitacao());
		list.add(this.getDataPartida());
		list.add(this.getDataAcerto());
		list.add(this.getDataAprovacaoAcerto());
		list.add(this.getEstatus());
		list.add(this.getMotivo());
		list.add(this.getDias());
		list.add(this.getTipo());

		return list;
	}
	
	@Override
	public Tabela getNovoObjeto() {
		return new Viagem();
	}

	@Override
	public List<String> getCamposNome(){
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("pk");
		listNomes.add("dataSolicitacao");
		listNomes.add("dataAprovacaoSolicitacao");
		listNomes.add("dataPartida");
		listNomes.add("dataAcerto");
		listNomes.add("dataAprovacaoAcerto");
		listNomes.add("estatus");
		listNomes.add("motivo");
		listNomes.add("dias");
		listNomes.add("tipo");
		return listNomes ;
	}

	@Override
	protected Retorno setCamposValor(List<Object> list) {
		Retorno ret = new Retorno(true, "OK");
		try{
			this.setPk((Integer) list.get(0));
			this.setDataSolicitacao((Date) list.get(1) );
			this.setDataAprovacaoSolicitacao((Date) list.get(2) );
			this.setDataPartida((Date) list.get(3) );
			this.setDataAcerto((Date) list.get(4) );
			this.setDataAprovacaoAcerto((Date) list.get(5) );
			this.setEstatus((Integer) list.get(6) );
			this.setMotivo((String) list.get(7) );
			this.setDias((Integer) list.get(8) );
			
			//tem que tratar para dados estrangeiros
			Tipo t = new Tipo();
			t = (Tipo) this.getDadosExtrangeiro(t, (Integer)list.get(9));
			
			this.setTipo(t); 
		}catch(Exception e){
			ret.setSucesso(false);
			ret.setMensagem("Erro ao configura campos, ERROR:"+e.getMessage());
		}
				
		return ret;
	}
	
	


	@Override
	public String getTableName() {
		return "Viagem";
	}
	
	@Override
	public String getTableNameLegivel() {
		return "Viagem";
	}

	@Override
	public String getTablePKName() {
		return "pk";
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public Date getDataAprovacaoSolicitacao() {
		return dataAprovacaoSolicitacao;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public Date getDataAcerto() {
		return dataAcerto;
	}

	public Date getDataAprovacaoAcerto() {
		return dataAprovacaoAcerto;
	}

	public EstatusViagem getEstatus() {
		return estatus;
	}

	public String getMotivo() {
		return motivo;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public void setDataAprovacaoSolicitacao(Date dataAprovacaoSolicitacao) {
		this.dataAprovacaoSolicitacao = dataAprovacaoSolicitacao;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public void setDataAcerto(Date dataAcerto) {
		this.dataAcerto = dataAcerto;
	}

	public void setDataAprovacaoAcerto(Date dataAprovacaoAcerto) {
		this.dataAprovacaoAcerto = dataAprovacaoAcerto;
	}

	public void setEstatus(EstatusViagem estatus) {
		this.estatus = estatus;
	}
	public void setEstatus(Integer estatus){
		if(estatus==null) return;
		this.estatus = EstatusViagem.getFromEstatusViagem(estatus);
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	protected List<String> getCamposObrigatorios() {
		List<String> list = new ArrayList<>();
		list.add("dataSolicitacao");
		list.add("dias");
		list.add("motivo");
		return list;
	}

	@Override
	public List<IConversor> getCamposConversor() {
		ArrayList<IConversor> listConversor = new ArrayList<>();
		listConversor.add(new IntegerConversor());//pk
		listConversor.add(new DateConversor());
		listConversor.add(new DateConversor());
		listConversor.add(new DateConversor());
		listConversor.add(new DateConversor());
		listConversor.add(new DateConversor());
		listConversor.add(new IntegerConversor());
		listConversor.add(new StringConversor());
		listConversor.add(new IntegerConversor());
		listConversor.add(new IntegerConversor());
		return listConversor ;
	}
}
