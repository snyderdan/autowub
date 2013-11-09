package autowub;

import java.util.Random;

public class Verse {
	int numMeasures;
	double numBeats;
	int keyIndex;
	double noteTypes[] = new double[]{.25,.25,.25,.25,.5,.5,1,1,.125,.125,.125,.125,.0625,.0625,.0625};
	
	public void play(){
		
	}
	
	public void create(String key, int pKeyIndex){
		keyIndex = pKeyIndex;
		createSoprano();
	}
	
	public void createSoprano(){
		for(int i = numMeasures; i>0; i--){
			double beatsLeft = numBeats;
			while(beatsLeft>0){
				double noteDur = pickNoteDur(beatsLeft);
				System.out.println(noteDur);
				beatsLeft -= noteDur;
			}
		}
	}
	
	public double pickNoteDur(double beatsLeft){
		double noteType;
		Random randy = new Random();
		noteType = noteTypes[randy.nextInt(noteTypes.length)];
		while(true){
			if(noteType<beatsLeft){
				break;
			}else{
				noteType = noteTypes[randy.nextInt(noteTypes.length)];
			}
		}
		return noteType;
	}
	
	public Verse(int pNumMeasures, int pNumBeats){
		numMeasures = pNumMeasures;
		numBeats = pNumBeats;
	}
}
