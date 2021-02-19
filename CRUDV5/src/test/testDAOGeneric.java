package test;

import interfaces.IDAO;
import modelo.Tabela;
import modelo.Tipo;
import modelo.Viagem;
import persistencia.DAOGeneric;
import util.Retorno;

public class testDAOGeneric {
	public static void main(String[] args) {
		IDAO dao = DAOGeneric.getIntancia();
		Viagem v = new Viagem();
		for (Tabela tabela : dao.listar(v)) {
			System.out.println("tabela:"+tabela);
		}
		
		Tipo t = new Tipo();
		for (Tabela tabela2 : dao.listar(t)) {
			System.out.println("tabela:"+tabela2);
		}
		Tipo t2 = new Tipo();
		t2.setPk(3);
		t2 = (Tipo)dao.getObjeto(t2);
		System.out.println(t2);
		
		Tipo t3 = new Tipo();
		t3.setNome("Tipo teste1");
		t3.setAtivo(true);
		Retorno ret = dao.incluir(t3);
		System.out.println("Ret:"+ret.isSucesso()+" Mensg:"+ret.getMensagem());
		Tipo t4 = new Tipo();
		t4.setNome("");
		t4.setAtivo(true);
		Retorno ret2 = dao.incluir(t4);
		System.out.println("Ret2:"+ret2.isSucesso()+" Mensg:"+ret2.getMensagem());
		Tipo t5 = new Tipo();
		t5.setNome(null);
		t5.setAtivo(true);
		Retorno ret3 = dao.incluir(t5);
		System.out.println("Ret3:"+ret3.isSucesso()+" Mensg:"+ret3.getMensagem());
		
		t5.setPk(6);
		Retorno ret4 = dao.remover(t5);
		System.out.println("Ret4:"+ret4.isSucesso()+" Mensg:"+ret4.getMensagem());
		
		Tipo t6 = new Tipo();
		t6.setPk(3);
		t6 = (Tipo) dao.getObjeto(t6);
		if(t6.getNome().equals("Teste")){
			t6.setNome("Teste alterado");
		}else{
			t6.setNome("Teste");
		}
		Retorno ret5 = dao.alterar(t6);
		System.out.println("Ret5:"+ret5.isSucesso()+" Mensg:"+ret5.getMensagem());
		System.out.println("OBJ:"+t6);
		
		Tipo t7 = new Tipo();
		t7.setNome("T");
		for (Tabela tabela2 : dao.procurar(t7)) {
			System.out.println("tabela:"+tabela2);
		}
		
		Tipo t8 = new Tipo();
		t8.setAtivo(false);
		for (Tabela tabela2 : dao.procurar(t8)) {
			System.out.println("tabela:"+tabela2);
		}
	}
}
