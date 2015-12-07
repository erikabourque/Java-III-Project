package group1.clinic.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import group1.clinic.business.Clinic;
import group1.clinic.business.Priority;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import java.awt.Color;

public class GUIViewController extends JFrame implements Observer {

	private JPanel contentPane;
	private JPanel dequeuePnl;
	private JPanel createPnl;
	private Clinic model;

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

	private JTextArea display;
	private JLabel lblDawsonMedicalClinic;
	private JTabbedPane tabbedPane;

	/**
	 * Create the frame.
	 */
	public GUIViewController(Observer model) {
		setBackground(Color.DARK_GRAY);
		setResizable(false);
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
		
		createPnl = new JPanel();
		createPnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		createPnl.setLayout(null);
		
		// TABS
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 444, 400);
		contentPane.add(tabbedPane);
		tabbedPane.addTab("Dequeue Visits", dequeuePnl);
		tabbedPane.addTab("New Visits", createPnl);
		

		// MENU
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnDawsonClinic = new JMenu("Dawson Clinic");
		menuBar.add(mnDawsonClinic);

		JMenuItem menuItemExit = new JMenuItem("Exit");
		mnDawsonClinic.add(menuItemExit);
		menuItemExit.addActionListener(new menuItemExitListener());

		JMenu mnCredits = new JMenu("Credits");
		menuBar.add(mnCredits);

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

		// LABEL
		lblDawsonMedicalClinic = new JLabel("Dawson Medical Clinic");
		lblDawsonMedicalClinic.setFont(new Font("Andalus", Font.PLAIN, 19));
		lblDawsonMedicalClinic.setHorizontalAlignment(SwingConstants.CENTER);
		lblDawsonMedicalClinic.setBounds(247, 0, 197, 23);
		contentPane.add(lblDawsonMedicalClinic);

		// OTHER
		this.model = (Clinic) model;
		model.addObserver(this);
		update(model, null);
		this.setVisible(true);

	}

	private class prioritizeTriageBtnListener implements ActionListener {

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

	private class nextToExamineBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			display.setText("Next Visit: \n\n" + model.nextForExamination().get().getPatient().getName().toString());
		}
	}

	private class nextTriageBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			display.setText("Next Visit:\n\n" + model.nextForTriage().get().getPatient().getName().toString());
		}
	}

	private class priorityOkBtnListener implements ActionListener {

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
			display.setText("Triaged visit priority has been cahnged to: " + aPriority + ".");
			reanimationRBtn.setVisible(false);
			veryUrgentRBtn.setVisible(false);
			urgentRBtn.setVisible(false);
			lessUrgentRBtn.setVisible(false);
			notUrgentRBtn.setVisible(false);
			priorityOkBtn.setVisible(false);
		}
	}

	private class menuItemExitListener implements ActionListener {

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

	@Override
	public void update(Observable model, Object arg) {
		// TODO Auto-generated method stub

	}
}
