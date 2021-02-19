package visao.util;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import modelo.Tabela;

public class ModeloJComboboxModel<MODELO extends Tabela> extends DefaultComboBoxModel {

	private List<MODELO> modelos;

	public ModeloJComboboxModel(List<MODELO> RetornaCargos) {
		this.modelos = RetornaCargos;
	}

	@Override
	public void addElement(Object anObject) {
		this.modelos.add((MODELO) anObject);
		// notifica o combo que o modelo foi alterado.
		this.fireIntervalAdded(this, 0, 0);
	}

	public Object getElementAt(int index) {
		return this.modelos.get(index);
	}

	@Override
	public Object getSelectedItem() {
		return super.getSelectedItem();
	}

	@Override
	public int getSize() {
		return this.modelos.size();
	}
}