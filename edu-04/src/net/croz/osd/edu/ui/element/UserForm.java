package net.croz.osd.edu.ui.element;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.croz.osd.edu.ui.element.CustomTable.Data;
import net.croz.osd.edu.ui.element.CustomTable.MyTableModel;
import net.croz.osd.edu.util.PostgreSQLJDBCUpdate;

public class UserForm extends JFrame {

	public JTextField username;
	public JLabel u;
	public JTextField password;
	public JLabel p;
	public JComboBox<String> role;
	public JLabel r;
	public JLabel e;
	public JCheckBox enabled;
	public JButton save;
	public JButton cancel;
	//public static Data updatedUser;

	public UserForm(MyTableModel model,Data user) {
		

		super("Edit User Panel");
		
		JPanel panel = new JPanel();

		username = new JTextField(user.getUsername());

		u = new JLabel("Username :");
		password = new JTextField(user.getPassword());
		p = new JLabel("Password :");
		role = new JComboBox<>(new String[] { "ROLE_USER", "ROLE_ADMIN" });
		role.setSelectedItem(user.getRole());
		r = new JLabel("Role :");
		enabled = new JCheckBox("", user.isState());
		e = new JLabel("Enabled :");
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		setLayout(new GridLayout(6, 2));
		//updatedUser = new Data(user.getId(), user.getUsername(), user.getPassword(), user.getRole(), user.isState(),user.getApplication());
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				Data updatedUser = new Data(user.getId(), username.getText(), password.getText(), (String)role.getSelectedItem(), enabled.isSelected(),user.getApplication());
				PostgreSQLJDBCUpdate.updateUser(updatedUser);
				model.update(user,updatedUser);
				
				model.fireTableDataChanged();
				dispose();
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

		getContentPane().add(panel);
		setPreferredSize(new Dimension(400, 210));
		setMinimumSize(new Dimension(400, 210));

		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void init(MyTableModel model,Data user) {

		UserForm form = new UserForm(model,user);
		form.setVisible(true);

	}

}
