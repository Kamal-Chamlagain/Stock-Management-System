package bistaAgroFarm;

import java.awt.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductHome {

	JFrame pFrame;
	private JTextField pCost;
	private JTextField pName;

	private JTable pTable;

	public void show() {

		DefaultTableModel sd = (DefaultTableModel) pTable.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {
			String sql = "select* from product";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				String sn = rs.getString(1);
				String name = rs.getString(2);
				String cost = rs.getString(3);

				String[] data = { sn, name, cost, };

				sd.addRow(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void clear() {
		pName.setText("");
		pCost.setText("");

		show();
	}

	public class Get {
		String c0 = pName.getText();
		String c1 = pCost.getText();

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductHome window = new ProductHome();
					window.pFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProductHome() {
		initialize();
		show();

	}

	@SuppressWarnings("serial")
	private void initialize() {

		pFrame = new JFrame();
		pFrame.setUndecorated(true);
		pFrame.getContentPane().setBackground(new Color(255, 200, 0));
		pFrame.setFont(new Font("Cooper Black", Font.BOLD, 13));
		pFrame.setTitle("Bista Agro Farm");
		pFrame.setBounds(600, 300, 805, 421);
		pFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pFrame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Products Detail");
		lblNewLabel.setBounds(315, 67, 167, 31);
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pFrame.getContentPane().add(lblNewLabel);

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(10, 174, 132, 36);
		lblProductName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pFrame.getContentPane().add(lblProductName);

		JLabel lblRate = new JLabel("Rate:");
		lblRate.setBounds(92, 214, 50, 36);
		lblRate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pFrame.getContentPane().add(lblRate);

		pCost = new JTextField();
		pCost.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[1-9][0-9]{0,5}$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(pCost.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					pCost.setText(null);
				} else {
					pCost.getText();
				}

			}
		});
		pCost.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pCost.setBounds(154, 218, 139, 28);
		pFrame.getContentPane().add(pCost);
		pCost.setColumns(10);

		pName = new JTextField();
		pName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pName.setBounds(154, 178, 139, 28);
		pName.setColumns(10);
		pFrame.getContentPane().add(pName);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 111, 798, 2);
		separator.setForeground(new Color(255, 0, 0));
		pFrame.getContentPane().add(separator);

		JButton pAdd = new JButton("Add");
		pAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				pAdd.setCursor(cursor);
			}
		});
		pAdd.setBounds(29, 263, 108, 36);
		pAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Get g = new Get();

				if (g.c0.equals("") || g.c1.equals("")) {

					JOptionPane.showMessageDialog(null, "Please insert all the detail");

				} else {

					try {
						String sql = "select * from product where product_name='" + g.c0 + "'";
						ResultSet rs = DbConnect.s.executeQuery(sql);
						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "product name already exist");

						} else {

							String query = "insert into product(product_name,product_cost) value('" + g.c0 + "','"
									+ g.c1 + "')";
							DbConnect.s.executeUpdate(query);
							JOptionPane.showMessageDialog(null, "Insert successful");

						}
					}

					catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
				clear();

			}
		}

		);
		pAdd.setBackground(Color.LIGHT_GRAY);
		pAdd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pFrame.getContentPane().add(pAdd);

		JButton pUpdate = new JButton("Update");
		pUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				pUpdate.setCursor(cursor);
			}
		});
		pUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int ro = pTable.getSelectedRow();
				Get h = new Get();

				if (ro == -1) {

					JOptionPane.showMessageDialog(null, "Please select row first");
				} else {

					TableModel sd = pTable.getModel();
					String a = sd.getValueAt(ro, 0).toString();
					String b = sd.getValueAt(ro, 1).toString();
					String c = sd.getValueAt(ro, 2).toString();

					if (h.c0.equals(b) && h.c1.equals(c)) {
						JOptionPane.showMessageDialog(null, "Information not changed");

					} else {
						try {

							String query = "update product set product_name='" + h.c0 + "',product_cost='" + h.c1
									+ "' where product_id =" + a;
							DbConnect.s.executeUpdate(query);

							JOptionPane.showMessageDialog(null, "update successful");

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}

					}
					clear();
				}
			}

		});
		pUpdate.setBounds(166, 263, 129, 36);
		pUpdate.setBackground(Color.LIGHT_GRAY);
		pUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pFrame.getContentPane().add(pUpdate);

		JButton pDelete = new JButton("Delete");
		pDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				pDelete.setCursor(cursor);
			}
		});
		pDelete.setBounds(166, 328, 130, 36);
		pDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				int rol = pTable.getSelectedRow();
				if (rol == -1) {
					JOptionPane.showMessageDialog(null, "Please select row first");
				} else {
					TableModel sd = pTable.getModel();
					String a = sd.getValueAt(rol, 0).toString();
					try {

						String query = "delete from product where product_id=" + a;
						if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "CONFIRMATION",
								JOptionPane.YES_NO_OPTION) == 0) {

							DbConnect.s.executeUpdate(query);
							JOptionPane.showMessageDialog(null, "Delete sucessfully");
						}

						clear();

					}

					catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		pDelete.setBackground(Color.LIGHT_GRAY);
		pDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pFrame.getContentPane().add(pDelete);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(390, 174, 323, 195);
		pFrame.getContentPane().add(scrollPane);

		pTable = new JTable();
		pTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel sd = pTable.getModel();
				int i = pTable.getSelectedRow();
				pName.setText(sd.getValueAt(i, 1).toString());
				pCost.setText(sd.getValueAt(i, 2).toString());

			}
		});
		pTable.setBackground(new Color(169, 169, 169));
		scrollPane.setViewportView(pTable);
		pTable.getTableHeader().setReorderingAllowed(false);

		pTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { " Id", "Product Name", "Rate" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		pTable.getColumnModel().getColumn(0).setPreferredWidth(71);
		pTable.getColumnModel().getColumn(0).setMaxWidth(71);
		pTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		pTable.getColumnModel().getColumn(1).setMaxWidth(150);
		pTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		pTable.getColumnModel().getColumn(2).setMaxWidth(100);

		JLabel lblBistaAgroFarm = new JLabel("Bista Agro Farm");
		lblBistaAgroFarm.setForeground(new Color(128, 0, 0));
		lblBistaAgroFarm.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBistaAgroFarm.setBounds(281, 18, 252, 36);
		pFrame.getContentPane().add(lblBistaAgroFarm);

		JLabel lblX = new JLabel("");
		lblX.setIcon(new ImageIcon("F:\\Images\\Sacross.png"));
		lblX.setBackground(Color.GREEN);
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
		lblX.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 25));
		lblX.setBounds(777, 0, 28, 34);
		pFrame.getContentPane().add(lblX);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\Images\\logoP.jpg"));
		lblNewLabel_1.setBounds(161, 0, 108, 111);
		pFrame.getContentPane().add(lblNewLabel_1);

		JButton pClear = new JButton("Clear");
		pClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				pClear.setCursor(cursor);
			}
		});
		pClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		pClear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pClear.setBackground(Color.LIGHT_GRAY);
		pClear.setBounds(28, 328, 109, 36);
		pFrame.getContentPane().add(pClear);

		JLabel back = new JLabel("New label");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home h = new Home();
				h.hFrame.setVisible(true);
				pFrame.dispose();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				back.setCursor(cursor);
			}
		});
		back.setIcon(new ImageIcon("F:\\Images\\Pback.jpg"));
		back.setBounds(0, 0, 56, 55);
		pFrame.getContentPane().add(back);

		JLabel lblRegisterNewProduct = new JLabel("Register new Product");
		lblRegisterNewProduct.setForeground(Color.RED);
		lblRegisterNewProduct.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblRegisterNewProduct.setBounds(276, 126, 185, 30);
		pFrame.getContentPane().add(lblRegisterNewProduct);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pFrame.setState(Frame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label.setCursor(cursor);
			}
		});
		label.setIcon(new ImageIcon("F:\\Images\\mp.png"));
		label.setBounds(744, 3, 35, 31);
		pFrame.getContentPane().add(label);

	}
}
