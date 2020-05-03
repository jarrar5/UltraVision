package Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultEditorKit.CutAction;

import DataAccess.CustomerDao;
import Helpers.InputValidation;
import Models.Customer;
import Models.Staff;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

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

	// RegisterCustomer
	private JPanel RegisterCustomer, CustDetailsPanelRegCust, LogoPanelRegCust;
	private JLabel lblRegisterCustomer, lblNameRegCust, lblEmailRegCust, lblPhoneRegCust, lblDebitcreditCardRegCust,
			lblAccessLevelRegCust, lblSubscriptionTypeRegCust, lblCustomerDetailsRegCust, lblLogoNameRegCust,
			lblLogoCityNameRegCust;
	private JButton btnGoToHomeRegCust, btnRegisterRegCust;
	private JTextField nameFieldRegCust, emailFieldRegCust, phoneFieldRegCust, cardNumberFieldRegCust;
	private JComboBox accesslvlComboRegCust, subscriptionComboRegCust;

	// Initializations
	String[] accesslvl = { "--Select Access Level Type--", "Music Lovers", "Premium", "TV Lover", "Video Lovers" };
	String[] subscription = { "--Select Subscription Type--", "Basic", "Delux", "Premium", "Standard" };

	// Other
	private Staff ActiveStaff;
	private Customer customer;

	private CustomerDao customerDao = new CustomerDao();
	
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

	public void RegisterCustomerGUI() {
		RegisterCustomer = new JPanel();
		layeredPane.add(RegisterCustomer, "name_2221718511575500");
		RegisterCustomer.setLayout(null);

		lblRegisterCustomer = new JLabel("Register Customer");
		lblRegisterCustomer.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblRegisterCustomer.setBounds(10, 11, 220, 31);
		RegisterCustomer.add(lblRegisterCustomer);

		CustDetailsPanelRegCust = new JPanel();
		CustDetailsPanelRegCust.setBounds(30, 76, 400, 250);
		RegisterCustomer.add(CustDetailsPanelRegCust);
		CustDetailsPanelRegCust.setLayout(null);

		lblCustomerDetailsRegCust = new JLabel("Customer Details");
		lblCustomerDetailsRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerDetailsRegCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerDetailsRegCust.setBounds(130, 11, 140, 20);
		CustDetailsPanelRegCust.add(lblCustomerDetailsRegCust);

		lblNameRegCust = new JLabel("Name");
		lblNameRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNameRegCust.setBounds(35, 67, 120, 20);
		CustDetailsPanelRegCust.add(lblNameRegCust);

		lblEmailRegCust = new JLabel("Email");
		lblEmailRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmailRegCust.setBounds(35, 98, 120, 20);
		CustDetailsPanelRegCust.add(lblEmailRegCust);

		lblPhoneRegCust = new JLabel("Phone");
		lblPhoneRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPhoneRegCust.setBounds(35, 129, 120, 20);
		CustDetailsPanelRegCust.add(lblPhoneRegCust);

		lblDebitcreditCardRegCust = new JLabel("Debit/Credit Card #");
		lblDebitcreditCardRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDebitcreditCardRegCust.setBounds(35, 160, 120, 20);
		CustDetailsPanelRegCust.add(lblDebitcreditCardRegCust);

		lblAccessLevelRegCust = new JLabel("Access Level");
		lblAccessLevelRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAccessLevelRegCust.setBounds(35, 191, 120, 20);
		CustDetailsPanelRegCust.add(lblAccessLevelRegCust);

		lblSubscriptionTypeRegCust = new JLabel("Subscription Type");
		lblSubscriptionTypeRegCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSubscriptionTypeRegCust.setBounds(35, 219, 120, 20);
		CustDetailsPanelRegCust.add(lblSubscriptionTypeRegCust);

		nameFieldRegCust = new JTextField();
		nameFieldRegCust.setBounds(165, 68, 200, 20);
		CustDetailsPanelRegCust.add(nameFieldRegCust);
		nameFieldRegCust.setColumns(10);

		emailFieldRegCust = new JTextField();
		emailFieldRegCust.setBounds(165, 99, 200, 20);
		CustDetailsPanelRegCust.add(emailFieldRegCust);
		emailFieldRegCust.setColumns(10);

		phoneFieldRegCust = new JTextField();
		phoneFieldRegCust.setBounds(165, 129, 200, 20);
		CustDetailsPanelRegCust.add(phoneFieldRegCust);
		phoneFieldRegCust.setText("");
		phoneFieldRegCust.setColumns(10);

		cardNumberFieldRegCust = new JTextField();
		cardNumberFieldRegCust.setBounds(165, 161, 200, 20);
		CustDetailsPanelRegCust.add(cardNumberFieldRegCust);
		cardNumberFieldRegCust.setText("");
		cardNumberFieldRegCust.setColumns(10);

		accesslvlComboRegCust = new JComboBox(accesslvl);
		accesslvlComboRegCust.setBounds(165, 192, 200, 20);
		CustDetailsPanelRegCust.add(accesslvlComboRegCust);

		subscriptionComboRegCust = new JComboBox(subscription);
		subscriptionComboRegCust.setBounds(165, 220, 200, 20);
		CustDetailsPanelRegCust.add(subscriptionComboRegCust);

		btnRegisterRegCust = new JButton("Register!");
		btnRegisterRegCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (nameFieldRegCust.getText().isEmpty() || emailFieldRegCust.getText().isEmpty()
						|| phoneFieldRegCust.getText().isEmpty() || cardNumberFieldRegCust.getText().isEmpty()
						|| accesslvlComboRegCust.getSelectedIndex() == 0
						|| subscriptionComboRegCust.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else if (!inputValidation.validateAlphabets(nameFieldRegCust.getText())
						|| !inputValidation.validateEmail(emailFieldRegCust.getText())
						|| !inputValidation.validateNumbers(phoneFieldRegCust.getText())
						|| !inputValidation.validateNumbers(cardNumberFieldRegCust.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Details are not Valid!!!");
				} else {
					if (phoneFieldRegCust.getText().length() > 13) {
						JOptionPane.showMessageDialog(rootPane, "Phone number Exceeds Limit of 13 Digits", "Error", 1);
					} else if (cardNumberFieldRegCust.getText().length() > 16) {
						JOptionPane.showMessageDialog(rootPane, "Card Number Exceeds Limit of 16 Digits", "Error", 1);
					} else {
						customer.setNME(nameFieldRegCust.getText());
						customer.setEMAIL(emailFieldRegCust.getText());
						customer.setPHNE(Long.parseLong(phoneFieldRegCust.getText()));
						customer.setACC_CRD(Long.parseLong(cardNumberFieldRegCust.getText()));
						customer.setLYLTY_PNTS(0);
						customer.setACCS_LVL(accesslvlComboRegCust.getSelectedItem().toString());
						customer.setSBSC(subscriptionComboRegCust.getSelectedItem().toString());

						customerDao.registerCustomer(customer);

						JOptionPane.showMessageDialog(rootPane, "Success");
						customer = new Customer();
						nameFieldRegCust.setText(null);
						emailFieldRegCust.setText(null);
						phoneFieldRegCust.setText(null);
						cardNumberFieldRegCust.setText(null);
						accesslvlComboRegCust.setSelectedIndex(0);
						subscriptionComboRegCust.setSelectedIndex(0);
					}
				}
			}
		});
		btnRegisterRegCust.setBounds(486, 303, 144, 23);
		RegisterCustomer.add(btnRegisterRegCust);

		btnGoToHomeRegCust = new JButton("Go to Home");
		btnGoToHomeRegCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameFieldRegCust.setText(null);
				emailFieldRegCust.setText(null);
				phoneFieldRegCust.setText(null);
				cardNumberFieldRegCust.setText(null);
				accesslvlComboRegCust.setSelectedIndex(0);
				subscriptionComboRegCust.setSelectedIndex(0);
				searchFieldHome.setText(null);
				switchPanels(Home);
			}
		});
		btnGoToHomeRegCust.setBounds(486, 269, 144, 23);
		RegisterCustomer.add(btnGoToHomeRegCust);

		LogoPanelRegCust = new JPanel();
		LogoPanelRegCust.setLayout(null);
		LogoPanelRegCust.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelRegCust.setBounds(432, 11, 232, 95);
		RegisterCustomer.add(LogoPanelRegCust);

		lblLogoNameRegCust = new JLabel("Ultra-Vision");
		lblLogoNameRegCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameRegCust.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameRegCust.setBounds(6, 16, 220, 49);
		LogoPanelRegCust.add(lblLogoNameRegCust);

		lblLogoCityNameRegCust = new JLabel("Dublin.");
		lblLogoCityNameRegCust.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameRegCust.setBounds(177, 62, 49, 26);
		LogoPanelRegCust.add(lblLogoCityNameRegCust);

	}

	public App(Staff staff) {

		setResizable(false);

		InstanciateApp(staff);

		HomeGUI();

		setVisible(true);
	}
}
