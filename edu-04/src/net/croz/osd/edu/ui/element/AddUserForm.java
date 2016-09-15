package net.croz.osd.edu.ui.element;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.croz.osd.edu.ui.element.CustomTable.Data;
import net.croz.osd.edu.ui.element.CustomTable.MyTableModel;
import net.croz.osd.edu.util.PostgreSQLJDBCInsert;
import net.croz.osd.edu.util.PostgreSQLJDBCReturn;

@Component
public class AddUserForm extends JDialog {
	public JTextField username;
	public JLabel u;
	public JPasswordField password;
	public JLabel p;
	public JComboBox<String> role;
	public JLabel r;
	public JLabel e;
	public JCheckBox enabled;
	public JButton save;
	public JButton cancel;
	//public JPanel panel;

	@Autowired
	PostgreSQLJDBCInsert ins;
	
	@Autowired
	PostgreSQLJDBCReturn ret;

	public AddUserForm init(MyTableModel model) {

		setTitle("Add New User");
		setModal(true);
		//panel = new JPanel();

		username = new JTextField();

		u = new JLabel("Username :");
		password = new JPasswordField();
		p = new JLabel("Password :");
		role = new JComboBox<>(new String[] { "ROLE_USER", "ROLE_ADMIN" });
		role.setSelectedItem("ROLE_USER");
		r = new JLabel("Role :");
		enabled = new JCheckBox("", true);
		e = new JLabel("Enabled :");
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		setLayout(new GridLayout(6, 2));

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				getContentPane().removeAll();
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
				String pass = bcpe.encode(password.getText());
				
				ins.insertUser(username.getText(),pass , (String) role.getSelectedItem(),
						enabled.isSelected());
				Data newUser = new Data(username.getText(), pass, (String) role.getSelectedItem(),
						enabled.isSelected(), "");

				ret.getDatabase(model);

				model.fireTableDataChanged();
				dispose();
				getContentPane().removeAll();
			}
		});

		add(u);
		add(username);
		add(p);
		add(password);
		add(r);
		add(role);
		add(e);
		add(enabled);
		add(save);
		add(cancel);

		setPreferredSize(new Dimension(400, 210));
		setMinimumSize(new Dimension(400, 210));

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		return this;
	}

}
