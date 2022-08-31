package bistaAgroFarm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomerHome {

	JFrame cFrame;
	private JTextField cName;
	private JTextField cAddress;
	private JTextField cPhone;
	private JLabel lblLabel;
	private JTable cTable;

	public void display() {

		DefaultTableModel d = (DefaultTableModel) cTable.getModel();
		int r = d.getRowCount();
		// delete the previous table data
		while (r-- != 0) {
			d.removeRow(r);
		}
		try {
			String sql = "select* from customer";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				String sn = rs.getString(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String phone = rs.getString(4);
				String[] data = { sn, name, address, phone };

				d.addRow(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void clear1() {
		cName.setText("");
		cAddress.setText("");
		cPhone.setText("");
		display();
	}

	public class Fetch {
		String t0 = cName.getText();
		String t1 = cAddress.getText();
		String t2 = cPhone.getText();
	}

	public CustomerHome() {
		initialize();
		display();

	}

	@SuppressWarnings("serial")
	private void initialize() {
		cFrame = new JFrame();
		cFrame.setUndecorated(true);
		cFrame.setBounds(550, 300, 948, 445);
		cFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 0, 948, 445);
		cFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblX = new JLabel("");
		lblX.setIcon(new ImageIcon("F:\\Images\\Sacross.png"));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?",
						"CONFIRMATION", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				lblX.setCursor(cursor);
			}
		});
		lblX.setForeground(Color.RED);
		lblX.setFont(new Font("Malgun Gothic", Font.BOLD, 25));
		lblX.setBounds(920, 0, 28, 32);
		panel.add(lblX);

		lblLabel = new JLabel("Customer Details");
		lblLabel.setForeground(new Color(220, 20, 60));
		lblLabel.setBorder(null);
		lblLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel.setBackground(Color.DARK_GRAY);
		lblLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblLabel.setBounds(338, 77, 234, 44);
		panel.add(lblLabel);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(12, 64, 378, -13);
		panel.add(toolBar);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(79, 174, 56, 35);
		panel.add(lblNewLabel);

		cName = new JTextField();
		cName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[a-zA-Z\\s]*$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(cName.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					cName.setText(null);
				}

			}
		});
		cName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cName.setBounds(143, 175, 198, 32);
		panel.add(cName);
		cName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Address:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(58, 235, 77, 35);
		panel.add(lblNewLabel_1);

		cAddress = new JTextField();
		cAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String pattern = "^[0-9a-zA-Z\\s,-]+$";

				Pattern patt = Pattern.compile(pattern);
				Matcher match = patt.matcher(cAddress.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					cAddress.setText(null);
				}

			}
		});
		cAddress.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cAddress.setBounds(143, 236, 198, 32);
		panel.add(cAddress);
		cAddress.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Phone Number:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(6, 292, 136, 35);
		panel.add(lblNewLabel_2);

		cPhone = new JTextField();

		cPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[9][0-9]{0,9}$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(cPhone.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					cPhone.setText(null);
				}

			}
		});
		cPhone.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cPhone.setBounds(143, 294, 198, 32);
		panel.add(cPhone);
		cPhone.setColumns(10);

		JButton cAdd = new JButton("ADD");
		cAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				cAdd.setCursor(cursor);
			}
		});
		cAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fetch f = new Fetch();
				if (f.t0.equals("") || f.t1.equals("") || f.t2.equals("")) {
					JOptionPane.showMessageDialog(null, "Please insert all field");
				} else if (f.t2.length() < 10) {
					JOptionPane.showMessageDialog(null, "Phone number should be 10 digit");
				} else {
					try {
						String sql = "select * from customer where cName='" + f.t0 + "' and cPhone='" + f.t2 + "'";
						ResultSet rs = DbConnect.s.executeQuery(sql);
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "customer already exist");

						} else {

							String sql1 = "select * from customer where cPhone='" + f.t2 + "'";
							ResultSet rs1 = DbConnect.s.executeQuery(sql1);
							if (rs1.next()) {
								JOptionPane.showMessageDialog(null, "Phone number already exist");

							} else {
								String query = "insert into customer(cName,cAddress,cPhone) value('" + f.t0 + "','"
										+ f.t1 + "','" + f.t2 + "')";
								DbConnect.s.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Insert successful");

							}
						}
						clear1();
					}

					catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}

				}

			}
		});
		cAdd.setBackground(Color.LIGHT_GRAY);
		cAdd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cAdd.setBounds(71, 340, 98, 35);
		panel.add(cAdd);

		JButton cUpdate = new JButton("UPDATE");
		cUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				cUpdate.setCursor(cursor);
			}
		});
		cUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ro = cTable.getSelectedRow();
				Fetch h = new Fetch();

				if (ro == -1) {

					JOptionPane.showMessageDialog(null, "Please select row first");
				} else {

					TableModel sd = cTable.getModel();
					String a = sd.getValueAt(ro, 0).toString();
					String b = sd.getValueAt(ro, 1).toString();
					String c = sd.getValueAt(ro, 2).toString();
					String d = sd.getValueAt(ro, 3).toString();

					if (h.t0.equals(b) && h.t1.equals(c) && h.t2.equals(d)) {
						JOptionPane.showMessageDialog(null, "Information not changed");
					} else {
						try {

							String query = "update customer set cName='" + h.t0 + "',cAddress='" + h.t1 + "',cPhone='"
									+ h.t2 + "' where cid =" + a;
							DbConnect.s.executeUpdate(query);

							JOptionPane.showMessageDialog(null, "update successful");

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}

					}
					clear1();
				}

			}
		});
		cUpdate.setBackground(Color.LIGHT_GRAY);
		cUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cUpdate.setBounds(205, 340, 136, 35);
		panel.add(cUpdate);

		JButton cDelete = new JButton("DELETE");
		cDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				cDelete.setCursor(cursor);
			}
		});
		cDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rl = cTable.getSelectedRow();
				if (rl == -1) {
					JOptionPane.showMessageDialog(null, "Please select row first");
				} else {
					TableModel d = cTable.getModel();
					String a = d.getValueAt(rl, 0).toString();
					try {

						if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "CONFIRMATION",
								JOptionPane.YES_NO_OPTION) == 0) {

							String query = "delete from customer where cid=" + a;
							DbConnect.s.executeUpdate(query);
							JOptionPane.showMessageDialog(null, "Delete sucessfully");
						}

						clear1();

					}

					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			}
		});
		cDelete.setBackground(Color.LIGHT_GRAY);
		cDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cDelete.setBounds(205, 388, 136, 35);
		panel.add(cDelete);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("F:\\Images\\LogoC.jpg"));
		label_1.setBounds(213, 0, 113, 123);
		panel.add(label_1);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.ORANGE);
		separator.setBounds(-26, 121, 971, 2);
		panel.add(separator);

		JLabel lblBistaAgroFarm = new JLabel("Bista Agro Farm");
		lblBistaAgroFarm.setForeground(new Color(128, 0, 0));
		lblBistaAgroFarm.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBistaAgroFarm.setBounds(338, 13, 252, 36);
		panel.add(lblBistaAgroFarm);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(383, 174, 529, 250);
		panel.add(scrollPane);

		cTable = new JTable();
		cTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel sd = cTable.getModel();
				int i = cTable.getSelectedRow();
				cName.setText(sd.getValueAt(i, 1).toString());
				cAddress.setText(sd.getValueAt(i, 2).toString());
				cPhone.setText(sd.getValueAt(i, 3).toString());
			}
		});
		cTable.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(cTable);
		cTable.getTableHeader().setReorderingAllowed(false);

		cTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Customer Name", "Address", "Phone Number" }

		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		cTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		cTable.getColumnModel().getColumn(0).setMaxWidth(60);
		cTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		cTable.getColumnModel().getColumn(1).setMaxWidth(200);
		cTable.getColumnModel().getColumn(2).setPreferredWidth(120);
		cTable.getColumnModel().getColumn(2).setMaxWidth(120);
		cTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		cTable.getColumnModel().getColumn(3).setMaxWidth(150);

		JButton cClear = new JButton("Clear");
		cClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				cClear.setCursor(cursor);
			}
		});
		cClear.setBackground(Color.LIGHT_GRAY);
		cClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear1();
			}
		});
		cClear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		cClear.setBounds(71, 388, 98, 32);
		panel.add(cClear);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("F:\\Images\\Cback.jpg"));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Home h = new Home();
				h.hFrame.setVisible(true);
				cFrame.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				lblNewLabel_3.setCursor(cursor);
			}
		});
		lblNewLabel_3.setBounds(0, 0, 50, 51);
		panel.add(lblNewLabel_3);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cFrame.setState(Frame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label.setCursor(cursor);
			}
		});
		label.setIcon(new ImageIcon("F:\\Images\\mc.png"));
		label.setBounds(887, 0, 35, 32);
		panel.add(label);
	}
}
