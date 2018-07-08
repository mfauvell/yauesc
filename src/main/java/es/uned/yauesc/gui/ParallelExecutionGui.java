package es.uned.yauesc.gui;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithm;
import es.uned.yauesc.geneticAlgorithm.GeneticAlgorithmObserver;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class ParallelExecutionGui extends JPanel implements GeneticAlgorithmObserver {
	
	private JTextField firstFitness;
	private JTextField secondFitness;
	private JTextField textField_2;
	private JTextField total;
	private JTextField done;

	/**
	 * Create the panel.
	 */
	public ParallelExecutionGui() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Parallel genetic algorithm execution", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		setPreferredSize(new Dimension(1030, 50));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblBestSolutionFitness = new JLabel("Best solution fitness:");
		add(lblBestSolutionFitness);
		
		JLabel lblFirstFitness = new JLabel("First");
		add(lblFirstFitness);
		
		firstFitness = new JTextField();
		add(firstFitness);
		firstFitness.setColumns(5);
		
		JLabel lblsecondFitness = new JLabel("Second");
		add(lblsecondFitness);
		
		secondFitness = new JTextField();
		add(secondFitness);
		secondFitness.setColumns(5);
		
		JLabel lblthirdFitness = new JLabel("Third");
		add(lblthirdFitness);
		
		textField_2 = new JTextField();
		add(textField_2);
		textField_2.setColumns(5);
		
		JLabel lblMigrations = new JLabel("Migrations:");
		add(lblMigrations);
		
		JLabel lblTotal = new JLabel("Total");
		add(lblTotal);
		
		total = new JTextField();
		add(total);
		total.setColumns(5);
		
		JLabel lblDone = new JLabel("Done");
		add(lblDone);
		
		done = new JTextField();
		add(done);
		done.setColumns(5);

	}

	@Override
	public void updateGeneticAlgorithmObserver(GeneticAlgorithm geneticAlgorithm) {
		// TODO Auto-generated method stub
		
	}

}
