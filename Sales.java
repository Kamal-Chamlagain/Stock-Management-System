package bistaAgroFarm;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;

public class Sales {

	JFrame frame;
	private JPanel bPanel;
	private JTextField textDate;
	private JTextField sAddress;
	private JTextField sContact;
	private JTextField sQuantity;
	private JTextField sRate;
	private JComboBox<String> sProduct, sCustomer, sType;
	private JTextField sTotal;
	private JTable saleTable;
	private JTextField textField;

	public class Text {

		int count = 0;
		String l = textDate.getText();
		String m = (String) sCustomer.getSelectedItem();
		String n = sAddress.getText();
		String o = sContact.getText();
		String p = (String) sProduct.getSelectedItem();
		String q = sQuantity.getText();
		String r = sRate.getText();
		String t = (String) sType.getSelectedItem();
		int p1 = Integer.parseInt(q);
		int p2 = Integer.parseInt(r);
		int s = p1 * p2;

	}

	public void showTransaction() {

		DefaultTableModel sd = (DefaultTableModel) saleTable.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {
			String sql = "select* from stable";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				String sn = rs.getString(1);
				String date = rs.getString(2);
				String cname = rs.getString(3);
				String cadd = rs.getString(4);
				String cphone = rs.getString(5);
				String type = rs.getString(6);
				String pname = rs.getString(7);
				String rate = rs.getString(8);
				String quantity = rs.getString(9);
				String total = rs.getString(10);
				String[] data = { sn, date, cname, cadd, cphone, pname, rate, quantity, total, type };

				sd.addRow(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void clear() {
		sAddress.setText("");
		sContact.setText("");
		sQuantity.setText("");
		sRate.setText("");
		sTotal.setText("");
		sType.setSelectedItem(null);
		sCustomer.setSelectedItem(null);
		sProduct.setSelectedItem(null);
	}

	public void curDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		textDate.setText(dtf.format(now));
	}

	public void displayItems() {
		try {
			String sql = "select * from product";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				sProduct.addItem(rs.getString("product_name"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void getPRate() {
		String g = (String) sProduct.getSelectedItem();
		String s = "select * from product where product_name='" + g + "'";
		String rate = "";
		try {
			ResultSet os = DbConnect.s.executeQuery(s);
			if (os.next()) {
				rate = os.getString("Product_cost");
				sRate.setText(rate);

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);

		}
	}

	public void getCDetail() {
		String m = (String) sCustomer.getSelectedItem();
		String s = "select * from customer where cName='" + m + "'";
		String phone = "", address = "";
		try {
			ResultSet os = DbConnect.s.executeQuery(s);
			if (os.next()) {
				phone = os.getString("cPhone");
				address = os.getString("cAddress");
				sContact.setText(phone);
				sAddress.setText(address);

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);

		}
	}

	public void displayName() {

		try {
			String sql = "select * from customer";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				sCustomer.addItem(rs.getString("cName"));

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public Sales() {
		initialize();
		displayName();
		displayItems();
		showTransaction();
		clear();
	}

	public void search(String str) {
		DefaultTableModel model = (DefaultTableModel) saleTable.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		saleTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}

	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(400, 250, 1234, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		bPanel = new JPanel();
		bPanel.setBackground(new Color(192, 192, 192));
		bPanel.setBounds(0, 0, 1234, 565);
		frame.getContentPane().add(bPanel);
		bPanel.setLayout(null);

		JLabel lblBistaAgroFarm = new JLabel("Bista Agro Farm");
		lblBistaAgroFarm.setForeground(new Color(128, 0, 0));
		lblBistaAgroFarm.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBistaAgroFarm.setBounds(524, 13, 252, 36);
		bPanel.add(lblBistaAgroFarm);

		JLabel lblDailySales = new JLabel("Daily Sales");
		lblDailySales.setForeground(new Color(0, 0, 205));
		lblDailySales.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblDailySales.setBounds(571, 88, 159, 29);
		bPanel.add(lblDailySales);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 69, 0));
		separator.setBounds(0, 122, 1236, 2);
		bPanel.add(separator);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setBounds(122, 149, 44, 16);
		bPanel.add(lblDate);

		JLabel lblCuName = new JLabel("Customer Name:");
		lblCuName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCuName.setForeground(new Color(0, 0, 0));
		lblCuName.setBounds(45, 189, 112, 16);
		bPanel.add(lblCuName);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAddress.setBounds(95, 225, 68, 25);
		bPanel.add(lblAddress);

		JLabel lblConNUM = new JLabel("Contact No:");
		lblConNUM.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblConNUM.setBounds(73, 269, 87, 16);
		bPanel.add(lblConNUM);

		JLabel lblNewLabel_6 = new JLabel("Items:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_6.setBounds(110, 350, 56, 14);
		bPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Quantity:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_7.setBounds(91, 429, 68, 16);
		bPanel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Rate:");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_8.setBounds(115, 389, 44, 16);
		bPanel.add(lblNewLabel_8);

		textDate = new JTextField();
		textDate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textDate.setBounds(172, 145, 126, 24);
		bPanel.add(textDate);
		textDate.setColumns(10);
		curDateTime();
		textDate.setEditable(false);

		sCustomer = new JComboBox<String>();
		sCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sCustomer.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				getCDetail();
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				getCDetail();
			}
		});

		sCustomer.setBounds(172, 185, 126, 24);
		bPanel.add(sCustomer);

		sAddress = new JTextField();
		sAddress.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sAddress.setEditable(false);
		sAddress.setBounds(172, 225, 126, 24);
		bPanel.add(sAddress);
		sAddress.setColumns(10);

		sContact = new JTextField();
		sContact.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sContact.setEditable(false);

		sContact.setBounds(172, 265, 126, 24);
		bPanel.add(sContact);
		sContact.setColumns(10);

		sQuantity = new JTextField();
		sQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[1-9][0-9]{0,6}$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(sQuantity.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					sQuantity.setText(null);
					sTotal.setText("");

				} else {
					sQuantity.getText();
					Text t = new Text();

					sTotal.setText("" + t.s);
				}

			}

		});
		sQuantity.setBounds(172, 425, 126, 24);
		bPanel.add(sQuantity);
		sQuantity.setColumns(10);

		sRate = new JTextField();
		sRate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sRate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[1-9][0-9]{0,6}$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(sRate.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					sRate.setText(null);
				} else {
					sRate.getText();
				}
			}
		});
		sRate.setBounds(172, 385, 126, 24);
		bPanel.add(sRate);
		sRate.setColumns(10);

		sProduct = new JComboBox<String>();
		sProduct.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				getPRate();
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});

		sProduct.setFont(new Font("Times new roman", Font.PLAIN, 15));
		sProduct.setBounds(172, 345, 126, 24);
		bPanel.add(sProduct);

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				btnSave.setCursor(cursor);
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String l = textDate.getText();
				String m = (String) sCustomer.getSelectedItem();
				String n = sAddress.getText();
				String o = sContact.getText();
				String p = (String) sProduct.getSelectedItem();
				String q = sQuantity.getText();
				String r = sRate.getText();
				String t = (String) sType.getSelectedItem();
				String u = sTotal.getText();

				if (l.equals("") || n.equals("") || o.equals("") || q.equals("") || r.equals("") || u.equals("")
						|| m == null || t == null || p == null) {

					JOptionPane.showMessageDialog(null, "Please insert all the field");
				}

				else {
					try {
						int n0 = Integer.parseInt(q);
						int n1 = 0;
						String sql = "Select * from product where product_name='" + p + "'";
						ResultSet rs = DbConnect.s.executeQuery(sql);
						if (rs.next()) {
							n1 = rs.getInt("product_quantity");

						}

						if (n0 <= n1) {
							String sql1 = "UPDATE product SET product_quantity=" + (n1 - n0) + " WHERE product_name='"
									+ p + "'";
							String query = "Insert into stable(Date,cName,cAddress,cPhone,tType,pName,pRate,pQuantity,Total) Values('"
									+ l + "','" + m + "','" + n + "','" + o + "','" + t + "','" + p + "','" + r + "','"
									+ q + "','" + u + "')";
							String sql6 = "insert into statement(date,pName,pOut) values('" + l + "','" + p + "','" + q
									+ "')";
							DbConnect.s.executeUpdate(sql6);
							DbConnect.s.executeUpdate(sql1);
							DbConnect.s.executeUpdate(query);

							JOptionPane.showMessageDialog(null, "Successful");
							showTransaction();

							if (JOptionPane.showConfirmDialog(null, "Do you want to get the receipt?", "CONFIRMATION",
									JOptionPane.YES_NO_OPTION) == 0) {
								BillingSystem s = new BillingSystem();
								s.bFrame.setVisible(true);
								frame.dispose();

								BillingSystem.customer.setText(m);
								BillingSystem.customer.setEditable(false);
								BillingSystem.txtAddress.setText(n);
								BillingSystem.txtAddress.setEditable(false);
								BillingSystem.txtContact.setText(o);
								BillingSystem.txtContact.setEditable(false);
								BillingSystem.product.setSelectedItem(p);
								BillingSystem.textRate.setText(r);
								BillingSystem.textRate.setEditable(false);
								BillingSystem.txtQuantity.setText(q);
								BillingSystem.txtQuantity.setEditable(false);
								BillingSystem.txtTotal.setText(u);
								BillingSystem.txtTotal.setEditable(false);

							}

							clear();
						} else {
							JOptionPane.showMessageDialog(null, "Stock  not Available (only " + n1 + " stocks left)");

						}
					}

					catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}

			}

		});

		btnSave.setBounds(172, 512, 89, 23);
		bPanel.add(btnSave);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTotal.setBounds(113, 469, 44, 16);
		bPanel.add(lblTotal);

		sTotal = new JTextField();
		sTotal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sTotal.setEditable(false);
		sTotal.setBounds(172, 465, 126, 24);
		bPanel.add(sTotal);
		sTotal.setColumns(10);

		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblType.setBounds(115, 309, 56, 16);
		bPanel.add(lblType);

		sType = new JComboBox<String>();
		sType.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		sType.setBounds(172, 305, 126, 24);
		bPanel.add(sType);
		sType.addItem("Cash");
		sType.addItem("Due");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(309, 145, 911, 348);
		bPanel.add(scrollPane);

		saleTable = new JTable();
		scrollPane.setViewportView(saleTable);
		saleTable.getTableHeader().setReorderingAllowed(false);
		saleTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Date", "Customer Name",
				"Address", "Phone Number", "Product Name", "Rate", "Quantity", "Total", "Type" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String skey = textField.getText();
				search(skey);
			}
		});
		textField.setBounds(587, 512, 116, 22);
		bPanel.add(textField);
		textField.setColumns(10);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearch.setBounds(519, 515, 56, 16);
		bPanel.add(lblSearch);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("F:\\Bista\\sales.jpg"));
		label.setBounds(403, 6, 109, 112);
		bPanel.add(label);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Home h = new Home();
				h.hFrame.setVisible(true);
				frame.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_1.setCursor(cursor);
			}
		});
		label_1.setIcon(new ImageIcon("F:\\Images\\saback.png"));
		label_1.setBounds(0, 6, 56, 43);
		bPanel.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("F:\\Images\\Sacross.png"));
		label_2.addMouseListener(new MouseAdapter() {
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
				label_2.setCursor(cursor);
			}
		});
		label_2.setBounds(1206, 0, 30, 36);
		bPanel.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_3.setCursor(cursor);
			}
		});
		label_3.setIcon(new ImageIcon("F:\\Images\\msa.png"));
		label_3.setBounds(1172, 0, 35, 36);
		bPanel.add(label_3);
		saleTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		saleTable.getColumnModel().getColumn(0).setMaxWidth(50);
		saleTable.getColumnModel().getColumn(1).setMaxWidth(75);
		saleTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		saleTable.getColumnModel().getColumn(2).setMaxWidth(150);
		saleTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		saleTable.getColumnModel().getColumn(3).setMaxWidth(100);
		saleTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		saleTable.getColumnModel().getColumn(4).setMaxWidth(100);
		saleTable.getColumnModel().getColumn(5).setPreferredWidth(110);
		saleTable.getColumnModel().getColumn(5).setMaxWidth(110);
		saleTable.getColumnModel().getColumn(6).setMaxWidth(75);
		saleTable.getColumnModel().getColumn(7).setMaxWidth(75);
		saleTable.getColumnModel().getColumn(8).setPreferredWidth(100);
		saleTable.getColumnModel().getColumn(8).setMaxWidth(100);
		saleTable.getColumnModel().getColumn(9).setMaxWidth(75);

	}
}
