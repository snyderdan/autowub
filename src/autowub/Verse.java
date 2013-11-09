package autowub;

import java.util.Random;

public class Verse {
	int numMeasures;
	double numBeats;
	int keyIndex;
	double noteTypes[] = new double[]{.25,.25,.25,.25,.5,.5,1,1,.125,.125,.125,.125,.0625,.0625,.0625};
	String I[] = new String[3];
	String ii[] = new String[3];
	String iii[] = new String[3];
	String IV[] = new String[3];
	String V[] = new String[3];
	String vi[] = new String[3];
	String vii[] = new String[3];
	
	public void play(){
		
	}
	
	public void create(String key, int pKeyIndex){
		keyIndex = pKeyIndex;
		
	}
	
	public void createSoprano(){
		for(int i = numMeasures; i>0; i--){
			double beatsLeft = numBeats;
			while(beatsLeft>0){
				
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
				noteType = noteType/2;
			}
		}
		return noteType;
	}
	
	public Verse(int pNumMeasures, int pNumBeats){
		numMeasures = pNumMeasures;
		numBeats = pNumBeats;
	}
}
