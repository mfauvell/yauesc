package es.uned.yauesc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import es.uned.yauesc.dataUned.FitnessUned;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithm;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmController;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmControllerObserver;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmObserver;
import es.uned.yauesc.geneticAlgorithm.IllegalParameterValueCheckedException;

import java.awt.CardLayout;

public class GeneticAlgorithmExecutionGui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1920379278298666697L;

	private MainFrame mainFrame;
	
	private JButton btnStop;
	private JButton btnStart;
	private JLabel working;
	private JPanel panelMainAlgorithm;
	private ParallelExecutionGui panelMainAlgorithmParallel;
	private SingleExecutionGui panelMainAlgorithmSingle;
	private SingleExecutionGui panelFirstAlgorithmSingle;
	private SingleExecutionGui panelSecondAlgorithmSingle;
	private SingleExecutionGui panelThirdAlgorithmSingle;
	private JPanelObserverGeneticAlgorithmController panelWorkingIcon;

	/**
	 * Create the panel.
	 */
	public GeneticAlgorithmExecutionGui(GeneticAlgorithmController geneticAlgorithmController, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		setLayout(new BorderLayout(0, 0));
		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorthWest = new JPanel();
		panelNorth.add(panelNorthWest, BorderLayout.WEST);
		panelNorthWest.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		btnStart = new JButton("Start");
		panelNorthWest.add(btnStart);
		
		btnStop = new JButton("Stop");
		panelNorthWest.add(btnStop);
		

		JPanel panelNorthEast = new JPanel();
		panelNorth.add(panelNorthEast, BorderLayout.EAST);
		panelNorthEast.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));

		panelWorkingIcon = new JPanelObserverGeneticAlgorithmController();
		FlowLayout flowLayout_1 = (FlowLayout) panelWorkingIcon.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		panelWorkingIcon.setPreferredSize(new Dimension(30, 30));
		working = new JLabel(new ImageIcon("./images/working.gif"));
		working.setEnabled(false);
		working.setVisible(false);
		panelWorkingIcon.add(working);
		panelNorthEast.add(panelWorkingIcon);
		
		JPanel panelMain = new JPanel();
		panelMain.setMinimumSize(new Dimension(1030, 10));
		panelMain.setMaximumSize(new Dimension(1030, 32767));
		add(panelMain);
		panelMain.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		panelMainAlgorithm = new JPanel();
		panelMainAlgorithm.setLayout(new CardLayout(0, 0));
		panelMainAlgorithm.setMinimumSize(new Dimension(1030, 50));
		panelMainAlgorithm.setMaximumSize(new Dimension(1030, 100));
		panelMain.add(panelMainAlgorithm);
		
		panelMainAlgorithmParallel = new ParallelExecutionGui("Main genetic algorithm");
		panelMainAlgorithm.add(panelMainAlgorithmParallel);
		
		panelMainAlgorithmSingle = new SingleExecutionGui("Main genetic algorithm");
		panelMainAlgorithm.add(panelMainAlgorithmSingle);
		
		panelFirstAlgorithmSingle = new SingleExecutionGui("First genetic algorithm");
		panelMain.add(panelFirstAlgorithmSingle);
		
		panelSecondAlgorithmSingle = new SingleExecutionGui("Second genetic algorithm");
		panelMain.add(panelSecondAlgorithmSingle);
		
		panelThirdAlgorithmSingle = new SingleExecutionGui("Third genetic algorithm");
		panelMain.add(panelThirdAlgorithmSingle);

		btnStop.setEnabled(false);
		panelMainAlgorithm.setVisible(false);
		panelMainAlgorithmParallel.setVisible(false);
		panelMainAlgorithmSingle.setVisible(false);
		panelFirstAlgorithmSingle.setVisible(false);
		panelSecondAlgorithmSingle.setVisible(false);
		panelThirdAlgorithmSingle.setVisible(false);
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				btnStop.setEnabled(true);
				btnStart.setEnabled(false);
				try {
					geneticAlgorithmController.resetGeneticAlgorithm();
				} catch (IllegalParameterValueCheckedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainFrame.disableExecutionButtons();
				mainFrame.resetFromGeneticAlgorithmExecution();
				int generations = geneticAlgorithmController.getGenerations();
				if (geneticAlgorithmController.isParallel()) {
					geneticAlgorithmController.registerObserverMainGeneticAlgorithm(panelMainAlgorithmParallel);
					geneticAlgorithmController.registerObserverFirstGeneticAlgorithm(panelFirstAlgorithmSingle);
					geneticAlgorithmController.registerObserverSecondGeneticAlgorithm(panelSecondAlgorithmSingle);
					geneticAlgorithmController.registerObserverThirdGeneticAlgorithm(panelThirdAlgorithmSingle);
					panelMainAlgorithmParallel.preparePanel(generations, geneticAlgorithmController.getGenerarionsToMigrate());
					panelFirstAlgorithmSingle.preparePanel(generations);
					panelSecondAlgorithmSingle.preparePanel(generations);
					panelThirdAlgorithmSingle.preparePanel(generations);
					panelMainAlgorithm.setVisible(true);
					panelMainAlgorithmParallel.setVisible(true);
					panelMainAlgorithmSingle.setVisible(false);
					panelFirstAlgorithmSingle.setVisible(true);
					panelSecondAlgorithmSingle.setVisible(true);
					panelThirdAlgorithmSingle.setVisible(true);
				} else {
					panelMainAlgorithmSingle.preparePanel(generations);
					geneticAlgorithmController.registerObserverMainGeneticAlgorithm(panelMainAlgorithmSingle);
					panelMainAlgorithm.setVisible(true);
					panelMainAlgorithmSingle.setVisible(true);
					panelMainAlgorithmParallel.setVisible(false);
					panelFirstAlgorithmSingle.setVisible(false);
					panelSecondAlgorithmSingle.setVisible(false);
					panelThirdAlgorithmSingle.setVisible(false);
				}
				geneticAlgorithmController.registerObserver(panelWorkingIcon);
				working.setVisible(true);
				
				@SuppressWarnings("rawtypes")
				SwingWorker worker = new SwingWorker() {
					@Override
					protected Object doInBackground() throws Exception {
						geneticAlgorithmController.startExecution();
						return null;
					}
				};
				worker.execute();
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				geneticAlgorithmController.stopExecution();
				btnStop.setEnabled(false);
			}
		});
	}
	
	public void initialize() {
		reset();
	}
	
	private void reset() {
		btnStop.setEnabled(false);
		panelMainAlgorithm.setVisible(false);
		panelMainAlgorithmParallel.setVisible(false);
		panelMainAlgorithmParallel.reset();
		panelMainAlgorithmSingle.setVisible(false);
		panelMainAlgorithmSingle.reset();
		panelFirstAlgorithmSingle.setVisible(false);
		panelFirstAlgorithmSingle.reset();
		panelSecondAlgorithmSingle.setVisible(false);
		panelSecondAlgorithmSingle.reset();
		panelThirdAlgorithmSingle.setVisible(false);
		panelThirdAlgorithmSingle.reset();
	}
	
	private class JPanelObserverGeneticAlgorithmController extends JPanel implements GeneticAlgorithmControllerObserver {

		private static final long serialVersionUID = 8199103030233916249L;

		@Override
		public void updateGeneticAlgorithmControllerObserver(GeneticAlgorithmController geneticAlgorithmController) {
			working.setVisible(false);
			btnStart.setEnabled(true);
			mainFrame.enableExecutionButtons();
			mainFrame.setObtainResultsTab();
		}
		
	}
	
	private class ParallelExecutionGui extends JPanel implements GeneticAlgorithmObserver {
	
		private static final long serialVersionUID = 5760354560321457062L;
		
		private JTextField firstFitness;
		private JTextField secondFitness;
		private JTextField thirdFitness;
		private JTextField total;
		private JTextField done;
		private JProgressBar migrationsProgressBar;

	
		public ParallelExecutionGui(String title) {
			setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
			setPreferredSize(new Dimension(1030, 100));
			setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JPanel panelBasicInformation = new JPanel();
			FlowLayout fl_panelBasicInformation = (FlowLayout) panelBasicInformation.getLayout();
			fl_panelBasicInformation.setAlignment(FlowLayout.LEFT);
			panelBasicInformation.setPreferredSize(new Dimension(1010, 30));
			add(panelBasicInformation);
			JLabel lblBestSolutionFitness = new JLabel("Best solution fitness:");
			panelBasicInformation.add(lblBestSolutionFitness);
			
			JLabel lblFirstFitness = new JLabel("First");
			panelBasicInformation.add(lblFirstFitness);
			
			firstFitness = new JTextField();
			panelBasicInformation.add(firstFitness);
			firstFitness.setColumns(5);
			
			JLabel lblsecondFitness = new JLabel("Second");
			panelBasicInformation.add(lblsecondFitness);
			
			secondFitness = new JTextField();
			panelBasicInformation.add(secondFitness);
			secondFitness.setColumns(5);
			
			JLabel lblthirdFitness = new JLabel("Third");
			panelBasicInformation.add(lblthirdFitness);
			
			thirdFitness = new JTextField();
			panelBasicInformation.add(thirdFitness);
			thirdFitness.setColumns(5);
			
			JLabel lblMigrations = new JLabel("Migrations:");
			panelBasicInformation.add(lblMigrations);
			
			JLabel lblTotal = new JLabel("Total");
			panelBasicInformation.add(lblTotal);
			
			total = new JTextField();
			panelBasicInformation.add(total);
			total.setColumns(5);
			
			JLabel lblDone = new JLabel("Done");
			panelBasicInformation.add(lblDone);
			
			done = new JTextField();
			panelBasicInformation.add(done);
			done.setColumns(5);
			
			JPanel panelProgressBar = new JPanel();
			panelProgressBar.setPreferredSize(new Dimension(1010, 35));
			add(panelProgressBar);
			
			migrationsProgressBar = new JProgressBar();
			migrationsProgressBar.setMaximum(100);
			migrationsProgressBar.setPreferredSize(new Dimension(1010, 25));
			migrationsProgressBar.setString("Migrations");
			migrationsProgressBar.setStringPainted(true);
			panelProgressBar.add(migrationsProgressBar);
			
		}
		
		public void reset()	{
			firstFitness.setText("");
			secondFitness.setText("");
			thirdFitness.setText("");
			total.setText("");
			done.setText("");
			migrationsProgressBar.setValue(0);
		}
		
		public void preparePanel(int generations, int generationsToMigrate) {
			total.setText("" + generations/generationsToMigrate);
			done.setText("0");
			migrationsProgressBar.setMaximum(generations/generationsToMigrate);
		}

		@Override
		public void updateGeneticAlgorithmObserver(GeneticAlgorithm geneticAlgorithm) {
			FitnessUned solutionFitness = (FitnessUned) geneticAlgorithm.getSolution().getFitness();
			firstFitness.setText("" + solutionFitness.getFirstLevel());
			secondFitness.setText("" + solutionFitness.getSecondLevel());
			thirdFitness.setText("" + solutionFitness.getThirdLevel());
			migrationsProgressBar.setValue(migrationsProgressBar.getValue() + 1);
			done.setText("" + (Integer.parseInt(done.getText()) + 1));
			repaint();
		}

	}


	private class SingleExecutionGui extends JPanel implements GeneticAlgorithmObserver {

		private static final long serialVersionUID = 8635653322025345551L;
		private JTextField firstFitness;
		private JTextField secondFitness;
		private JTextField thirdFitness;
		private JTextField total;
		private JTextField done;
		JProgressBar generationsProgressBar;
		
	
		public SingleExecutionGui(String title) {
			setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
			setPreferredSize(new Dimension(1030, 100));
			setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JPanel panelBasicInformation = new JPanel();
			FlowLayout fl_panelBasicInformation = (FlowLayout) panelBasicInformation.getLayout();
			fl_panelBasicInformation.setAlignment(FlowLayout.LEFT);
			panelBasicInformation.setPreferredSize(new Dimension(1010, 30));
			add(panelBasicInformation);
			JLabel lblBestSolutionFitness = new JLabel("Best solution fitness:");
			panelBasicInformation.add(lblBestSolutionFitness);
			
			JLabel lblFirstFitness = new JLabel("First");
			panelBasicInformation.add(lblFirstFitness);
			
			firstFitness = new JTextField();
			panelBasicInformation.add(firstFitness);
			firstFitness.setColumns(5);
			
			JLabel lblsecondFitness = new JLabel("Second");
			panelBasicInformation.add(lblsecondFitness);
			
			secondFitness = new JTextField();
			panelBasicInformation.add(secondFitness);
			secondFitness.setColumns(5);
			
			JLabel lblthirdFitness = new JLabel("Third");
			panelBasicInformation.add(lblthirdFitness);
			
			thirdFitness = new JTextField();
			panelBasicInformation.add(thirdFitness);
			thirdFitness.setColumns(5);
			
			JLabel lblGenerations = new JLabel("Generations:");
			panelBasicInformation.add(lblGenerations);
			
			JLabel lblTotal = new JLabel("Total");
			panelBasicInformation.add(lblTotal);
			
			total = new JTextField();
			panelBasicInformation.add(total);
			total.setColumns(5);
			
			JLabel lblDone = new JLabel("Done");
			panelBasicInformation.add(lblDone);
			
			done = new JTextField();
			panelBasicInformation.add(done);
			done.setColumns(5);
			
			JPanel panelProgressBar = new JPanel();
			panelProgressBar.setPreferredSize(new Dimension(1010, 35));
			add(panelProgressBar);
			
			generationsProgressBar = new JProgressBar();
			generationsProgressBar.setMaximum(100);
			generationsProgressBar.setPreferredSize(new Dimension(1010, 25));
			generationsProgressBar.setString("Generations");
			generationsProgressBar.setStringPainted(true);
			panelProgressBar.add(generationsProgressBar);
			
		}
		
		public void reset() {
			firstFitness.setText("");
			secondFitness.setText("");
			thirdFitness.setText("");
			total.setText("");
			done.setText("");
			generationsProgressBar.setValue(0);
		}
		
		public void preparePanel(int generations) {
			total.setText("" + generations);
			done.setText("0");
			generationsProgressBar.setMaximum(generations);
		}

		@Override
		public void updateGeneticAlgorithmObserver(GeneticAlgorithm geneticAlgorithm) {
			FitnessUned solutionFitness = (FitnessUned) geneticAlgorithm.getSolution().getFitness();
			firstFitness.setText("" + solutionFitness.getFirstLevel());
			secondFitness.setText("" + solutionFitness.getSecondLevel());
			thirdFitness.setText("" + solutionFitness.getThirdLevel());
			generationsProgressBar.setValue(generationsProgressBar.getValue() + 1);
			done.setText("" + (Integer.parseInt(done.getText()) + 1));
			paintImmediately(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}

	}
}
