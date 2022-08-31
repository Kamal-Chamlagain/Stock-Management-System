package bistaAgroFarm;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Home {

	JFrame hFrame;

	public void design() {
		ProductHome h = new ProductHome();
		h.pFrame.setVisible(true);
		hFrame.dispose();
	}

	public void database() {
		String sqlP = "Create table if not exists product" + "( product_id int primary key auto_increment,"
				+ "product_name varchar(20) not null," + "product_cost decimal(6,0) not null,"
				+ "product_quantity int not null default 0)";
		String sqlC = "Create table if not exists customer("
				+ "cid int primary key auto_increment,cName varchar(30) not null,"
				+ "cAddress varchar(25) not null,cPhone varchar(10) not null)";
		String sqlSI = "create table if not exists stock (" + "itid int primary key auto_increment,"
				+ "date date not null," + "pName varchar(23) not null," + "pStock int not null)";
		String sqls = "create table if not exists stable(" + "outid int primary key auto_increment,"
				+ "Date date not null," + "cName varchar(20) not null," + "cAddress varchar(30) not null,"
				+ "cPhone Varchar(10) not null," + "tType varchar(10) not null default'cash',"
				+ "pName varchar(20) not null," + "pRate double not null," + "pQuantity varchar(20) not null,"
				+ "Total double not null	)";
		String sqlbill="create table if not exists bill(" + 
				"bill_no int primary key auto_increment," + 
				"date date," + 
				"cname varchar(24)," + 
				"pname varchar(23)," + 
				"rate double," + 
				"quantity int," + 
				"total double)";
		String sqlst="Create table if not exists Statement(" + 
				"sid int primary key auto_increment," + 
				"date date not null," + 
				"pname varchar(25) not null," + 
				"pIn int ," + 
				"pOut int)";

		try {
			
			DbConnect.s.executeUpdate(sqlP);
			DbConnect.s.executeUpdate(sqlC);
			DbConnect.s.executeUpdate(sqlSI);
			DbConnect.s.executeUpdate(sqls);
			DbConnect.s.executeUpdate(sqlbill);
			DbConnect.s.executeUpdate(sqlst);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	
	public Home() {
		initialize();
		database();
	}

	private void initialize() {
		hFrame = new JFrame();
		hFrame.setUndecorated(true);
		hFrame.setBounds(500, 250, 793, 588);
		hFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 794, 589);
		hFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton pButton = new JButton("Products");
		pButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				 pButton.setCursor(cursor);
			}
		});
		pButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		pButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				design();
			}
		});
		pButton.setBounds(78, 299, 119, 32);
		panel.add(pButton);

		JButton cButton = new JButton("Customer");
		cButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				 cButton.setCursor(cursor);
			}
		});
		// cButton.setBackground(Color.WHITE);
		cButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		cButton.setToolTipText("");
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerHome c = new CustomerHome();
				c.cFrame.setVisible(true);
				hFrame.dispose();
			}
		});
		cButton.setBounds(335, 302, 110, 32);
		panel.add(cButton);

		JButton billButton = new JButton("Bill");
		billButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				 billButton.setCursor(cursor);
			}
		});
		billButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		billButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BillingSystem s = new BillingSystem();
				s.bFrame.setVisible(true);
				hFrame.dispose();
			}
		});
		billButton.setBounds(335, 546, 110, 32);
		panel.add(billButton);

		JButton btnStock = new JButton("Stock");
		btnStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				 btnStock.setCursor(cursor);
			}
		});
		btnStock.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stock s = new Stock();
				s.sFrame.setVisible(true);
				hFrame.dispose();
			}
		});
		btnStock.setBounds(89, 551, 97, 25);
		panel.add(btnStock);

		JButton hTransaction = new JButton("Transactions");
		hTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				 hTransaction.setCursor(cursor);
			}
		});
		hTransaction.setFont(new Font("Times New Roman", Font.BOLD, 18));
		hTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transaction t = new Transaction();
				t.tFrame.setVisible(true);
				hFrame.dispose();
			}
		});
		hTransaction.setBounds(573, 551, 151, 25);
		panel.add(hTransaction);

		JLabel lblBistaAgroFarm = new JLabel("Bista Agro Farm");
		lblBistaAgroFarm.setForeground(new Color(128, 0, 0));
		lblBistaAgroFarm.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBistaAgroFarm.setBounds(280, 33, 252, 36);
		panel.add(lblBistaAgroFarm);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\Images\\home.jpg"));
		lblNewLabel_1.setBounds(141, 0, 110, 112);
		panel.add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 115, 784, 2);
		panel.add(separator);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("F:\\Images\\c.png"));
		label.setBounds(296, 115, 191, 202);
		panel.add(label);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				design();
			}
		});
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_1.setIcon(new ImageIcon("F:\\Images\\images.jpg"));
		label_1.setBounds(29, 118, 200, 183);
		panel.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("F:\\Images\\bill.jpg"));
		label_2.setBounds(295, 360, 200, 183);
		panel.add(label_2);

		JButton btnDai = new JButton("Sales");
		btnDai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				 btnDai.setCursor(cursor);
			}
		});
		btnDai.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sales s = new Sales();
				s.frame.setVisible(true);
				hFrame.dispose();
			}
		});
		btnDai.setBounds(584, 299, 97, 33);
		panel.add(btnDai);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("F:\\Images\\s.jpg"));
		label_3.setBounds(42, 360, 200, 195);
		panel.add(label_3);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("F:\\Images\\t.jpg"));
		label_4.setBounds(540, 360, 209, 170);
		panel.add(label_4);

		JLabel label_4_1 = new JLabel("");
		label_4_1.setIcon(new ImageIcon("F:\\Images\\sales.png"));
		label_4_1.setBounds(526, 115, 226, 194);
		panel.add(label_4_1);

		JLabel label_5 = new JLabel("");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?",
						"CONFIRMATION", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				 label_5.setCursor(cursor);
			}
		});
		label_5.setIcon(new ImageIcon("F:\\Images\\Sacross.png"));
		label_5.setBounds(764, 0, 30, 32);
		panel.add(label_5);

		JLabel label_6 = new JLabel("");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Loginpage l=new Loginpage();
				l.lFrame.setVisible(true);
				hFrame.dispose();
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_6.setCursor(cursor);
			}
		});
		label_6.setIcon(new ImageIcon("F:\\Images\\hback.jpg"));
		label_6.setBounds(0, 0, 40, 47);
		panel.add(label_6);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 hFrame.setState(Frame.ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
	            lblNewLabel.setCursor(cursor);
			}
		});
		lblNewLabel.setIcon(new ImageIcon("F:\\Images\\mh.png"));
		lblNewLabel.setBounds(730, 0, 37, 36);
		panel.add(lblNewLabel);
	}
}
