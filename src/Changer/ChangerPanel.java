package Changer;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class ChangerPanel extends JPanel
{
	private SongChanger SChanger;
	private JCheckBox tempo, soprano, alto, tenor, bass;
	private JTextField tempoPercent, sopranoPercent, altoPercent, tenorPercent, bassPercent;
	private boolean tempoChanged, sopranoChanged, altoChanged, tenorChanged, bassChanged = false;
	private JLabel percentage, percentage2, percentage3, percentage4, percentage5;
	private JButton change;
	
	public ChangerPanel(SongChanger SChanger)
	{
		this.SChanger = SChanger;
		change = new JButton("Change!");
		ButtonListener changer = new ButtonListener();
		change.addActionListener(changer);
		percentage = new JLabel("By what percent?");
		percentage2 = new JLabel("By what percent?");
		percentage3 = new JLabel("By what percent?");
		percentage4 = new JLabel("By what percent?");
		percentage5 = new JLabel("By what percent?");
		
		tempo = new JCheckBox("Change tempo?");
		soprano = new JCheckBox("Change soprano?");
		alto = new JCheckBox("Change alto?");
		tenor = new JCheckBox("Change tenor?");
		bass = new JCheckBox("Change bass?");
		CheckBoxListener checkBoxListener = new CheckBoxListener();
		tempo.addItemListener(checkBoxListener);
		soprano.addItemListener(checkBoxListener);
		alto.addItemListener(checkBoxListener);
		tenor.addItemListener(checkBoxListener);
		bass.addItemListener(checkBoxListener);
		
		tempoPercent = new JTextField(3);
		sopranoPercent = new JTextField(3);
		altoPercent = new JTextField(3);
		tenorPercent = new JTextField(3);
		bassPercent = new JTextField(3);
		tempoPercent.setText("0");
		sopranoPercent.setText("0");
		altoPercent.setText("0");
		tenorPercent.setText("0");
		bassPercent.setText("0");
		
		add(tempo);
		add(percentage);
		add(tempoPercent);
		add(soprano);
		add(percentage2);
		add(sopranoPercent);
		add(alto);
		add(percentage3);
		add(altoPercent);
		add(tenor);
		add(percentage4);
		add(tenorPercent);
		add(bass);
		add(percentage5);
		add(bassPercent);
		add(change);
		setPreferredSize(new Dimension(325, 250));
	}
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(tempoChanged)
				SChanger.changeTempo(Integer.parseInt(tempoPercent.getText()) / 100);
			if(sopranoChanged)
				SChanger.changeSoprano(Integer.parseInt(sopranoPercent.getText()) / 100);
			if(altoChanged)
				SChanger.changeAlto(Integer.parseInt(altoPercent.getText()) / 100);
			if(tenorChanged)
				SChanger.changeTenor(Integer.parseInt(tenorPercent.getText()) / 100);
			if(bassChanged)
				SChanger.changeBass(Integer.parseInt(bassPercent.getText()) / 100);
		}
	}
	
	private class CheckBoxListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)
		{
			tempoChanged = tempo.isSelected();
			sopranoChanged = soprano.isSelected();
			altoChanged = alto.isSelected();
			tenorChanged = tenor.isSelected();
			bassChanged = bass.isSelected();
		}
	}
}
