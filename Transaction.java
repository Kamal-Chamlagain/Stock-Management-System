package bistaAgroFarm;

import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Transaction {

	JFrame tFrame;
	private JTable iTable;

	private JScrollPane scrollPane;
	private JTextField textIn;
	private JTextField textOut;
	private JTextField textStock;
	private JTextField textField;
	private JLabel lblSearch;
	private JLabel lblBistaAgroFarm;
	private JLabel lblStockTransactions;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;

	public void search(String str) {
		DefaultTableModel model = (DefaultTableModel) iTable.getModel();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		iTable.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(str));
	}

	public void showStatement() {

		DefaultTableModel sd = (DefaultTableModel) iTable.getModel();
		int rc = sd.getRowCount();
		// delete the previous table data
		while (rc-- != 0) {
			sd.removeRow(rc);
		}
		try {
			String sql = "select* from statement";
			ResultSet rs = DbConnect.s.executeQuery(sql);
			while (rs.next()) {
				String sn = rs.getString(1);
				String date = rs.getString(2);
				String name = rs.getString(3);
				String in = rs.getString(4);
				String out = rs.getString(5);
				String[] data = { sn, date, name, in, out };

				sd.addRow(data);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void sum() {
		int insum = 0, osum = 0;
		String h = null;
		for (int i = 0; i < iTable.getRowCount(); i++) {
			String a = (String) iTable.getValueAt(i, 3);
			String b = (String) iTable.getValueAt(i, 4);

			if (a == h) {
				a = "0";

			}
			if (b == h) {

				b = "0";
			}

			insum = insum + Integer.parseInt(a);
			osum = osum + Integer.parseInt(b);
		}
		int remain = insum - osum;
		textIn.setText("" + insum);
		textOut.setText("" + osum);
		textStock.setText("" + remain);
	}

	public Transaction() {
		initialize();
		showStatement();
		sum();

	}

	private void initialize() {
		tFrame = new JFrame();
		tFrame.setUndecorated(true);
		tFrame.setBounds(500, 250, 837, 458);
		tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(0, 0, 834, 458);
		tFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.GREEN);
		separator_1.setBounds(-12, 96, 846, 2);
		panel.add(separator_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 146, 551, 288);
		panel.add(scrollPane);

		iTable = new JTable();
		scrollPane.setViewportView(iTable);
		iTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Date", "Product name", "Incoming Stock", "Outgoing Stock" }));
		iTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		iTable.getColumnModel().getColumn(0).setMaxWidth(60);
		iTable.getColumnModel().getColumn(1).setPreferredWidth(100);
		iTable.getColumnModel().getColumn(1).setMaxWidth(100);
		iTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		iTable.getColumnModel().getColumn(2).setMaxWidth(150);
		iTable.getColumnModel().getColumn(3).setPreferredWidth(120);
		iTable.getColumnModel().getColumn(3).setMaxWidth(120);
		iTable.getColumnModel().getColumn(4).setPreferredWidth(120);
		iTable.getColumnModel().getColumn(4).setMaxWidth(120);

		JLabel lblNewLabel = new JLabel("Incoming");
		lblNewLabel.setBounds(643, 234, 56, 16);
		panel.add(lblNewLabel);

		textIn = new JTextField();
		textIn.setBounds(725, 231, 102, 22);
		panel.add(textIn);
		textIn.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Outgoing");
		lblNewLabel_1.setBounds(643, 287, 56, 16);
		panel.add(lblNewLabel_1);

		textOut = new JTextField();
		textOut.setBounds(725, 284, 102, 22);
		panel.add(textOut);
		textOut.setColumns(10);

		textStock = new JTextField();
		textStock.setBounds(725, 329, 102, 22);
		panel.add(textStock);
		textStock.setColumns(10);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(657, 332, 45, 16);
		panel.add(lblStock);

		JButton btnView = new JButton("View");
		btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				btnView.setCursor(cursor);

			}
		});
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sum();
			}
		});
		btnView.setBounds(673, 169, 97, 25);
		panel.add(btnView);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String skey = textField.getText();
				search(skey);
			}
		});
		textField.setBounds(317, 111, 116, 22);
		panel.add(textField);
		textField.setColumns(10);

		lblSearch = new JLabel("Search");
		lblSearch.setBounds(247, 117, 56, 16);
		panel.add(lblSearch);

		lblBistaAgroFarm = new JLabel("Bista Agro Farm");
		lblBistaAgroFarm.setForeground(new Color(128, 0, 0));
		lblBistaAgroFarm.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBistaAgroFarm.setBounds(272, 13, 252, 36);
		panel.add(lblBistaAgroFarm);

		lblStockTransactions = new JLabel("Stock Transactions");
		lblStockTransactions.setForeground(new Color(65, 105, 225));
		lblStockTransactions.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblStockTransactions.setBounds(307, 75, 200, 16);
		panel.add(lblStockTransactions);

		label = new JLabel("");
		label.setIcon(new ImageIcon("F:\\Images\\logot.jpg"));
		label.setBounds(174, 0, 97, 98);
		panel.add(label);

		label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Home h = new Home();
				h.hFrame.setVisible(true);
				tFrame.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_1.setCursor(cursor);
			}
		});
		label_1.setIcon(new ImageIcon("F:\\Images\\tback.jpg"));
		label_1.setBounds(0, 0, 56, 45);
		panel.add(label_1);

		label_2 = new JLabel("");
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
		label_2.setIcon(new ImageIcon("F:\\Images\\Sacross.png"));
		label_2.setBounds(804, 0, 30, 36);
		panel.add(label_2);

		label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tFrame.setState(Frame.ICONIFIED);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_3.setCursor(cursor);
			}
		});
		label_3.setIcon(new ImageIcon("F:\\Images\\mst.png"));
		label_3.setBounds(768, 0, 35, 36);
		panel.add(label_3);
	}
}
