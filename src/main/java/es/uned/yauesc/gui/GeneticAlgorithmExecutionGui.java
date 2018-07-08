package es.uned.yauesc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class GeneticAlgorithmExecutionGui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1920379278298666697L;

	private MainFrame mainFrame;
	
	private JButton btnStop;
	private JButton btnStart;
	private JPanel panelMainAlgorithm;
	private ParallelExecutionGui panelMainAlgorithmParallel;
	private SingleExecutionGui panelMainAlgorithmSingle;
	private SingleExecutionGui panelFirstAlgorithmSingle;
	private SingleExecutionGui panelSecondAlgorithmSingle;
	private SingleExecutionGui panelThirdAlgorithmSingle;
	private JPanelObserverGeneticAlgorithmController panelAdvise;
	private JPanel panelInProgress;
	private JPanel panelFinished;

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
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		panelNorthWest.add(btnStop);
		
		JPanel panelMain = new JPanel();
		panelMain.setMinimumSize(new Dimension(1030, 10));
		panelMain.setMaximumSize(new Dimension(1030, 32767));
		add(panelMain);
		panelMain.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		panelAdvise = new JPanelObserverGeneticAlgorithmController();
		panelAdvise.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAdvise.setPreferredSize(new Dimension(1030, 70));
		panelMain.add(panelAdvise);
		panelAdvise.setLayout(new CardLayout(0, 0));
		panelAdvise.setVisible(false);
		
		panelInProgress = new JPanel();
		panelAdvise.add(panelInProgress, "name_25225078782987");
		panelInProgress.setVisible(false);
		
		JLabel lblInProgress = new JLabel("IN PROGRESS");
		lblInProgress.setForeground(Color.RED);
		lblInProgress.setFont(new Font("Dialog", Font.BOLD, 48));
		panelInProgress.add(lblInProgress);
		
		panelFinished = new JPanel();
		panelAdvise.add(panelFinished, "name_25230197734628");
		panelFinished.setVisible(false);
		
		JLabel lblFinished = new JLabel("FINISHED");
		lblFinished.setForeground(Color.GREEN);
		lblFinished.setFont(new Font("Dialog", Font.BOLD, 48));
		panelFinished.add(lblFinished);
		
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
				//TODO
				btnStop.setEnabled(true);
				btnStart.setEnabled(false);
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
					panelMainAlgorithmSingle.setVisible(true);
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
				geneticAlgorithmController.registerObserver(panelAdvise);
				panelAdvise.setVisible(true);
				panelInProgress.setVisible(true);
				panelFinished.setVisible(false);
				
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
			public void actionPerformed(ActionEvent arg0) {
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
		panelAdvise.setVisible(false);
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
			panelFinished.setVisible(true);
			panelInProgress.setVisible(false);
			btnStart.setEnabled(true);
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
			paintImmediately(0, 0, 1030, 200);
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
			paintImmediately(0, 0, 1030, 200);
		}

	}
}
