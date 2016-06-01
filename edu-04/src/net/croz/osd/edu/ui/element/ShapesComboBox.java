package net.croz.osd.edu.ui.element;

import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import net.croz.osd.edu.util.ShapeComboModel;

@org.springframework.stereotype.Component
public class ShapesComboBox extends JComboBox<ShapeComboModel.Item> {

	@SuppressWarnings({ "serial", "unchecked" })
	public JComboBox<ShapeComboModel.Item> init() {
		setModel(new DefaultComboBoxModel<ShapeComboModel.Item>(ShapeComboModel.getModel()));
		setRenderer(new BasicComboBoxRenderer() {
			@SuppressWarnings("rawtypes")
			@Override
        	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    setText(((ShapeComboModel.Item)value).getLabel());
                }   
                return this;
            }
        }); 
		return this;
	}
}
