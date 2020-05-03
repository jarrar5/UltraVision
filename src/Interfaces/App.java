package Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultEditorKit.CutAction;
import Helpers.InputValidation;
import Models.Staff;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class App extends JFrame {

	private JPanel contentPane;

	private JLayeredPane layeredPane;

	// Home
	private JPanel Home, UserDetailsPanelHome, LogoPanelHome;
	private JLabel lblHome, lblActiveUserHome, lblActiveStaffNameHome, lblLogoNameHome, lblLogoCityNameHome;
	private JButton btnSearchCustomerHome, btnRegisterCustomerHome, btnSearchTitleHome, btnAddTitleHome, btnLogoutHome;
	private JTextField searchFieldHome;

	// Other
	private Staff ActiveStaff;
	private InputValidation inputValidation;
		
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void InstanciateApp(Staff staff) {
		ActiveStaff = staff;
		inputValidation = new InputValidation();
		setTitle("Ultra-Vision");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 674, 349);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}

	public void HomeGUI() {
		Home = new JPanel();
		Home.setBorder(null);
		layeredPane.add(Home, "name_2178593783278100");
		Home.setLayout(null);

		lblHome = new JLabel("Home");
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblHome.setToolTipText("Home Page");
		lblHome.setBounds(10, 11, 75, 31);
		Home.add(lblHome);

		searchFieldHome = new JTextField();
		searchFieldHome.setBounds(177, 160, 300, 30);
		Home.add(searchFieldHome);
		searchFieldHome.setColumns(10);

		btnSearchCustomerHome = new JButton("Search Customer");
		
		btnSearchCustomerHome.setBounds(137, 234, 150, 30);
		Home.add(btnSearchCustomerHome);

		btnRegisterCustomerHome = new JButton("Register Customer");
		btnRegisterCustomerHome.setBounds(137, 275, 150, 30);
		Home.add(btnRegisterCustomerHome);

		UserDetailsPanelHome = new JPanel();
		UserDetailsPanelHome.setLayout(null);
		UserDetailsPanelHome.setBounds(504, 11, 160, 80);
		Home.add(UserDetailsPanelHome);

		btnLogoutHome = new JButton("Logout!");
		btnLogoutHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActiveStaff = null;
				dispose();
				new Login();
			}
		});
		btnLogoutHome.setBounds(59, 51, 100, 23);
		UserDetailsPanelHome.add(btnLogoutHome);

		lblActiveUserHome = new JLabel("Active User");
		lblActiveUserHome.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblActiveUserHome.setBounds(89, 11, 70, 14);
		UserDetailsPanelHome.add(lblActiveUserHome);

		lblActiveStaffNameHome = new JLabel("User Not Found!");
		lblActiveStaffNameHome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActiveStaffNameHome.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblActiveStaffNameHome.setBounds(10, 25, 149, 22);
		UserDetailsPanelHome.add(lblActiveStaffNameHome);

		lblActiveStaffNameHome.setText(ActiveStaff.getName());

		btnSearchTitleHome = new JButton("Search Title");
		btnSearchTitleHome.setBounds(363, 234, 150, 30);
		Home.add(btnSearchTitleHome);

		btnAddTitleHome = new JButton("Add Title");
		btnAddTitleHome.setBounds(363, 275, 150, 30);
		Home.add(btnAddTitleHome);

		LogoPanelHome = new JPanel();
		LogoPanelHome.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelHome.setBounds(211, 34, 232, 95);
		Home.add(LogoPanelHome);
		LogoPanelHome.setLayout(null);

		lblLogoNameHome = new JLabel("Ultra-Vision");
		lblLogoNameHome.setBounds(6, 16, 220, 49);
		LogoPanelHome.add(lblLogoNameHome);
		lblLogoNameHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameHome.setFont(new Font("Segoe Script", Font.BOLD, 30));

		lblLogoCityNameHome = new JLabel("Dublin.");
		lblLogoCityNameHome.setBounds(177, 62, 49, 26);
		LogoPanelHome.add(lblLogoCityNameHome);
		lblLogoCityNameHome.setFont(new Font("Segoe Print", Font.PLAIN, 14));
	}

	public App(Staff staff) {

		setResizable(false);

		InstanciateApp(staff);

		HomeGUI();

		setVisible(true);
	}
}
