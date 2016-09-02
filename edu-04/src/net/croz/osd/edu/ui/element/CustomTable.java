package net.croz.osd.edu.ui.element;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import net.croz.osd.edu.util.PostgreSQLJDBCDelete;
import net.croz.osd.edu.util.PostgreSQLJDBCReturn;

@org.springframework.stereotype.Component
public class CustomTable extends JPanel {

	public CustomTable() {

		MyTableModel model = new MyTableModel() {
			public Class getColumnClass(int c) {
				switch (c) {
				case 4:
					return Boolean.class;
				default:
					return String.class;
				}
			}
		};
		PostgreSQLJDBCReturn.getDatabase(model);

		JTable table = new JTable(model);
		JButton add = new JButton("Add New User");

		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setMinimumSize(new Dimension(800, 600));
		scrollpane.setPreferredSize(new Dimension(800, 600));
		add(scrollpane, BorderLayout.NORTH);
		add(add, BorderLayout.SOUTH);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AddUserForm.init(model);

			}
		});

		ButtonsRenderer renderer = new ButtonsRenderer();

		table.getColumnModel().getColumn(5).setCellRenderer(renderer);
		table.getColumnModel().getColumn(5).setCellEditor(new ButtonsEditor());
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(5).setCellEditor(new ButtonsEditor());
		table.getColumnModel().getColumn(4).setPreferredWidth(15);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.setRowHeight(
				renderer.getTableCellRendererComponent(table, null, true, true, 0, 0).getPreferredSize().height);

	}

	public JPanel init() {
		CustomTable tt = new CustomTable();
		tt.setVisible(true);

		return tt;
	}

	public static class Data {

		private int id;
		private String username;
		private String password;
		private String role;
		private String application;
		private boolean state;

		public Data(int id, String username, String password, String role, boolean state, String application) {
			this.setId(id);
			this.setUsername(username);
			this.setPassword(password);
			this.setRole(role);
			this.setApplication(application);
			this.setState(state);
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getApplication() {
			return application;
		}

		public void setApplication(String application) {
			this.application = application;
		}

		public boolean isState() {
			return state;
		}

		public void setState(boolean state) {
			this.state = state;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

	}

	public class MyTableModel extends AbstractTableModel {

		private List<Data> data;

		public MyTableModel() {
			data = new ArrayList<>();
		}

		public List<Data> getList() {
			return this.data;
		}

		@Override
		public String getColumnName(int column) {
			String value = null;
			switch (column) {
			case 0:
				value = "ID";
				break;
			case 1:
				value = "Username";
				break;
			case 2:
				value = "Password";
				break;
			case 3:
				value = "Role";
				break;
			case 4:
				value = "Enabled";
				break;
			case 5:
				value = "Actions";
				break;
			}
			return value;
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			Class value = Object.class;
			switch (columnIndex) {
			case 0:
				value = Integer.class;
				break;
			case 1:
				value = String.class;
				break;
			case 2:
				value = String.class;
				break;
			case 3:
				value = String.class;
				break;
			case 4:
				value = Boolean.class;
				break;
			}

			return value;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Data obj = data.get(rowIndex);
			Object value = null;
			switch (columnIndex) {
			case 0:
				value = obj.getId();
				break;
			case 1:
				value = obj.getUsername();
				break;
			case 2:
				value = obj.getPassword();
				break;
			case 3:
				value = obj.getRole();
				break;
			case 4:
				value = obj.isState();
			}
			return value;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if (columnIndex == 5) {

				System.out.println(aValue);

				Data value = data.get(rowIndex);
				if ("edit".equals(aValue)) {
					UserForm.init(this, value);

				} else {

					remove(value);
					PostgreSQLJDBCDelete.deleteUser(value.getId());
					fireTableCellUpdated(rowIndex, columnIndex);
				}

			}
			if (columnIndex == 4) {
				Data value = data.get(rowIndex);
				if ((boolean) aValue == true) {
					value.setState(true);
				} else {
					value.setState(false);
				}
				fireTableCellUpdated(rowIndex, columnIndex);
			}

		}

		public void add(Data value) {
			int startIndex = getRowCount();
			data.add(value);
			fireTableRowsInserted(startIndex, getRowCount() - 1);
		}

		public void remove(Data value) {
			int startIndex = data.indexOf(value);

			data.remove(value);
			fireTableRowsDeleted(startIndex, startIndex);
		}

		public void update(Data oldValue, Data newValue) {

			int startIndex = data.indexOf(oldValue);

			data.set(startIndex, newValue);

		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 4 || columnIndex == 5;
		}
	}

	public class ButtonsPane extends JPanel {

		private JButton edit;
		private JButton remove;
		private String state;

		public ButtonsPane() {
			setLayout(new GridBagLayout());
			edit = new JButton("Edit");
			edit.setActionCommand("edit");
			remove = new JButton("Remove");
			remove.setActionCommand("remove");

			add(edit);
			add(remove);

			ActionListener listener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					state = e.getActionCommand();
					System.out.println("State = " + state);
				}
			};

			edit.addActionListener(listener);

			remove.addActionListener(listener);
		}

		public void addActionListener(ActionListener listener) {
			edit.addActionListener(listener);
			remove.addActionListener(listener);
		}

		public String getState() {
			return state;
		}

	}

	public class ButtonsRenderer extends DefaultTableCellRenderer {

		private ButtonsPane buttonsPane;

		public ButtonsRenderer() {
			buttonsPane = new ButtonsPane();
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				buttonsPane.setBackground(table.getSelectionBackground());
			} else {
				buttonsPane.setBackground(table.getBackground());
			}
			return buttonsPane;
		}
	}

	public class ButtonsEditor extends AbstractCellEditor implements TableCellEditor {

		private ButtonsPane buttonsPane;

		public ButtonsEditor() {
			buttonsPane = new ButtonsPane();
			buttonsPane.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							stopCellEditing();
						}
					});
				}
			});
		}

		@Override
		public Object getCellEditorValue() {
			return buttonsPane.getState();
		}

		@Override
		public boolean isCellEditable(EventObject e) {
			return true;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				buttonsPane.setBackground(table.getSelectionBackground());
			} else {
				buttonsPane.setBackground(table.getBackground());
			}
			return buttonsPane;
		}
	}

}