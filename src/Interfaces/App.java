package Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultEditorKit.CutAction;

import DataAccess.CustomerDao;
import DataAccess.ProductDao;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	// Search Customer Panel
	private JPanel SearchCustomer, CustTablePanelSrchCust, LogoPanelSrchCust;
	private JLabel lblSearchResultsSrchCust, lblLogoNameSrchCust, lblLogoCityNameSrchCust, lblSelectedCustomerSrchCust,
			lblSelectedCustNameSrchCust;
	private JButton btnGoToHomeSrchCust, btnEditProfileSrchCust, btnViewProfileSrchCust;
	private JTable CustTableSrchCust;
	private JScrollPane CustScrollPaneSrchCust;

	// Edit Customer
	private JPanel EditCustomer, LogoPanelEditCust, CustomerDetailsPanelEditCust;
	private JLabel lblEditCustomer, lblNameEditCust, lblEmailEditCust, lblPhoneEditCust, lblDebitcreditCardEditCust,
			lblAccessLevelEditCust, lblSubscriptionEditCust, lblLogoNameEditCust, lblLogoCityNameEditCust,
			lblCustomerDetailsEditCust;
	private JTextField nameFieldEditCust, emailFieldEditCust, phoneFieldEditCust, cardNumberFieldEditCust;
	private JComboBox accesslvlComboEditCust, subscriptionComboEditCust;
	private JButton btnUpdateEditCust, btnGoToHomeEditCust;

	// Profile
	private JPanel Profile, LogoPanelProfile, CustDetailsPanelProfile;
	private JLabel lblProfile, lblNameProfile, lblEmailProfile, lblPhoneProfile, lblDebitcreditCardProfile,
			lblLoyaltyPointsProfile, lblAccessLevelProfile, lblSubscriptionTypeProfile, lblNameCustProfile,
			lblEmailCustProfile, lblDebitcreditCardCustProfile, lblPhoneCustProfile, lblSubscriptionTypeCustProfile,
			lblAccessLevelCustProfile, lblLoyaltyPointsCustProfile, lblIdProfile, lblidCustProfile, lblLogoNameProfile,
			lblLogoCityNameProfile, lblCustomerDetailsProfile;
	private JButton btnIssueNewRentalProfile, btnViewIssuedRentalsProfile, btnGoToHomeProfile, btnRedeemLoyaltyPoints;

	// Initializations
	String[] accesslvl = { "--Select Access Level Type--", "Music Lovers", "Premium", "TV Lover", "Video Lovers" };
	String[] subscription = { "--Select Subscription Type--", "Basic", "Delux", "Premium", "Standard" };

	// Other
	private Staff ActiveStaff;
	private Customer customer;

	int index = -1;
	private TableModel model;

	boolean redeemFlag = false;

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

	public void SearchCustomerGUI() {
		SearchCustomer = new JPanel();
		layeredPane.add(SearchCustomer, "name_2324474656071100");
		SearchCustomer.setLayout(null);

		lblSearchResultsSrchCust = new JLabel("Search Results");
		lblSearchResultsSrchCust.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblSearchResultsSrchCust.setBounds(10, 11, 220, 40);
		SearchCustomer.add(lblSearchResultsSrchCust);

		CustTablePanelSrchCust = new JPanel();
		CustTablePanelSrchCust.setBounds(10, 130, 534, 208);
		SearchCustomer.add(CustTablePanelSrchCust);
		CustTablePanelSrchCust.setLayout(null);

		CustScrollPaneSrchCust = new JScrollPane();
		CustScrollPaneSrchCust.setBounds(0, 0, 534, 208);
		CustTablePanelSrchCust.add(CustScrollPaneSrchCust);

		CustTableSrchCust = new JTable();
		CustScrollPaneSrchCust.setViewportView(CustTableSrchCust);
		CustTableSrchCust.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index = CustTableSrchCust.getSelectedRow();
				model = CustTableSrchCust.getModel();
				btnEditProfileSrchCust.setVisible(true);
				btnViewProfileSrchCust.setVisible(true);
				lblSelectedCustomerSrchCust.setVisible(true);
				lblSelectedCustNameSrchCust.setVisible(true);
				lblSelectedCustNameSrchCust.setText(model.getValueAt(index, 1).toString());

				System.out.println(model.getValueAt(index, 0).toString());

				customer.setID(Integer.parseInt(model.getValueAt(index, 0).toString()));
				customer.setNME(model.getValueAt(index, 1).toString());
				customer.setEMAIL(model.getValueAt(index, 2).toString());
				customer.setPHNE(Long.parseLong(model.getValueAt(index, 3).toString()));
				customer.setACC_CRD(Long.parseLong(model.getValueAt(index, 4).toString()));
				customer.setLYLTY_PNTS(Integer.parseInt(model.getValueAt(index, 5).toString()));
				customer.setACCS_LVL(model.getValueAt(index, 6).toString());
				customer.setSBSC(model.getValueAt(index, 7).toString());

			}
		});

		btnGoToHomeSrchCust = new JButton("Go to Home");
		btnGoToHomeSrchCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Home);
			}
		});
		btnGoToHomeSrchCust.setBounds(554, 191, 110, 23);
		SearchCustomer.add(btnGoToHomeSrchCust);

		btnEditProfileSrchCust = new JButton("Edit Profile");
		btnEditProfileSrchCust.setVisible(false);
		btnEditProfileSrchCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index != -1) {

					nameFieldEditCust.setText(customer.getNME());
					emailFieldEditCust.setText(customer.getEMAIL());
					phoneFieldEditCust.setText(String.valueOf(customer.getPHNE()));
					cardNumberFieldEditCust.setText(String.valueOf(customer.getACC_CRD()));
					accesslvlComboEditCust.setSelectedItem(customer.getACCS_LVL());
					subscriptionComboEditCust.setSelectedItem(customer.getSBSC());

					switchPanels(EditCustomer);

					btnEditProfileSrchCust.setText("Edit Customer");
					btnEditProfileSrchCust.setVisible(false);
					btnViewProfileSrchCust.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(rootPane, "Click on a row to Select");
				}
			}
		});
		btnEditProfileSrchCust.setBounds(554, 225, 110, 23);
		SearchCustomer.add(btnEditProfileSrchCust);

		btnViewProfileSrchCust = new JButton("View Profile");
		btnViewProfileSrchCust.setVisible(false);
		btnViewProfileSrchCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customer.getLYLTY_PNTS() >= 100) {
					btnRedeemLoyaltyPoints.setVisible(true);
				} else {
					btnRedeemLoyaltyPoints.setVisible(false);
				}
				lblidCustProfile.setText(String.valueOf(customer.getID()));
				lblNameCustProfile.setText(customer.getNME());
				lblEmailCustProfile.setText(customer.getEMAIL());
				lblPhoneCustProfile.setText(String.valueOf(customer.getPHNE()));
				lblDebitcreditCardCustProfile.setText(String.valueOf(customer.getACC_CRD()));
				lblLoyaltyPointsCustProfile.setText(String.valueOf(customer.getLYLTY_PNTS()));
				lblAccessLevelCustProfile.setText(customer.getACCS_LVL());
				lblSubscriptionTypeCustProfile.setText(customer.getSBSC());
				switchPanels(Profile);
			}
		});
		btnViewProfileSrchCust.setBounds(554, 259, 110, 23);
		SearchCustomer.add(btnViewProfileSrchCust);

		LogoPanelSrchCust = new JPanel();
		LogoPanelSrchCust.setLayout(null);
		LogoPanelSrchCust.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelSrchCust.setBounds(432, 11, 232, 95);
		SearchCustomer.add(LogoPanelSrchCust);

		lblLogoNameSrchCust = new JLabel("Ultra-Vision");
		lblLogoNameSrchCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameSrchCust.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameSrchCust.setBounds(6, 16, 220, 49);
		LogoPanelSrchCust.add(lblLogoNameSrchCust);

		lblLogoCityNameSrchCust = new JLabel("Dublin.");
		lblLogoCityNameSrchCust.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameSrchCust.setBounds(177, 62, 49, 26);
		LogoPanelSrchCust.add(lblLogoCityNameSrchCust);

		lblSelectedCustomerSrchCust = new JLabel("Selected Customer");
		lblSelectedCustomerSrchCust.setVisible(false);
		lblSelectedCustomerSrchCust.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectedCustomerSrchCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSelectedCustomerSrchCust.setBounds(36, 92, 115, 14);
		SearchCustomer.add(lblSelectedCustomerSrchCust);

		lblSelectedCustNameSrchCust = new JLabel("");
		lblSelectedCustNameSrchCust.setVisible(false);
		lblSelectedCustNameSrchCust.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSelectedCustNameSrchCust.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblSelectedCustNameSrchCust.setBounds(161, 79, 130, 31);
		SearchCustomer.add(lblSelectedCustNameSrchCust);
	}

	private void ProfileGUI() {
		Profile = new JPanel();
		layeredPane.add(Profile, "name_2701493917085700");
		Profile.setLayout(null);

		lblProfile = new JLabel("Profile");
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblProfile.setBounds(10, 11, 100, 40);
		Profile.add(lblProfile);

		LogoPanelProfile = new JPanel();
		LogoPanelProfile.setLayout(null);
		LogoPanelProfile.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelProfile.setBounds(432, 11, 232, 95);
		Profile.add(LogoPanelProfile);

		lblLogoNameProfile = new JLabel("Ultra-Vision");
		lblLogoNameProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameProfile.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameProfile.setBounds(6, 16, 220, 49);
		LogoPanelProfile.add(lblLogoNameProfile);

		lblLogoCityNameProfile = new JLabel("Dublin.");
		lblLogoCityNameProfile.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameProfile.setBounds(177, 62, 49, 26);
		LogoPanelProfile.add(lblLogoCityNameProfile);

		lblCustomerDetailsProfile = new JLabel("Customer Details");
		lblCustomerDetailsProfile.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblCustomerDetailsProfile.setBounds(20, 86, 154, 27);
		Profile.add(lblCustomerDetailsProfile);

		btnRedeemLoyaltyPoints = new JButton("Redeem Loyalty Points");

		btnRedeemLoyaltyPoints.setVisible(false);

		btnRedeemLoyaltyPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnRedeemLoyaltyPoints.setBounds(257, 90, 165, 23);
		Profile.add(btnRedeemLoyaltyPoints);

		CustDetailsPanelProfile = new JPanel();
		CustDetailsPanelProfile.setBounds(10, 124, 504, 214);
		Profile.add(CustDetailsPanelProfile);
		CustDetailsPanelProfile.setLayout(null);

		lblNameProfile = new JLabel("Name");
		lblNameProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNameProfile.setBounds(34, 11, 120, 25);
		CustDetailsPanelProfile.add(lblNameProfile);

		lblEmailProfile = new JLabel("Email");
		lblEmailProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmailProfile.setBounds(34, 46, 120, 25);
		CustDetailsPanelProfile.add(lblEmailProfile);

		lblPhoneProfile = new JLabel("Phone");
		lblPhoneProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPhoneProfile.setBounds(34, 81, 120, 25);
		CustDetailsPanelProfile.add(lblPhoneProfile);

		lblDebitcreditCardProfile = new JLabel("Debit/Credit Card #");
		lblDebitcreditCardProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDebitcreditCardProfile.setBounds(34, 116, 120, 25);
		CustDetailsPanelProfile.add(lblDebitcreditCardProfile);

		lblAccessLevelProfile = new JLabel("Access Level");
		lblAccessLevelProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAccessLevelProfile.setBounds(34, 151, 120, 25);
		CustDetailsPanelProfile.add(lblAccessLevelProfile);

		lblSubscriptionTypeProfile = new JLabel("Subscription Type");
		lblSubscriptionTypeProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSubscriptionTypeProfile.setBounds(34, 186, 120, 25);
		CustDetailsPanelProfile.add(lblSubscriptionTypeProfile);

		lblNameCustProfile = new JLabel("");
		lblNameCustProfile.setBounds(154, 11, 199, 25);
		CustDetailsPanelProfile.add(lblNameCustProfile);
		lblNameCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblEmailCustProfile = new JLabel("");
		lblEmailCustProfile.setBounds(154, 46, 199, 25);
		CustDetailsPanelProfile.add(lblEmailCustProfile);
		lblEmailCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblPhoneCustProfile = new JLabel("");
		lblPhoneCustProfile.setBounds(154, 81, 199, 25);
		CustDetailsPanelProfile.add(lblPhoneCustProfile);
		lblPhoneCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblDebitcreditCardCustProfile = new JLabel("");
		lblDebitcreditCardCustProfile.setBounds(154, 116, 199, 25);
		CustDetailsPanelProfile.add(lblDebitcreditCardCustProfile);
		lblDebitcreditCardCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblSubscriptionTypeCustProfile = new JLabel("");
		lblSubscriptionTypeCustProfile.setBounds(154, 186, 199, 25);
		CustDetailsPanelProfile.add(lblSubscriptionTypeCustProfile);
		lblSubscriptionTypeCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblLoyaltyPointsProfile = new JLabel("Loyalty Points:");
		lblLoyaltyPointsProfile.setBounds(398, 116, 85, 14);
		CustDetailsPanelProfile.add(lblLoyaltyPointsProfile);
		lblLoyaltyPointsProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		lblLoyaltyPointsCustProfile = new JLabel("000");
		lblLoyaltyPointsCustProfile.setBounds(398, 131, 85, 24);
		CustDetailsPanelProfile.add(lblLoyaltyPointsCustProfile);
		lblLoyaltyPointsCustProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoyaltyPointsCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblIdProfile = new JLabel("ID");
		lblIdProfile.setBounds(436, 63, 15, 15);
		CustDetailsPanelProfile.add(lblIdProfile);
		lblIdProfile.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		lblidCustProfile = new JLabel("0");
		lblidCustProfile.setBounds(410, 78, 65, 24);
		CustDetailsPanelProfile.add(lblidCustProfile);
		lblidCustProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblidCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblAccessLevelCustProfile = new JLabel("");
		lblAccessLevelCustProfile.setBounds(154, 151, 199, 25);
		CustDetailsPanelProfile.add(lblAccessLevelCustProfile);
		lblAccessLevelCustProfile.setFont(new Font("Times New Roman", Font.BOLD, 20));

		btnIssueNewRentalProfile = new JButton("Issue New Rental");
		btnIssueNewRentalProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnGoToHomeProfile = new JButton("Go to Home");
		btnGoToHomeProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(Home);
			}
		});
		btnGoToHomeProfile.setBounds(524, 188, 140, 25);
		Profile.add(btnGoToHomeProfile);
		btnIssueNewRentalProfile.setBounds(524, 223, 140, 25);
		Profile.add(btnIssueNewRentalProfile);

		btnViewIssuedRentalsProfile = new JButton("View Issued \r\nRentals");
		btnViewIssuedRentalsProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnViewIssuedRentalsProfile.setBounds(524, 258, 140, 25);
		Profile.add(btnViewIssuedRentalsProfile);
	}

	public void EditCustomerGUI() {
		EditCustomer = new JPanel();
		layeredPane.add(EditCustomer, "name_2671265022984500");
		EditCustomer.setLayout(null);

		lblEditCustomer = new JLabel("Edit Customer");
		lblEditCustomer.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblEditCustomer.setBounds(10, 11, 195, 32);
		EditCustomer.add(lblEditCustomer);

		lblCustomerDetailsEditCust = new JLabel("Customer Details");
		lblCustomerDetailsEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCustomerDetailsEditCust.setBounds(204, 119, 135, 22);
		EditCustomer.add(lblCustomerDetailsEditCust);

		CustomerDetailsPanelEditCust = new JPanel();
		CustomerDetailsPanelEditCust.setBounds(10, 144, 534, 194);
		EditCustomer.add(CustomerDetailsPanelEditCust);
		CustomerDetailsPanelEditCust.setLayout(null);

		lblNameEditCust = new JLabel("Name");
		lblNameEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNameEditCust.setBounds(101, 11, 120, 20);
		CustomerDetailsPanelEditCust.add(lblNameEditCust);

		lblEmailEditCust = new JLabel("Email");
		lblEmailEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmailEditCust.setBounds(101, 42, 120, 20);
		CustomerDetailsPanelEditCust.add(lblEmailEditCust);

		lblPhoneEditCust = new JLabel("Phone");
		lblPhoneEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPhoneEditCust.setBounds(101, 73, 120, 20);
		CustomerDetailsPanelEditCust.add(lblPhoneEditCust);

		lblDebitcreditCardEditCust = new JLabel("Debit/Credit Card #");
		lblDebitcreditCardEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDebitcreditCardEditCust.setBounds(101, 104, 120, 20);
		CustomerDetailsPanelEditCust.add(lblDebitcreditCardEditCust);

		lblAccessLevelEditCust = new JLabel("Access Level");
		lblAccessLevelEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAccessLevelEditCust.setBounds(101, 135, 120, 20);
		CustomerDetailsPanelEditCust.add(lblAccessLevelEditCust);

		lblSubscriptionEditCust = new JLabel("Subscription Type");
		lblSubscriptionEditCust.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSubscriptionEditCust.setBounds(101, 166, 120, 20);
		CustomerDetailsPanelEditCust.add(lblSubscriptionEditCust);

		nameFieldEditCust = new JTextField();
		nameFieldEditCust.setBounds(231, 12, 200, 20);
		CustomerDetailsPanelEditCust.add(nameFieldEditCust);
		nameFieldEditCust.setColumns(10);

		emailFieldEditCust = new JTextField();
		emailFieldEditCust.setBounds(231, 43, 200, 20);
		CustomerDetailsPanelEditCust.add(emailFieldEditCust);
		emailFieldEditCust.setColumns(10);

		phoneFieldEditCust = new JTextField();
		phoneFieldEditCust.setBounds(231, 74, 200, 20);
		CustomerDetailsPanelEditCust.add(phoneFieldEditCust);
		phoneFieldEditCust.setColumns(10);

		cardNumberFieldEditCust = new JTextField();
		cardNumberFieldEditCust.setBounds(231, 105, 200, 20);
		CustomerDetailsPanelEditCust.add(cardNumberFieldEditCust);
		cardNumberFieldEditCust.setColumns(10);

		accesslvlComboEditCust = new JComboBox(accesslvl);
		accesslvlComboEditCust.setBounds(231, 136, 200, 20);
		CustomerDetailsPanelEditCust.add(accesslvlComboEditCust);

		subscriptionComboEditCust = new JComboBox(subscription);
		subscriptionComboEditCust.setBounds(231, 167, 200, 20);
		CustomerDetailsPanelEditCust.add(subscriptionComboEditCust);

		btnUpdateEditCust = new JButton("Update");
		btnUpdateEditCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(index);
				System.out.println(model.getValueAt(index, 5).toString());
				customer = new Customer(Integer.parseInt(model.getValueAt(index, 5).toString()));
				if (nameFieldEditCust.getText().isEmpty() || emailFieldEditCust.getText().isEmpty()
						|| phoneFieldEditCust.getText().isEmpty() || cardNumberFieldEditCust.getText().isEmpty()
						|| accesslvlComboEditCust.getSelectedIndex() == 0
						|| subscriptionComboEditCust.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(rootPane, "Fill all the Fields!!!");
				} else if (!inputValidation.validateAlphabets(nameFieldEditCust.getText())
						|| !inputValidation.validateEmail(emailFieldEditCust.getText())
						|| !inputValidation.validateNumbers(phoneFieldEditCust.getText())
						|| !inputValidation.validateNumbers(cardNumberFieldEditCust.getText())) {
					JOptionPane.showMessageDialog(rootPane, "Details are not Valid!!!");
				} else {
					if (phoneFieldEditCust.getText().length() > 13) {
						JOptionPane.showMessageDialog(rootPane, "Phone Number limit exceeds 13 digits", "Error", 1);
					} else if (cardNumberFieldEditCust.getText().length() > 16) {
						JOptionPane.showMessageDialog(rootPane, "Card Number limit exceeds 16 digits", "Error", 1);
					} else {
						customer.setNME(nameFieldEditCust.getText());
						customer.setEMAIL(emailFieldEditCust.getText());
						customer.setPHNE(Long.parseLong(phoneFieldEditCust.getText()));
						customer.setACC_CRD(Long.parseLong(cardNumberFieldEditCust.getText()));
						customer.setACCS_LVL(accesslvlComboEditCust.getSelectedItem().toString());
						customer.setSBSC(subscriptionComboEditCust.getSelectedItem().toString());

						customerDao.updateCustomer(Integer.parseInt(model.getValueAt(index, 0).toString()), customer);
						customer = new Customer();
						JOptionPane.showMessageDialog(rootPane, "Success");
						switchPanels(Home);
					}
				}
			}
		});
		btnUpdateEditCust.setBounds(554, 239, 110, 23);
		EditCustomer.add(btnUpdateEditCust);

		btnGoToHomeEditCust = new JButton("Go to Home");
		btnGoToHomeEditCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = -1;
				switchPanels(Home);
				customer = new Customer();
			}
		});
		btnGoToHomeEditCust.setBounds(554, 205, 110, 23);
		EditCustomer.add(btnGoToHomeEditCust);

		LogoPanelEditCust = new JPanel();
		LogoPanelEditCust.setLayout(null);
		LogoPanelEditCust.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LogoPanelEditCust.setBounds(432, 11, 232, 95);
		EditCustomer.add(LogoPanelEditCust);

		lblLogoNameEditCust = new JLabel("Ultra-Vision");
		lblLogoNameEditCust.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogoNameEditCust.setFont(new Font("Segoe Script", Font.BOLD, 30));
		lblLogoNameEditCust.setBounds(6, 16, 220, 49);
		LogoPanelEditCust.add(lblLogoNameEditCust);

		lblLogoCityNameEditCust = new JLabel("Dublin.");
		lblLogoCityNameEditCust.setFont(new Font("Segoe Print", Font.PLAIN, 14));
		lblLogoCityNameEditCust.setBounds(177, 62, 49, 26);
		LogoPanelEditCust.add(lblLogoCityNameEditCust);
	}

	public App(Staff staff) {

		setResizable(false);

		InstanciateApp(staff);

		HomeGUI();
		RegisterCustomerGUI();
		SearchCustomerGUI();
		ProfileGUI();
		EditCustomerGUI();
		

		setVisible(true);
	}
}
