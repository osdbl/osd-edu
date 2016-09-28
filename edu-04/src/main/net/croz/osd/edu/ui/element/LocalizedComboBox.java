package net.croz.osd.edu.ui.element;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import net.croz.osd.edu.ui.action.LanguageButtonActionListener;
import net.croz.osd.edu.ui.action.LocaleChangeListener;
import net.croz.osd.edu.ui.model.MessageAwareComboModel;
import net.croz.osd.edu.ui.model.ShapeComboModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") 
public class LocalizedComboBox extends JComboBox<ShapeComboModel.Item> implements LocaleChangeListener {
	MessageAwareComboModel comboModel;
	@Autowired LanguageButtonActionListener languageButtonActionListener;
	
	@SuppressWarnings({ "serial", "unchecked" })
	public JComboBox<ShapeComboModel.Item> init(MessageAwareComboModel comboModel) {
		this.comboModel = comboModel;
		
		setModel(new DefaultComboBoxModel<MessageAwareComboModel.Item>(comboModel.getModel(Locale.getDefault())));
		setRenderer(new BasicComboBoxRenderer() {
			@SuppressWarnings("rawtypes")
			@Override
        	public java.awt.Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    setText(((MessageAwareComboModel.Item)value).label);
                }   
                return this;
            }
        }); 
		languageButtonActionListener.addLocaleChangeListener(this);
		return this;
	}

	@Override
	public void localeChange(Locale locale) {
		setModel(new DefaultComboBoxModel<MessageAwareComboModel.Item>(comboModel.getModel(locale)));
	}
}
