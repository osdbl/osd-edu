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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.croz.osd.edu.jdbc.UsersDao;
import net.croz.osd.edu.ui.element.CustomTable.Data;
import net.croz.osd.edu.ui.element.CustomTable.MyTableModel;

@Component
public class EditUserForm extends JDialog {

	public JTextField username;
	public JLabel u;
	public JPasswordField password;
	public JLabel p;
	public JCheckBox roleUser;
	public JCheckBox roleAdmin;
	public JLabel r;
	public JLabel e;
	public JCheckBox enabled;
	public JButton save;
	public JButton cancel;

	@Autowired
	UsersDao usersDao;
	
	@Autowired
	CustomTable customTable;

	public EditUserForm init(MyTableModel model, Data user) {
		
		getContentPane().removeAll();
		setTitle("Edit User");
		setModal(true);
		username = new JTextField(user.getUsername());
		u = new JLabel("Username :");
		password = new JPasswordField(user.getPassword());
		
		p = new JLabel("Password :");
		roleUser=new JCheckBox("User");
		roleAdmin=new JCheckBox("Admin");
		if (user.getRole()==null){
			roleUser.setSelected(false);
			roleAdmin.setSelected(false);
		}
		else{
		if(user.getRole().contains("ROLE_USER"))
			roleUser.setSelected(true);
		if(user.getRole().contains("ROLE_ADMIN"))
			roleAdmin.setSelected(true);
		}
		r = new JLabel("Roles :");
		enabled = new JCheckBox("", user.isState());
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
				if(username.getText().equals("") || password.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
				String s="";
				if(roleUser.isSelected())
					s+="ROLE_USER,";
				if(roleAdmin.isSelected())
					s+="ROLE_ADMIN,";
				if(s.length()>0)
					s=s.substring(0, s.length()-1);
				
				Data updatedUser = new Data(username.getText(), password.getText(), s,	enabled.isSelected(), user.getApplication());
				usersDao.updateUser(customTable.dataToUser(user), customTable.dataToUser(updatedUser));
				customTable.fillModel(model);
				model.fireTableDataChanged();
				dispose();
				getContentPane().removeAll();
			}
			}
		});

		add(u);
		add(username);
		add(p);
		add(password);
		add(r);
		add(roleUser);
		add(new JPanel());
		add(roleAdmin);
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
