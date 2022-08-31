package bistaAgroFarm;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Stock {

	JFrame sFrame;
	private JTextField pStock;
	private JComboBox<String> pList;
	private JTextField hDate;
	private JTable sTable, stockTable;
	private final JSeparator separator = new JSeparator();
	private JTextField textField;

	public void display() {
		try {
			String sql = "select * from product";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				pList.addItem(rs.getString("product_name"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void search(String str) {
		DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		stockTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}

	public void show() {

		DefaultTableModel sd = (DefaultTableModel) sTable.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {
			String sql = "select* from product";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString(2);
				String quantity = rs.getString(4);
				String[] data = { name, quantity };

				sd.addRow(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void showStock() {

		DefaultTableModel sd = (DefaultTableModel) stockTable.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {
			String sql = "select* from stock order by itid desc";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				String date = rs.getString(2);
				String name = rs.getString(3);
				String cost = rs.getString(4);
				String[] data = { date, name, cost, };
				sd.addRow(data);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void curDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		hDate.setText(dtf.format(now));
	}

	public Stock() {
		initialize();
		display();
		show();
		showStock();
		pList.setSelectedItem(null);
	}

	@SuppressWarnings("serial")
	private void initialize() {
		sFrame = new JFrame();
		sFrame.setUndecorated(true);
		sFrame.setBounds(500, 200, 735, 562);
		sFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(189, 183, 107));
		panel.setBounds(0, 0, 735, 559);
		sFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setBounds(65, 172, 38, 16);
		panel.add(lblDate);

		JLabel lblNewLabel = new JLabel("Product Name:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(4, 201, 109, 37);
		panel.add(lblNewLabel);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQuantity.setBounds(42, 241, 67, 27);
		panel.add(lblQuantity);

		pStock = new JTextField();
		pStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[1-9][0-9]{0,6}$";
				Pattern patt = Pattern.compile(PATTERN);
				Matcher match = patt.matcher(pStock.getText());
				if (!match.matches()) {
					JOptionPane.showMessageDialog(null, "invalid");
					pStock.setText(null);
				} else {
					pStock.getText();
				}
			}
		});
		pStock.setBounds(115, 243, 133, 22);
		panel.add(pStock);
		pStock.setColumns(10);

		JButton hAdd = new JButton("Add");
		hAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				hAdd.setCursor(cursor);
			}
		});
		hAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String val = (String) pList.getSelectedItem();
				String v = pStock.getText();
				String g = hDate.getText();

				int n = 0;
				try {

					String sql = "Select * from product where product_name='" + val + "'";
					ResultSet rs = DbConnect.s.executeQuery(sql);
					if (rs.next()) {
						n = rs.getInt("product_quantity");

					}
					if (v.equals("")) {
						JOptionPane.showMessageDialog(null, "Please insert stock");
					}

					else {
						int n1 = Integer.parseInt(v);
						String sql1 = "UPDATE product SET product_quantity=" + (n + n1) + " WHERE product_name='" + val
								+ "'";
						DbConnect.s.executeUpdate(sql1);
						String sql2 = "insert into stock(date,pName,pStock) value('" + g + "','" + val + "','" + v
								+ "')";
						DbConnect.s.executeUpdate(sql2);
						String sql6 = "insert into statement(date,pName,pIn) values('" + g + "','" + val + "','" + v
								+ "')";
						DbConnect.s.executeUpdate(sql6);
						JOptionPane.showMessageDialog(null, "Added successfully");
						pStock.setText("");
						pList.setSelectedItem(null);
						show();
						showStock();
					}

				} catch (Exception e0) {
					JOptionPane.showMessageDialog(null, e0);
				}
			}
		});
		hAdd.setBounds(137, 278, 73, 25);
		panel.add(hAdd);

		pList = new JComboBox<String>();
		pList.setBounds(115, 208, 133, 22);
		panel.add(pList);

		hDate = new JTextField();
		hDate.setBounds(115, 169, 133, 22);
		panel.add(hDate);
		hDate.setColumns(10);

		curDateTime();
		hDate.setEditable(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(407, 146, 251, 160);
		panel.add(scrollPane);

		sTable = new JTable();
		scrollPane.setViewportView(sTable);
		sTable.getTableHeader().setReorderingAllowed(false);
		sTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Product Name", "Stock" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Available Stock");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(484, 117, 140, 16);
		panel.add(lblNewLabel_1);
		separator.setBounds(0, 108, 735, 2);
		panel.add(separator);

		JLabel lblLastHistory = new JLabel("History");
		lblLastHistory.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblLastHistory.setBounds(301, 339, 67, 16);
		panel.add(lblLastHistory);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(129, 358, 452, 198);
		panel.add(scrollPane_1);

		stockTable = new JTable();
		scrollPane_1.setViewportView(stockTable);
		stockTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Date", "Product name", "stock" }));

		JLabel lblBistaAgroFarm = new JLabel("Bista Agro Farm");
		lblBistaAgroFarm.setForeground(new Color(128, 0, 0));
		lblBistaAgroFarm.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBistaAgroFarm.setBounds(235, 17, 252, 36);
		panel.add(lblBistaAgroFarm);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setForeground(new Color(0, 0, 255));
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblStock.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblStock.setBorder(null);
		lblStock.setBackground(Color.DARK_GRAY);
		lblStock.setBounds(235, 66, 234, 44);
		panel.add(lblStock);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("F:\\Images\\logoS.jpg"));
		label.setBackground(new Color(189, 183, 107));
		label.setBounds(132, 0, 100, 110);
		panel.add(label);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
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
				label_1.setCursor(cursor);
			}

		});
		label_1.setIcon(new ImageIcon("F:\\Images\\Sacross.png"));
		label_1.setBounds(704, 0, 31, 36);
		panel.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Home h = new Home();
				h.hFrame.setVisible(true);
				sFrame.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_2.setCursor(cursor);
			}
		});
		label_2.setIcon(new ImageIcon("F:\\Images\\stback.jpg"));
		label_2.setBounds(0, 3, 50, 50);
		panel.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sFrame.setState(Frame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_3.setCursor(cursor);
			}
		});
		label_3.setIcon(new ImageIcon("F:\\Images\\lst.png"));
		label_3.setBounds(667, 0, 38, 37);
		panel.add(label_3);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSearch.setBounds(30, 365, 56, 16);
		panel.add(lblSearch);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String skey = textField.getText();
				search(skey);
			}
		});
		textField.setBounds(4, 394, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		stockTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		stockTable.getColumnModel().getColumn(0).setMaxWidth(150);
		stockTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		stockTable.getColumnModel().getColumn(1).setMaxWidth(200);
		stockTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		stockTable.getColumnModel().getColumn(2).setMaxWidth(100);
		sTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		sTable.getColumnModel().getColumn(0).setMaxWidth(150);
		sTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		sTable.getColumnModel().getColumn(1).setMaxWidth(100);
	}
}
