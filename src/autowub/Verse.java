package autowub;

import java.util.Random;
import java.util.ArrayList;

public class Verse {
	int numMeasures;
	double numBeats;
	int keyIndex;
	double noteTypes[] = new double[]{.25,.25,.25,.25,.5,.5,1,1,.125,.125,.125,.125,.0625,.0625,.0625};
	ArrayList<Double> trackRhythm = new ArrayList<Double>();
	ArrayList<Boolean> sopFlow = new ArrayList<Boolean>();
	boolean trueOrFalse[] = new boolean[]{true,false};
	Note sopranoLine[];
	Note bassLine[];
	Note altoLine[];
	Note tenorLine[];
	
	public void play(){
		
	}
	
	public void create(String key, int pKeyIndex){
		keyIndex = pKeyIndex;
		createRhythm();
	}
	
	public void createRhythm(){
		boolean upFlow = true;
		Random randy = new Random();
		for(int i = 0; i<numMeasures; i++){
			System.out.println("Measure: " + i);
			upFlow = trueOrFalse[randy.nextInt(2)];
			System.out.println(upFlow);
			double beatsLeft = numBeats;
			while(beatsLeft>0){
				double noteDur = pickNoteDur(beatsLeft);
				trackRhythm.add(noteDur);
				sopFlow.add(upFlow);
				System.out.println(noteDur);
				beatsLeft = beatsLeft - noteDur;
			}
		}
	}
	
	public void createSoprano(){
		sopranoLine = new Note[trackRhythm.size()];
		sopranoLine[0] = new Note(trackRhythm.get(0),pickNoteFromChord(Song.chords[0]),false,90);
		for(int i = 1; i<trackRhythm.size(); i++){
			
		}
	}
	
	public String pickNoteFromChord(String[] chord){
		Random randy = new Random();
		String note = chord[randy.nextInt(chord.length)];
		return note;
	}
	
	public String pickNotePitch(String startingPitch, boolean upFlow){
		String pitch = startingPitch;
		return pitch;
	}
	
	public double pickNoteDur(double beatsLeft){
		double noteType;
		Random randy = new Random();
		noteType = noteTypes[randy.nextInt(noteTypes.length)];
		while(true){
			if(noteType<=beatsLeft){
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
