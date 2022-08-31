package bistaAgroFarm;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;

public class Loginpage {

	JFrame lFrame;
	private JTextField tUser;
	private JPasswordField tPass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginpage window = new Loginpage();
					window.lFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Loginpage() {
		initialize();

	}

	@SuppressWarnings("deprecation")
	public void enter() {
		String user = tUser.getText();
		String pass = tPass.getText();
		if (user.length() == 0 || pass.length() == 0) {
			JOptionPane.showMessageDialog(null, "Please insert all field");

		} else {
			try {

				String q1 = "SELECT * from admin WHERE uname=? and password=?";
				PreparedStatement pst = DbConnect.con.prepareStatement(q1);
				pst.setString(1, tUser.getText());
				pst.setString(2, tPass.getText());
				ResultSet rs = pst.executeQuery();
				int count = 0;
				while (rs.next()) {
					count = count + 1;
				}
				if (count == 1) {
					JOptionPane.showMessageDialog(null, "Valid UserName and Password");
					Home h = new Home();
					h.hFrame.setVisible(true);
					lFrame.dispose();

				}

				else {
					JOptionPane.showMessageDialog(null, " Invalid USername and password ");
					tUser.setText("");
					tPass.setText("");
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2);
			}
		}
	}

	private void initialize() {

		lFrame = new JFrame();
		lFrame.setUndecorated(true);
		lFrame.setTitle("Bista Agro farm");
		lFrame.setBackground(new Color(0, 51, 153));

		lFrame.setBounds(600, 350, 635, 373);
		lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lFrame.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 204));
		panel_1.setBounds(0, 0, 634, 371);
		lFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bista Agro Farm");
		lblNewLabel.setBounds(121, 13, 374, 71);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);

		tUser = new JTextField();
		tUser.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tUser.setBounds(207, 142, 207, 23);
		panel_1.add(tUser);
		tUser.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(Color.GREEN);
		lblNewLabel_2.setFont(new Font("Arial Nova", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(262, 178, 87, 23);
		panel_1.add(lblNewLabel_2);

		tPass = new JPasswordField();
		tPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					enter();
				}
			}
		});

		tPass.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tPass.setBounds(209, 214, 205, 23);
		panel_1.add(tPass);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				btnNewButton.setCursor(cursor);
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enter();
			}
		});
		btnNewButton.setBounds(260, 273, 89, 23);
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setLabelFor(lFrame);
		lblNewLabel_1.setBounds(262, 97, 89, 32);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIgnoreRepaint(true);
		lblNewLabel_1.setMaximumSize(new Dimension(19, 14));
		lblNewLabel_1.setIconTextGap(1);
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Arial Nova", Font.PLAIN, 15));

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(32, 126, 161, 155);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBackground(new Color(0, 0, 255));
		lblNewLabel_3.setIgnoreRepaint(true);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setIcon(new ImageIcon("F:\\Images\\LogoM.jpg"));

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
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
				label.setCursor(cursor);
			}
		});
		label.setIcon(new ImageIcon("F:\\Images\\Sacross.png"));
		label.setBounds(604, 0, 30, 32);
		panel_1.add(label);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
				label_1.setCursor(cursor);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				lFrame.setState(Frame.ICONIFIED);
			}
		});
		label_1.setIcon(new ImageIcon("F:\\Images\\ml.png"));
		label_1.setBounds(570, 0, 34, 32);
		panel_1.add(label_1);

	}
}
