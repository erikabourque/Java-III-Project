package group1.clinic.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import group1.clinic.business.Clinic;
import group1.clinic.business.Priority;
import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitManager;
import dw317.clinic.business.interfaces.Visit;
import dw317.lib.medication.DINMedication;
import dw317.lib.medication.Medication;
import dw317.lib.medication.NDCMedication;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import java.awt.Color;

/**
 * GUI controller for Dawson Medical Clininc.
 * 
 * @author Uen Yi Cindy Hunf
 * @version 09/12/2015
 *
 */
public class GUIViewController extends JFrame implements Observer {

	// VARIABLES
	private Clinic model;
	private String result;
	private Medication medication;

	// PANELS ANS SUCH
	private JPanel contentPane;
	private JPanel dequeuePnl;
	private JPanel createPnl;
	private JTabbedPane tabbedPane;

	// BUTTONS
	private JButton nextTriageBtn;
	private JButton prioritizeTriageBtn;
	private JButton nextToExamineBtn;
	private JButton priorityOkBtn;

	// RADIO BUTTONS
	private JRadioButton reanimationRBtn;
	private JRadioButton veryUrgentRBtn;
	private JRadioButton urgentRBtn;
	private JRadioButton lessUrgentRBtn;
	private JRadioButton notUrgentRBtn;

	// TEXT FIELDS
	private JTextField priorityTxt;
	private JTextField complaintTxt;
	private JTextField medNumberTxt;
	private JTextField medSchemeTxt;
	private JTextField medNameTxt;
	private JTextField conditionTxt;
	private JTextField phoneNumberTxt;
	private JTextField ramqTxt;
	private JTextField lastNameTxt;
	private JTextField firstNameTxt;

	// OTHER
	private JTextArea display;
	private JLabel lblDawsonMedicalClinic;
	private JLabel statusLbl;

	/**
	 * Create the frame.
	 */
	public GUIViewController(Observer model) {
		setResizable(false);
		setBackground(Color.DARK_GRAY);
		setTitle("Dawson Clinic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);

		// PANELS
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dequeuePnl = new JPanel();
		dequeuePnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		dequeuePnl.setLayout(null);

		// TABS
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 450, 377);
		contentPane.add(tabbedPane);

		createPnl = new JPanel();
		createPnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		createPnl.setLayout(null);
		tabbedPane.addTab("New Patient", createPnl);
		tabbedPane.addTab("Dequeue Visits", dequeuePnl);

		// MENU
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDawsonClinic = new JMenu("Dawson Clinic");
		menuBar.add(mnDawsonClinic);

		JMenuItem menuItemExit = new JMenuItem("Exit");
		mnDawsonClinic.add(menuItemExit);
		menuItemExit.addActionListener(new menuItemExitListener());

		JMenu mnCredits = new JMenu("Credits");
		//menuBar.add(mnCredits);

		// LABEL
		lblDawsonMedicalClinic = new JLabel("Dawson Medical Clinic");
		lblDawsonMedicalClinic.setFont(new Font("Andalus", Font.PLAIN, 19));
		lblDawsonMedicalClinic.setHorizontalAlignment(SwingConstants.CENTER);
		lblDawsonMedicalClinic.setBounds(247, 0, 197, 23);
		contentPane.add(lblDawsonMedicalClinic);

		statusLbl = new JLabel("Status Bar");
		statusLbl.setBounds(10, 380, 434, 20);
		contentPane.add(statusLbl);

		// *****************************************************************************************
		// FOR DEQUEUE PANEL
		// BUTTONS
		nextTriageBtn = new JButton("To Next Triage");
		nextTriageBtn.setFont(new Font("Bell MT", Font.BOLD, 12));
		nextTriageBtn.setBounds(20, 11, 135, 23);
		dequeuePnl.add(nextTriageBtn);
		nextTriageBtn.addActionListener(new nextTriageBtnListener());

		prioritizeTriageBtn = new JButton("Prioritize Triage");
		prioritizeTriageBtn.setFont(new Font("Bell MT", Font.BOLD, 12));
		prioritizeTriageBtn.setBounds(20, 46, 135, 23);
		dequeuePnl.add(prioritizeTriageBtn);
		prioritizeTriageBtn.addActionListener(new prioritizeTriageBtnListener());

		nextToExamineBtn = new JButton("To Next Examine");
		nextToExamineBtn.setFont(new Font("Bell MT", Font.BOLD, 12));
		nextToExamineBtn.setBounds(20, 81, 135, 23);
		dequeuePnl.add(nextToExamineBtn);
		nextToExamineBtn.addActionListener(new nextToExamineBtnListener());

		priorityOkBtn = new JButton("OK");
		priorityOkBtn.setFont(new Font("Bell MT", Font.BOLD, 12));
		priorityOkBtn.setBounds(58, 309, 49, 20);
		dequeuePnl.add(priorityOkBtn);
		priorityOkBtn.setVisible(false);
		priorityOkBtn.addActionListener(new priorityOkBtnListener());

		// RADIO BUTTONS
		reanimationRBtn = new JRadioButton("Reanimation");
		reanimationRBtn.setSelected(true);
		reanimationRBtn.setFont(new Font("Stencil", Font.PLAIN, 11));
		reanimationRBtn.setBounds(30, 129, 116, 23);
		dequeuePnl.add(reanimationRBtn);
		reanimationRBtn.setVisible(false);

		veryUrgentRBtn = new JRadioButton("Very Urgent");
		veryUrgentRBtn.setFont(new Font("Stencil", Font.PLAIN, 11));
		veryUrgentRBtn.setBounds(30, 164, 116, 23);
		dequeuePnl.add(veryUrgentRBtn);
		veryUrgentRBtn.setVisible(false);

		urgentRBtn = new JRadioButton("Urgent");
		urgentRBtn.setFont(new Font("Stencil", Font.PLAIN, 11));
		urgentRBtn.setBounds(30, 199, 116, 23);
		dequeuePnl.add(urgentRBtn);
		urgentRBtn.setVisible(false);

		lessUrgentRBtn = new JRadioButton("Less Urgent");
		lessUrgentRBtn.setFont(new Font("Stencil", Font.PLAIN, 11));
		lessUrgentRBtn.setBounds(30, 234, 116, 23);
		dequeuePnl.add(lessUrgentRBtn);
		lessUrgentRBtn.setVisible(false);

		notUrgentRBtn = new JRadioButton("Not Urgent");
		notUrgentRBtn.setFont(new Font("Stencil", Font.PLAIN, 11));
		notUrgentRBtn.setBounds(30, 269, 116, 23);
		dequeuePnl.add(notUrgentRBtn);
		notUrgentRBtn.setVisible(false);

		display = new JTextArea();
		display.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		display.setBackground(SystemColor.menu);
		display.setBounds(197, 45, 213, 116);
		dequeuePnl.add(display);

		display.setText("Results will be displayed here.");

		// ************************************************************************************
		// FOR CREATE PANEL
		// LABEL
		JLabel firstNameLbl = new JLabel("First Name");
		firstNameLbl.setBounds(43, 11, 100, 23);
		firstNameLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		createPnl.add(firstNameLbl);

		JLabel lastNameLbl = new JLabel("Last Name");
		lastNameLbl.setBounds(43, 41, 100, 23);
		lastNameLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		createPnl.add(lastNameLbl);

		JLabel ramqLbl = new JLabel("RAMQ ID");
		ramqLbl.setBounds(43, 71, 100, 23);
		ramqLbl.setFont(new Font("Bell MT", Font.PLAIN, 13));
		createPnl.add(ramqLbl);

		JLabel phoneNumberLbl = new JLabel("Phone number");
		phoneNumberLbl.setBounds(43, 101, 100, 23);
		phoneNumberLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		createPnl.add(phoneNumberLbl);

		JLabel conditionLbl = new JLabel("Condition");
		conditionLbl.setBounds(43, 131, 100, 23);
		conditionLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		createPnl.add(conditionLbl);

		JLabel medicationNameLbl = new JLabel("Medication Name");
		medicationNameLbl.setBounds(43, 161, 150, 23);
		medicationNameLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		createPnl.add(medicationNameLbl);

		JLabel medicationSchemeLbl = new JLabel("Medication Scheme");
		medicationSchemeLbl.setBounds(43, 191, 150, 23);
		medicationSchemeLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		createPnl.add(medicationSchemeLbl);

		JLabel medicationNumberLbl = new JLabel("Medication ID number");
		medicationNumberLbl.setBounds(43, 221, 150, 23);
		medicationNumberLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		createPnl.add(medicationNumberLbl);

		JLabel complaintLbl = new JLabel("Complaint");
		complaintLbl.setBounds(43, 251, 150, 23);
		complaintLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		// createPnl.add(complaintLbl);

		JLabel priorityLbl = new JLabel("Priority");
		priorityLbl.setBounds(43, 281, 150, 23);
		priorityLbl.setFont(new Font("Bell MT", Font.PLAIN, 14));
		// createPnl.add(priorityLbl);

		// TEXT BOX
		firstNameTxt = new JTextField();
		firstNameTxt.setBounds(223, 11, 150, 23);
		createPnl.add(firstNameTxt);

		lastNameTxt = new JTextField();
		lastNameTxt.setBounds(223, 41, 150, 23);
		createPnl.add(lastNameTxt);

		ramqTxt = new JTextField();
		ramqTxt.setBounds(223, 71, 150, 23);
		createPnl.add(ramqTxt);

		phoneNumberTxt = new JTextField();
		phoneNumberTxt.setBounds(223, 101, 150, 23);
		createPnl.add(phoneNumberTxt);

		conditionTxt = new JTextField();
		conditionTxt.setBounds(223, 131, 150, 23);
		createPnl.add(conditionTxt);

		medNameTxt = new JTextField();
		medNameTxt.setBounds(223, 161, 150, 23);
		createPnl.add(medNameTxt);

		medSchemeTxt = new JTextField();
		medSchemeTxt.setBounds(223, 191, 150, 23);
		createPnl.add(medSchemeTxt);

		medNumberTxt = new JTextField();
		medNumberTxt.setBounds(223, 221, 150, 23);
		createPnl.add(medNumberTxt);

		complaintTxt = new JTextField();
		complaintTxt.setBounds(223, 251, 150, 23);
		// createPnl.add(complaintTxt);

		priorityTxt = new JTextField();
		priorityTxt.setBounds(223, 281, 150, 23);
		// createPnl.add(priorityTxt);

		// BUTTON
		JButton createOkBtn = new JButton("OK");
		createOkBtn.setBounds(182, 315, 50, 23);
		createPnl.add(createOkBtn);
		createOkBtn.addActionListener(new createOkBtnListener());

		// OTHER
		this.model = (Clinic) model;
		model.addObserver(this);
		update(model, null);
		this.setVisible(true);

	}

	// *******************************************************************************************
	/**
	 * Inner class event-handler for the 'priority triage' button.
	 */
	private class prioritizeTriageBtnListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			reanimationRBtn.setSelected(true);
			reanimationRBtn.setVisible(true);
			veryUrgentRBtn.setVisible(true);
			urgentRBtn.setVisible(true);
			lessUrgentRBtn.setVisible(true);
			notUrgentRBtn.setVisible(true);
			priorityOkBtn.setVisible(true);
		}
	}

	/**
	 * Inner class event-handler for the 'next to examine' button.
	 */
	private class nextToExamineBtnListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.nextForExamination();
				//result = "Next Visit: \n\n" + model.nextForExamination().get().getPatient().getName().toString();
				//update(model, model.nextForExamination());
			} catch (Exception exception) {
				statusLbl.setText(exception.getMessage());
			}
		}
	}

	/**
	 * Inner class event-handler for the 'next triage' button.
	 */
	private class nextTriageBtnListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				model.nextForTriage();
				//result = "Next Visit:\n\n" + model.nextForTriage().get().getPatient().getName().toString();
				//update(model, model.nextForTriage());
			} catch (Exception exception) {
				statusLbl.setText(exception.getMessage());
			}
		}
	}

	/**
	 * Inner class event-handler for 'ok' button in the priority selection
	 * section.
	 */
	private class priorityOkBtnListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Priority aPriority;
			if (reanimationRBtn.isSelected())
				aPriority = Priority.REANIMATION;
			else if (veryUrgentRBtn.isSelected())
				aPriority = Priority.VERYURGENT;
			else if (urgentRBtn.isSelected())
				aPriority = Priority.URGENT;
			else if (lessUrgentRBtn.isSelected())
				aPriority = Priority.LESSURGENT;
			else if (notUrgentRBtn.isSelected())
				aPriority = Priority.NOTURGENT;

			model.changeTriageVisitPriority(aPriority);
			//result = "Triaged visit priority has been changed to: " + aPriority + ".";
			//update(model, model.changeTriageVisitPriority(aPriority));
			reanimationRBtn.setVisible(false);
			veryUrgentRBtn.setVisible(false);
			urgentRBtn.setVisible(false);
			lessUrgentRBtn.setVisible(false);
			notUrgentRBtn.setVisible(false);
			priorityOkBtn.setVisible(false);
		}
	}

	/**
	 * Inner class event-handler for menu item 'exit'.
	 */
	private class menuItemExitListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				model.closeClinic();
			} catch (IOException ioe) {
			} finally {
				System.exit(0);
			}
		}
	}

	/**
	 * Inner class event-handler for 'ok' button in the new patient tab.
	 */
	private class createOkBtnListener implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				if (medSchemeTxt.getText().equalsIgnoreCase("DIN"))
					medication = new DINMedication(medNumberTxt.getText(), medNameTxt.getText());
				else if (medSchemeTxt.getText().equalsIgnoreCase("NDC"))
					medication = new NDCMedication(medNumberTxt.getText(), medNameTxt.getText());
				else
					throw new IllegalArgumentException("Invalid medication scheme.");

				model.registerNewPatient(firstNameTxt.getText(), lastNameTxt.getText(), ramqTxt.getText(),
						phoneNumberTxt.getText(), medication, conditionTxt.getText());
				// model.createVisit(model.findPatient(ramqTxt.getText()),
				// complaintTxt.getText());
			} catch (Exception exception) {
				statusLbl.setText("Patient not created. " + exception.getMessage());
			}
			statusLbl.setText("Patient created.");
			firstNameTxt.setText("");
			lastNameTxt.setText("");
			ramqTxt.setText("");
			phoneNumberTxt.setText("");
			conditionTxt.setText("");
			medNameTxt.setText("");
			medSchemeTxt.setText("");
			medNumberTxt.setText("");
			// complaintTxt.setText("");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		result = "";
		if ( arg instanceof Optional<?>)
			result = "Next Visit:\n\n" + ((Visit)arg).getPatient().getName().toString();
		else if (arg instanceof Patient)
			result = ((Patient)arg).getName().toString() + "\n" + ((Patient)arg).getRamq().toString();
		else if (arg instanceof Visit)
			result = "Visit created \n\n" + ((Visit)arg).getPatient() + "\nRegistered: " + ((Visit)arg).getRegistrationDateAndTime();
		else if (arg instanceof Priority)
			result = "Priority changed to " + ((Priority)arg);
		display.setText(result);

	}
}
