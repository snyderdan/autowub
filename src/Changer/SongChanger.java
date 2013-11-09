package Changer;

import java.util.Scanner;

import javax.swing.JFrame;

import autowub.Song;

public class SongChanger 
{
	Scanner scan = new Scanner(System.in);
	double tempo, bass, alto, soprano, tenor;
	double[] values = {tempo, soprano, alto, tenor, bass};
	String[] songParts = {"tempo", "soprano", "alto", "tenor", "bass"};
	
	public void recieveSong(Song song)
	{
		values[0] = (double)song.bpm;
		values[1] = song.soprano;
		values[2] = song.alto;
		values[3] = song.tenor;
		values[4] = song.bass;
	}
	
	public void changeSong(boolean useGui)
	{
		if(useGui)
		{
			//using a GUI
			JFrame frame = new JFrame("Song Changer");
			ChangerPanel panel = new ChangerPanel(this);
			frame.getContentPane().add(panel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}
		else
		{
			//Using the terminal
			boolean responded = false;
			while(!responded)
			{
				System.out.print("Does this song need to be changed? (Y/N): ");
				String response = scan.next();
				if(!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N"))
				{
					System.out.print("Invalid response");
				}
				else
				{
					responded = true;
					if(response.equalsIgnoreCase("N"))
					{
						return;
					}
				}
			}
			for(int a = 0; a < values.length; a++)
			{
				boolean respondar = false;
				String theResponse = "N";
				while(!respondar)
				{
					System.out.print("Does the " + songParts[a] + " of this song need to be changed? (Y/N): ");
					theResponse = scan.next();
					if(!theResponse.equalsIgnoreCase("Y") && !theResponse.equalsIgnoreCase("N"))
					{
						System.out.print("Invalid response");
					}
					else
					{
						respondar = true;
					}
				}
				if(theResponse.equalsIgnoreCase("Y"))
				{
					boolean directionResponded = false;
					int direction = 0;
					while(!directionResponded)
					{
						System.out.print("Increase or decrease? (I/D): ");
						String directionresponse = scan.next();
						if(!directionresponse.equalsIgnoreCase("I") && !directionresponse.equalsIgnoreCase("D"))
						{
							System.out.print("Invalid response");
						}
						else
						{
							responded = true;
							if(directionresponse.equalsIgnoreCase("I"))
							{
								direction = 1;
							}
							else
							{
								direction = -1;
							}
						}
					}
					boolean percentResponded = false;
					while(!percentResponded)
					{
						System.out.print("By what percent?");
						double change = (double)scan.nextInt() / 100 * direction;
						if((1 + change) < 0)
						{
							System.out.print("Invalid percent");
						}
						else
						{
							percentResponded = true;
							double finalChange = 1 + change;
							values[a] = values[a] * finalChange;
						}
					}
				}
			}
		}
	}
	
	public void changeTempo(double change)
	{
		values[0] = values[0] * change;
	}
	
	public void changeSoprano(double change)
	{
		values[1] = values[1] * change;
	}
	
	public void changeAlto(double change)
	{
		values[2] = values[2] * change;
	}
	
	public void changeTenor(double change)
	{
		values[3] = values[3] * change;
	}
	
	public void changeBass(double change)
	{
		values[4] = values[4] * change;
	}
	
	public void createNewSong(Song song)
	{
		song.bpm = (int) values[0];
		song.soprano = values[1];
		song.alto = values[2];
		song.tenor = values[3];
		song.bass = values[4];
	}
	
//	public Song leapMotionControl(Track track)
//	{
//		return null;
//	}
}
