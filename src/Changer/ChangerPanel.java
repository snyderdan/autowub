package Changer;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class ChangerPanel extends JPanel
{
	private SongChanger SChanger;
	private JCheckBox tempo, bass;
	private JTextField tempoPercent, bassPercent;
	private boolean tempoChanged, bassChanged = false;
	private JLabel percentage, percentage2;
	private JButton change;
	private JRadioButton upOctave, stayOctave, downOctave;
	private JRadioButton instrument1, instrument2, instrument3;
	private int[] inst = {1, 1, 1};
	private int octaveChange = 0;
	
	public ChangerPanel(SongChanger SChanger)
	{
		this.SChanger = SChanger;
		change = new JButton("Change!");
		ButtonListener changer = new ButtonListener();
		change.addActionListener(changer);
		percentage = new JLabel("By what percent?");
		percentage2 = new JLabel("By what percent?");
		
		tempo = new JCheckBox("Change tempo?");
		bass = new JCheckBox("Change bass?");
		CheckBoxListener checkBoxListener = new CheckBoxListener();
		tempo.addItemListener(checkBoxListener);
		bass.addItemListener(checkBoxListener);
		tempoPercent = new JTextField(3);
		bassPercent = new JTextField(3);
		tempoPercent.setText("0");
		bassPercent.setText("0");
		
		upOctave = new JRadioButton("+ Octave", false);
		stayOctave = new JRadioButton("stay", true);
		downOctave = new JRadioButton("- Octave", false);
		OctaveListener octList = new OctaveListener();
		upOctave.addActionListener(octList);
		stayOctave.addActionListener(octList);
		downOctave.addActionListener(octList);
		ButtonGroup octaves = new ButtonGroup();
		octaves.add(upOctave);
		octaves.add(stayOctave);
		octaves.add(downOctave);
		
		JPanel octavePanel = new JPanel();
		octavePanel.setPreferredSize(new Dimension(100, 150));
		octavePanel.setBorder(BorderFactory.createEtchedBorder());
		octavePanel.add(upOctave);
		octavePanel.add(stayOctave);
		octavePanel.add(downOctave);
		
		instrument1 = new JRadioButton("Combo #1", true);
		instrument2 = new JRadioButton("Combo #2", false);
		instrument3 = new JRadioButton("Combo #3", false);
		InstrumentListener instList = new InstrumentListener();
		instrument1.addActionListener(instList);
		instrument2.addActionListener(instList);
		instrument3.addActionListener(instList);
		ButtonGroup instruments = new ButtonGroup();
		instruments.add(instrument1);
		instruments.add(instrument2);
		instruments.add(instrument3);
		
		JPanel instPanel = new JPanel();
		instPanel.setPreferredSize(new Dimension(100, 150));
		instPanel.setBorder(BorderFactory.createEtchedBorder());
		instPanel.add(instrument1);
		instPanel.add(instrument2);
		instPanel.add(instrument3);
		
		add(tempo);
		add(percentage);
		add(tempoPercent);
		add(bass);
		add(percentage2);
		add(bassPercent);
		add(octavePanel);
		add(instPanel);
		add(change);
		setPreferredSize(new Dimension(325, 250));
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(tempoChanged)
				SChanger.changeTempo(Integer.parseInt(tempoPercent.getText()) / 100);
			if(bassChanged)
				SChanger.changeBass(Integer.parseInt(bassPercent.getText()) / 100);
			SChanger.changeOctave(octaveChange);
			SChanger.changeInstument(inst);
			
			ChangerPanel.this.setVisible(false);
		}
	}
	
	private class CheckBoxListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)
		{
			tempoChanged = tempo.isSelected();
			bassChanged = bass.isSelected();
		}
	}
	
	private class OctaveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source == upOctave)
				octaveChange = 1;
			else
			{
				if(source == downOctave)
					octaveChange = -1;
				else
					octaveChange = 0;
			}
		}
	}
	
	private class InstrumentListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source == instrument1)
				inst[0] = 1; //change to instrument number array
			else
			{
				if(source == instrument2)
					inst[0] = 2;//change to instrument number array
				else
					inst[0] = 3;//change to instrument number array
			}
		}
	}
}
