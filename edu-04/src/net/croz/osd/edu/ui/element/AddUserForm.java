package net.croz.osd.edu.ui.element;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.croz.osd.edu.ui.element.CustomTable.Data;
import net.croz.osd.edu.ui.element.CustomTable.MyTableModel;
import net.croz.osd.edu.util.PostgreSQLJDBCInsert;

public class AddUserForm extends JFrame {
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

	public AddUserForm(MyTableModel model) {

		super("Add New User Panel");

		JPanel panel = new JPanel();

		username = new JTextField();

		u = new JLabel("Username :");
		password = new JTextField();
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

			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				PostgreSQLJDBCInsert.insertUser(username.getText(), password.getText(), (String) role.getSelectedItem(),
						enabled.isSelected());
				int id = returnId(username.getText());
				Data updatedUser = new Data(id, username.getText(), password.getText(), (String) role.getSelectedItem(),
						enabled.isSelected(), "");

				model.add(updatedUser);

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

	public static void init(MyTableModel model) {

		AddUserForm form = new AddUserForm(model);
		form.setVisible(true);

	}

	public static int returnId(String username) {
		int id = -1;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM db WHERE username='" + username + "';");
			while (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return id;
	}

}
