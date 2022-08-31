package bistaAgroFarm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class BillingSystem {

	JFrame bFrame;
	private JPanel bPanel;
	private JTextField dateField;

	public static JTextField textRate, customer, txtAddress, txtContact, txtQuantity, txtTotal;
	public static JComboBox<String> product;
	private JTable bTable;
	private JTextField textField;
	private final JSeparator separator = new JSeparator();

	public class Textf {

		int count = 0;
		String y = dateField.getText();
		String m = customer.getText();
		String n = txtAddress.getText();
		String o = txtContact.getText();
		String g = txtQuantity.getText();
		String h = textRate.getText();
		String i = txtTotal.getText();
		String w = (String) product.getSelectedItem();
		int p = Integer.parseInt(g);
		int x = Integer.parseInt(h);
		int z = p * x;

	}

	public void curDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		dateField.setText(dtf.format(now));
	}

	public void displayItems() {
		try {
			String sql = "select * from product";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				product.addItem(rs.getString("product_name"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void getPRate() {
		String g = (String) product.getSelectedItem();
		String s = "select * from product where product_name='" + g + "'";
		String rate = "";
		try {
			ResultSet os = DbConnect.s.executeQuery(s);
			if (os.next()) {
				rate = os.getString("Product_cost");
				textRate.setText(rate);

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);

		}
	}

	public void clear2() {
		customer.setText("");
		txtAddress.setText("");
		txtContact.setText("");
		product.setSelectedItem(null);
		textRate.setText("");
		txtTotal.setText("");
		txtQuantity.setText("");
	}

	public void showBillTransaction() {

		DefaultTableModel sd = (DefaultTableModel) bTable.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {
			String sql = "select* from bill";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				String sn = rs.getString(1);
				String date = rs.getString(2);
				String cname = rs.getString(3);

				String pname = rs.getString(4);
				String rate = rs.getString(5);
				String quantity = rs.getString(6);
				String total = rs.getString(7);

				String[] data = { sn, date, cname, pname, rate, quantity, total };

				sd.addRow(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void search(String str) {
		DefaultTableModel model = (DefaultTableModel) bTable.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		bTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}

	public BillingSystem() {
		initialize();
		displayItems();
		clear2();
		showBillTransaction();

	}

	private void initialize() {
		bFrame = new JFrame();
		bFrame.setUndecorated(true);
		bFrame.getContentPane();
		bFrame.setFont(new Font("Cooper Black", Font.BOLD, 13));
		bFrame.setTitle("Bista Agro Farm");
		bFrame.setBounds(500, 100, 857, 798);
		bFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bFrame.getContentPane().setLayout(null);

		bPanel = new JPanel();
		bPanel.setForeground(new Color(255, 165, 0));
		bPanel.setBackground(new Color(220, 220, 220));
		bPanel.setBounds(0, 0, 855, 797);
		bFrame.getContentPane().add(bPanel);
		bPanel.setLayout(null);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAddress.setBounds(94, 259, 74, 16);
		bPanel.add(lblAddress);

		JLabel lblCuName = new JLabel("Customer Name:");
		lblCuName.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCuName.setBounds(41, 219, 120, 16);
		lblCuName.setForeground(new Color(0, 0, 0));
		bPanel.add(lblCuName);

		JLabel lblNewLabel = new JLabel("Bista Agro Farm");
		lblNewLabel.setBounds(301, 30, 220, 31);
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 26));
		bPanel.add(lblNewLabel);

		JLabel lblConNUM = new JLabel("Contact No:");
		lblConNUM.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblConNUM.setBounds(75, 299, 90, 16);
		bPanel.add(lblConNUM);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setBounds(116, 179, 47, 16);
		bPanel.add(lblDate);

		JLabel lblNewLabel_6 = new JLabel("Items:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_6.setBounds(110, 339, 47, 16);
		bPanel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Quantity:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_7.setBounds(90, 419, 74, 16);
		bPanel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Rate:");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_8.setBounds(114, 379, 54, 16);
		bPanel.add(lblNewLabel_8);

		dateField = new JTextField();
		dateField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		dateField.setBounds(172, 175, 119, 24);
		bPanel.add(dateField);
		dateField.setColumns(10);
		curDateTime();
		dateField.setEditable(false);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtAddress.setBounds(172, 255, 119, 24);
		bPanel.add(txtAddress);
		txtAddress.setColumns(10);

		txtContact = new JTextField();
		txtContact.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtContact.setBounds(172, 295, 119, 24);
		txtContact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String PATTERN = "^[9][0-9]{0,9}$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(txtContact.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					txtContact.setText(null);
				} else {
					txtContact.getText();
				}

			}

		});
		bPanel.add(txtContact);
		txtContact.setColumns(10);

		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtQuantity.setBounds(172, 415, 119, 24);
		txtQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[1-9][0-9]{0,6}$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(txtQuantity.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					txtQuantity.setText(null);
					txtTotal.setText("");

				} else {
					txtQuantity.getText();
					Textf t = new Textf();

					txtTotal.setText("" + t.z);

				}

			}

		});
		bPanel.add(txtQuantity);
		txtQuantity.setColumns(10);

		textRate = new JTextField();
		textRate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textRate.setBounds(172, 375, 119, 24);

		textRate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[1-9][0-9]{0,6}$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(textRate.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					textRate.setText(null);
				} else {
					textRate.getText();
				}
			}
		});
		bPanel.add(textRate);
		textRate.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(380, 175, 350, 363);
		bPanel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		product = new JComboBox<String>();
		product.setBounds(172, 335, 119, 24);
		product.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				getPRate();
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		product.setEditable(false);
		product.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		bPanel.add(product);

		JButton btnNewButton = new JButton("Print");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				btnNewButton.setCursor(cursor);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(642, 537, 89, 23);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if (textArea.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "please generate invoice first");
					} else {
						boolean complete = textArea.print();
						if (complete) {
							JOptionPane.showMessageDialog(null, "Printing Completed");

						}

						customer.setEditable(true);
						txtAddress.setEditable(true);
						txtContact.setEditable(true);
						textRate.setEditable(true);
						txtQuantity.setEditable(true);
					}
					textArea.setText("");
				} catch (PrinterException e) {

					e.printStackTrace();
				}

			}
		});
		bPanel.add(btnNewButton);

		customer = new JTextField();
		customer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String PATTERN = "^[a-zA-Z\\s]*$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(customer.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					customer.setText(null);
				}
			}
		});
		customer.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		customer.setBounds(172, 215, 119, 24);
		bPanel.add(customer);
		customer.setColumns(10);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtTotal.setBounds(172, 455, 119, 24);
		txtTotal.setEditable(false);

		bPanel.add(txtTotal);
		txtTotal.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTotal.setBounds(112, 459, 54, 16);
		bPanel.add(lblTotal);

		JButton btnBill = new JButton("Receipt");
		btnBill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				btnBill.setCursor(cursor);
			}
		});
		btnBill.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnBill.setBounds(202, 513, 97, 25);
		btnBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String y = dateField.getText();
				String m = customer.getText();
				String n = txtAddress.getText();
				String o = txtContact.getText();
				String g1 = txtQuantity.getText();
				String h = textRate.getText();
				String i = txtTotal.getText();
				String w = (String) product.getSelectedItem();
				int count = 0;

				if (y.equals("") || m.equals("") || n.equals("") || o.equals("") || w == null || h.equals("")
						|| g1.equals("") || i.equals("")) {
					JOptionPane.showMessageDialog(null, "Please insert all details");

				} else if (o.length() < 10) {
					JOptionPane.showMessageDialog(null, "Phone number must be 10 digit");
				}

				else {
					int n0 = Integer.parseInt(g1);
					int n1 = 0;
					String sql = "Select * from product where product_name='" + w + "'";

					String sql3 = "Select cName from customer where cName='" + m + "'";
					try {
						ResultSet rs = DbConnect.s.executeQuery(sql);
						if (rs.next()) {
							n1 = rs.getInt("product_quantity");

						}

						ResultSet s = DbConnect.s.executeQuery(sql3);

						if (n0 <= n1 || s.next()) {
							String sql1 = "insert into bill(date,cName,pName,rate,quantity,total) values('" + y + "','"
									+ m + "','" + w + "','" + h + "','" + g1 + "','" + i + "')";
							String sql2 = "Select bill_no from bill order by bill_no desc";

							DbConnect.s.executeUpdate(sql1);
							ResultSet r = DbConnect.s.executeQuery(sql2);
							if (r.next()) {
								count = r.getInt(1);
							}
							int p = Integer.parseInt(g1);
							int x = Integer.parseInt(h);

							int z = p * x;
							textArea.append("\t ***Bista Agro Farm*** \t\n\n" + "\t      ***Receipt*** \t\t\n"
									+ "\t Bill No:" + count + "\n" + "\n Date:\t\t" + y + "\n" + " Customer Name:\t" + m
									+ "\n" + " Address:\t\t" + n + "\n" + " Contact:\t\t" + o + "\n" + " Item:\t\t" + w
									+ "\n" + " Quantity:\t\t" + p + "\n" + " Rate:\t\t" + x + "\n" + "\n Total:\t\t" + z
									+ "\n");

							clear2();

							ResultSet s2 = DbConnect.s.executeQuery(sql3);
							if (!s2.next()) {
								String sql4 = "UPDATE product SET product_quantity=" + (n1 - n0)
										+ " WHERE product_name='" + w + "'";
								String query = "Insert into stable(Date,cName,cAddress,cPhone,pName,pRate,pQuantity,Total) Values('"
										+ y + "','" + m + "','" + n + "','" + o + "','" + w + "','" + h + "','" + g1
										+ "','" + i + "')";
								String sql6 = "insert into statement(date,pName,pOut) values('" + y + "','" + w + "','"
										+ g1 + "')";
								DbConnect.s.executeUpdate(sql6);

								DbConnect.s.executeUpdate(sql4);
								DbConnect.s.executeUpdate(query);
							}
							showBillTransaction();

						}

						else {
							JOptionPane.showMessageDialog(null, "Stock Not Available");
						}

					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);

					}

				}
			}

		});
		bPanel.add(btnBill);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(87, 616, 672, 168);
		bPanel.add(scrollPane_1);

		bTable = new JTable();
		scrollPane_1.setViewportView(bTable);
		bTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Bill No", "Date", "Customer Name", "Product Name", "Rate", "Quantity", "Total" }));
		bTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		bTable.getColumnModel().getColumn(0).setMaxWidth(50);
		bTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		bTable.getColumnModel().getColumn(1).setMaxWidth(100);
		bTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		bTable.getColumnModel().getColumn(2).setMaxWidth(150);
		bTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		bTable.getColumnModel().getColumn(3).setMaxWidth(120);
		bTable.getColumnModel().getColumn(4).setMaxWidth(75);
		bTable.getColumnModel().getColumn(5).setMaxWidth(75);
		bTable.getColumnModel().getColumn(6).setPreferredWidth(100);
		bTable.getColumnModel().getColumn(6).setMaxWidth(100);

		JLabel lblHistory = new JLabel("History");
		lblHistory.setBounds(390, 577, 72, 31);
		lblHistory.setFont(new Font("Tahoma", Font.BOLD, 18));
		bPanel.add(lblHistory);

		textField = new JTextField();
		textField.setBounds(171, 581, 116, 22);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String skey = textField.getText();
				search(skey);
			}
		});
		bPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Search ");
		lblNewLabel_2.setBounds(96, 587, 67, 16);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bPanel.add(lblNewLabel_2);
		separator.setForeground(new Color(255, 140, 0));
		separator.setBounds(0, 121, 861, 7);
		bPanel.add(separator);

		JLabel lblBilling = new JLabel("Billing");
		lblBilling.setForeground(new Color(0, 0, 205));
		lblBilling.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblBilling.setBounds(354, 76, 120, 31);
		bPanel.add(lblBilling);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?",
						"CONFIRMATION", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);

				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label.setCursor(cursor);
			}
		});
		label.setIcon(new ImageIcon("F:\\Images\\Sacross.png"));
		label.setBounds(825, 0, 30, 31);
		bPanel.add(label);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\Images\\logoB.jpg"));
		lblNewLabel_1.setBounds(184, 13, 106, 102);
		bPanel.add(lblNewLabel_1);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Home h = new Home();
				h.hFrame.setVisible(true);
				bFrame.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_1.setCursor(cursor);
			}
		});
		label_1.setIcon(new ImageIcon("F:\\Images\\Bback.jpg"));
		label_1.setBounds(0, 0, 54, 46);
		bPanel.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bFrame.setState(Frame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_2.setCursor(cursor);
			}
		});
		label_2.setIcon(new ImageIcon("F:\\Images\\mb.png"));
		label_2.setBounds(789, 0, 35, 31);
		bPanel.add(label_2);

	}
}
