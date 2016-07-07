package net.croz.osd.edu.ui.element;

import javax.swing.JTable;

import net.croz.osd.edu.ui.model.MessageAwareOutTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalizedTable extends JTable {
	@Autowired MessageAwareOutTableModel tableModel;

	public JTable init() {
		setModel(tableModel.init());
		getColumnModel().getColumn(0).setPreferredWidth(300);
		getColumnModel().getColumn(1).setPreferredWidth(250);
		setFillsViewportHeight(true);
		return this;
	}
}
