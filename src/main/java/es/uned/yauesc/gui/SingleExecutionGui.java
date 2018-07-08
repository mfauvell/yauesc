package es.uned.yauesc.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;

import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithm;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmObserver;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class SingleExecutionGui extends JPanel implements GeneticAlgorithmObserver {

	private JTextField firstFitness;
	private JTextField secondFitness;
	private JTextField thirdFitness;
	private JTextField total;
	private JTextField done;
	
	/**
	 * Create the panel.
	 */
	public SingleExecutionGui() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Single genetic algorithm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
