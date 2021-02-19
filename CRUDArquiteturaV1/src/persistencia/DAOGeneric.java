package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IDAO;
import modelo.Tabela;
import util.Configuracoes;
import util.Retorno;

public class DAOGeneric implements IDAO {

	protected Connection con;
	private static DAOGeneric dao;

	public static DAOGeneric getIntancia() {
		if (dao == null) {
			dao = new DAOGeneric();
		}
		return dao;
	}

	private DAOGeneric() throws RuntimeException {
		Configuracoes conf = Configuracoes.getInstance();
		String driver = conf.getDriveBd();
		try {
			Class.forName(driver);
			// padrao jdbc ://nomeserver:porta/database
			con = DriverManager.getConnection(conf.getUrlConexao(), conf.getUsuarioBd(), conf.getSenhaBd());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			throw new RuntimeException("DAOFuncionarioSQL erro(1):" + e.getMessage());
		}
	}

	private String getListaCamposNomes(Tabela tab) {
		return this.getListaCamposNomes(tab, false);
	}

	private String getListaCamposNomesInserir(Tabela tab) {
		return this.getListaCamposNomes(tab, true);
	}

	private String getListaCamposNomes(Tabela tab, boolean removerPKname) {
		String lista = "";
		String pkName = tab.getTablePKName();
		if (tab != null) {
			for (String campo : tab.getCamposNome()) {
				if (!removerPKname || !campo.equals(pkName)) {
					lista = lista + "," + campo;
				}
			}
			lista = lista.substring(1);
		}
		return lista;
	}

	@Override
	public List<Tabela> listar(Tabela tab) {
		if (tab == null) {
			throw new RuntimeException("Defina a tabela para usar DAOGenric.Listar");
		}
		String sql = "Select " + this.getListaCamposNomes(tab) + " from " + tab.getTableName();

		// busca apenas o selecionado se informar a pk no objeto
		String strWhere = "";
		if (tab.getPk() != null) {
			strWhere = " where " + tab.getTablePKName() + " = " + tab.getPk();
			sql = sql + strWhere;
		}

		System.out.println("SQL:" + sql);
		PreparedStatement pst;
		ArrayList<Tabela> lista = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Tabela auxTab = tab.getNovoObjeto();
				auxTab.setCamposValorUtil(this.getCamposValores(rs));
				lista.add(auxTab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}

	private List<Object> getCamposValores(ResultSet rs) throws SQLException {
		ArrayList<Object> list = new ArrayList<>();
		int columnCount = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			list.add(rs.getObject(i));
		}
		return list;
	}

	@Override
	public List<Tabela> procurar(Tabela tab) {
		if (tab == null) {
			throw new RuntimeException("Defina a tabela para usar DAOGenric.procurar");
		}
		String sql = "Select " + this.getListaCamposNomes(tab) + " from " + tab.getTableName();

		// busca apenas o selecionado se informar a pk no objeto
		String strWhere = "";
		strWhere = getStringWhereProcurar(tab);
		if (!strWhere.equals("")) {
			strWhere = " where " + strWhere;
			sql = sql + strWhere;
		}

		System.out.println("SQL:" + sql);
		PreparedStatement pst;
		ArrayList<Tabela> lista = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			setPreparedStatementProcurar(tab, pst);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Tabela auxTab = tab.getNovoObjeto();
				auxTab.setCamposValorUtil(this.getCamposValores(rs));
				lista.add(auxTab);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}

		return lista;
	}

	private void setPreparedStatementProcurar(Tabela tab, PreparedStatement pst) throws SQLException {
		List<String> camposNome = tab.getCamposNome();
		List<Object> camposValores = tab.getCamposValor();

		int indTab = 1;
		int indPS = 1;

		for (String campoNome : camposNome) {
			Object campoValor = camposValores.get(indTab - 1);
			if (campoValor != null || validarVazioSeString(campoValor)) {
				if (campoValor instanceof String) {
					campoValor = ((String) campoValor).concat("%");
				} else
					campoValor = trataValoresEspeciaisObjetoParaBanco(campoValor);
				pst.setObject(indPS, campoValor);

				indPS++;
			}
			indTab++;
		}

	}

	private String getStringWhereProcurar(Tabela tab) {
		String strWhere = "";
		List<String> camposNome = tab.getCamposNome();
		List<Object> camposValores = tab.getCamposValor();

		int indTab = 1;

		for (String campoNome : camposNome) {
			Object campoValor = camposValores.get(indTab - 1);
			if (campoValor != null || validarVazioSeString(campoValor)) {
				if (campoValor instanceof String) {
					strWhere = strWhere + " and " + campoNome + " like ?";
				} else {
					strWhere = strWhere + " and " + campoNome + " = ? ";
				}
			}
			indTab++;
		}
		if (strWhere.length() > 0) {
			strWhere = strWhere.substring(4);
		}
		return strWhere;
	}

	private Retorno validarIncluir(Tabela tab) {
		Retorno ret = new Retorno(true, "OK");
		List<String> camposObrigatorios = tab.getCamposObrigatoriosUtil();

		List<Object> camposValores = tab.getCamposValor();

		List<String> camposNomes = tab.getCamposNome();

		int ind = 0;
		for (String nomeCampo : camposNomes) {
			for (String nomeObrigatorio : camposObrigatorios) {
				if (nomeCampo.equals(nomeObrigatorio)) {
					Object valor = camposValores.get(ind);
					if (valor == null || validarVazioSeString(valor)) {
						ret.setSucesso(false);
						ret.setMensagem("Campo:" + nomeCampo + " não está preenchido");
						return ret;
					}

				}
			}
			ind++;
		}
		return ret;
	}

	private boolean validarVazioSeString(Object valor) {
		return valor instanceof String && ((String) valor).equals("");
	}

	@Override
	public Retorno incluir(Tabela tab) {
		if (tab == null) {
			throw new RuntimeException("Defina a tabela para usar DAOGenric.incluir");
		}
		Retorno ret = validarIncluir(tab);
		if (!ret.isSucesso()) {
			return ret;
		}
		String camposNome = this.getListaCamposNomesInserir(tab);
		// string com ?,? para cada valor de campo(sem incluir a PK)
		String valores = getStringValoresPreparedstatementInsert(tab);

		String sql = "insert into " + tab.getTableName() + "(" + camposNome + ")" + " values(" + valores + ")";
		System.out.println("SQL Incluir:" + sql);
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// set os valores para ?,? sem incluir a PK
			setPreparedstatementValores(tab, ps);
			int result = ps.executeUpdate();
			if (result != 1) {
				ret.setSucesso(false);
				ret.setMensagem("Erro ao Incluir");
			}

		} catch (SQLException e) {
			ret.setSucesso(false);
			ret.setMensagem("ERROSQL:" + e.getMessage());
		}
		return ret;
	}

	/**
	 * configura os valores do preparedstatement pulando o valor da PK.
	 * 
	 * @param tab
	 * @param ps
	 * @return retorna um indice da proximo valor a ser configurado na string de ?
	 * @throws SQLException
	 */
	private int setPreparedstatementValores(Tabela tab, PreparedStatement ps) throws SQLException {
		int indPk = getCampoPkIndice(tab);
		// percorre setando os valores e pula o indice da PK
		int indTab = 1;
		int indPS = 1;

		for (Object valor : tab.getCamposValor()) {
			// só seta o valor se o indice não for o da pk
			if (indTab != indPk) {
				valor = trataValoresEspeciaisObjetoParaBanco(valor);
				ps.setObject(indPS, valor);
				indPS++;
			}
			indTab++;
		}
		return indPS;
	}

	private Object trataValoresEspeciaisObjetoParaBanco(Object valor) {
		if (valor instanceof java.util.Date) {
			valor = new java.sql.Date(((java.util.Date) valor).getTime());
		} else if (valor instanceof Tabela) {
			valor = ((Tabela) valor).getPk();
		} else if (valor instanceof Enum<?>) {
			valor = ((Enum<?>) valor).ordinal();
		}
		return valor;
	}

	private String getStringValoresPreparedstatementInsert(Tabela tab) {
		List<Object> camposValor = tab.getCamposValor();
		int qtdCampos = camposValor.size() - 1;
		String valores = "";
		for (int i = 0; i < qtdCampos; i++) {
			valores = valores + ",?";
		}
		valores = valores.substring(1);
		return valores;
	}

	private String getStringValoresPreparedstatementUpdate(Tabela tab) {
		String sqlSet = "";
		int indPk = getCampoPkIndice(tab);
		// percorre setando os valores e pula o indice da PK
		int indTab = 1;
		int indPS = 1;

		for (String campoNome : tab.getCamposNome()) {
			// só seta o valor se o indice não for o da pk
			if (indTab != indPk) {
				sqlSet = sqlSet + "," + campoNome + " = ? ";
				indPS++;
			}
			indTab++;
		}

		sqlSet = sqlSet.substring(1);
		return sqlSet;
	}

	private int getCampoPkIndice(Tabela tab) {
		// verifica o indice da PK para ser pulado
		int indPk = 1;
		for (String campoNome : tab.getCamposNome()) {
			if (campoNome.equals(tab.getTablePKName())) {
				break;
			}
			indPk++;
		}
		return indPk;
	}

	@Override
	public Retorno alterar(Tabela tab) {
		if (tab == null || tab.getPk() == null) {
			throw new RuntimeException("Defina a tabela para usar DAOGenric.alterar");
		}

		Retorno ret = new Retorno(true, "OK");
		String sqlValores = getStringValoresPreparedstatementUpdate(tab);

		String sql = "update " + tab.getTableName() + " " + "set " + sqlValores + "" + " where " + tab.getTablePKName()
				+ " = ?";
		System.out.println("SQL Update:" + sql);

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int indInsert = setPreparedstatementValores(tab, ps);
			ps.setObject(indInsert, tab.getPk());
			int ok = ps.executeUpdate();
			if (ok != 1) {
				ret.setSucesso(false);
				ret.setMensagem("ERRO: nenhuma linha alterada");
			}
		} catch (SQLException e) {
			ret.setSucesso(false);
			ret.setMensagem("ERRO UPDATE:" + e.getMessage());
		}

		return ret;
	}

	@Override
	public Retorno remover(Tabela objPk) {
		if (objPk == null || objPk.getPk() == null) {
			throw new RuntimeException("Defina a tabela para usar DAOGenric.remover");
		}
		Retorno ret = new Retorno(true, "OK");
		String sql = "delete from " + objPk.getTableName() + "" + " where " + objPk.getTablePKName() + " = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1, objPk.getPk());

			int ok = ps.executeUpdate();
			if (ok != 1) {
				ret.setSucesso(false);
				ret.setMensagem("nenhuma linha excluída");
			}
		} catch (SQLException e) {
			ret.setSucesso(false);
			ret.setMensagem("EERRO EXcluir:" + e.getMessage());
		}

		return ret;
	}

	@Override
	public Tabela getObjeto(Tabela objPk) {
		if (objPk == null || objPk.getPk() == null) {
			return null;
		}
		List<Tabela> tab = this.listar(objPk);
		if (tab.size() == 1) {
			return tab.get(0);
		}
		return null;
	}

}
