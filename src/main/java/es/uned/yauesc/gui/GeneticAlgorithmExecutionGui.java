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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

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


	/**
	 * Create the panel.
	 */
	public GeneticAlgorithmExecutionGui() {
		setLayout(new BorderLayout(0, 0));
		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorthWest = new JPanel();
		panelNorth.add(panelNorthWest, BorderLayout.WEST);
		panelNorthWest.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		panelNorthWest.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
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
		
		JPanel panelAdvise = new JPanelObserverGeneticAlgorithmController();
		panelAdvise.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAdvise.setPreferredSize(new Dimension(1030, 70));
		panelMain.add(panelAdvise);
		panelAdvise.setLayout(new CardLayout(0, 0));
		panelAdvise.setVisible(false);
		
		JPanel panelInProgress = new JPanel();
		panelAdvise.add(panelInProgress, "name_25225078782987");
		panelInProgress.setVisible(false);
		
		JLabel lblInProgress = new JLabel("IN PROGRESS");
		lblInProgress.setForeground(Color.RED);
		lblInProgress.setFont(new Font("Dialog", Font.BOLD, 48));
		panelInProgress.add(lblInProgress);
		
		JPanel panelFinished = new JPanel();
		panelAdvise.add(panelFinished, "name_25230197734628");
		panelFinished.setVisible(false);
		
		JLabel lblFinished = new JLabel("FINISHED");
		lblFinished.setForeground(Color.GREEN);
		lblFinished.setFont(new Font("Dialog", Font.BOLD, 48));
		panelFinished.add(lblFinished);
		
		JPanel panelMainAlgorithm = new JPanel();
		panelMainAlgorithm.setLayout(new CardLayout(0, 0));
		panelMainAlgorithm.setMinimumSize(new Dimension(1030, 50));
		panelMainAlgorithm.setMaximumSize(new Dimension(1030, 100));
		panelMain.add(panelMainAlgorithm);
		
		JPanel panelMainAlgorithmParallel = new ParallelExecutionGui("Main genetic algorithm", 10, 10);
		panelMainAlgorithm.add(panelMainAlgorithmParallel);
		
		JPanel panelMainAlgorithmSingle = new SingleExecutionGui("Main genetic algorithm", 10);
		panelMainAlgorithm.add(panelMainAlgorithmSingle);
		panelMainAlgorithmSingle.setVisible(false);
		
		JPanel panelFirstAlgorithmSingle = new SingleExecutionGui("First genetic algorithm", 10);
		panelMain.add(panelFirstAlgorithmSingle);
		
		JPanel panelSecondAlgorithmSingle = new SingleExecutionGui("Second genetic algorithm", 10);
		panelMain.add(panelSecondAlgorithmSingle);
		
		JPanel panelThirdAlgorithmSingle = new SingleExecutionGui("Third genetic algorithm", 10);
		panelMain.add(panelThirdAlgorithmSingle);

	}
	
	private class JPanelObserverGeneticAlgorithmController extends JPanel implements GeneticAlgorithmControllerObserver {

		private static final long serialVersionUID = 8199103030233916249L;

		@Override
		public void updateGeneticAlgorithmControllerObserver(GeneticAlgorithmController geneticAlgorithmController) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ParallelExecutionGui extends JPanel implements GeneticAlgorithmObserver {
	
		private static final long serialVersionUID = 5760354560321457062L;
		
		private JTextField firstFitness;
		private JTextField secondFitness;
		private JTextField thirdFitness;
		private JTextField total;
		private JTextField done;

	
		public ParallelExecutionGui(String title, int numberMigrates, int generations) {
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
			
			JProgressBar migrationsProgressBar = new JProgressBar();
			migrationsProgressBar.setMaximum(generations / numberMigrates);
			migrationsProgressBar.setPreferredSize(new Dimension(1010, 25));
			migrationsProgressBar.setString("Migrations");
			migrationsProgressBar.setStringPainted(true);
			panelProgressBar.add(migrationsProgressBar);

		}

		@Override
		public void updateGeneticAlgorithmObserver(GeneticAlgorithm geneticAlgorithm) {
			// TODO Auto-generated method stub
			
		}

	}


	private class SingleExecutionGui extends JPanel implements GeneticAlgorithmObserver {

		private static final long serialVersionUID = 8635653322025345551L;
		private JTextField firstFitness;
		private JTextField secondFitness;
		private JTextField thirdFitness;
		private JTextField total;
		private JTextField done;
		
	
		public SingleExecutionGui(String title, int generations) {
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
			
			JProgressBar generationsProgressBar = new JProgressBar();
			generationsProgressBar.setMaximum(generations);
			generationsProgressBar.setPreferredSize(new Dimension(1010, 25));
			generationsProgressBar.setString("Generations");
			generationsProgressBar.setStringPainted(true);
			panelProgressBar.add(generationsProgressBar);
			
		}

		@Override
		public void updateGeneticAlgorithmObserver(GeneticAlgorithm geneticAlgorithm) {
			// TODO Auto-generated method stub
			
		}

	}
}
