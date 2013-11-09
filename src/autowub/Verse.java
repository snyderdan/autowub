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
		createSoprano();
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
		sopranoLine[0] = new Note(trackRhythm.get(0),pickNoteFromChord(Song.chords[0]),false);
		//System.out.println(Song.getKeyIndex("c"));
		for(int i = 1; i<trackRhythm.size(); i++){
			sopranoLine[i] = new Note(trackRhythm.get(i),pickNotePitch(sopranoLine[i-1].pitch,sopFlow.get(i)),false);
		}
		for(int j = 0; j<sopranoLine.length; j++){
			System.out.println(sopranoLine[j].pitch);
		}
	}
	
	public String pickNoteFromChord(String[] chord){
		Random randy = new Random();
		String note = chord[randy.nextInt(chord.length)];
		return note;
	}
	
	public String pickNotePitch(String startingPitch, boolean upFlow){
		Random randy = new Random();
		String pitch = startingPitch;
		//System.out.println(Song.getKeyIndex(pitch));
		String[] noteRange = new String[5];
		if(upFlow){
			for(int i = 0; i<noteRange.length; i++){
				if((Song.chordTones.length>Song.getKeyIndex(pitch)-1+i) && (Song.getKeyIndex(pitch)-1+i>=0)){
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)-1+i];
				}else if((Song.chordTones.length<=Song.getKeyIndex(pitch)-1+i)){
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)-1+i-Song.chordTones.length];
				}else{
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)-1+i+Song.chordTones.length];
				}
			}
		}else{
			for(int i = 0; i<noteRange.length; i++){
				if((Song.chordTones.length>Song.getKeyIndex(pitch)+1-i) && (Song.getKeyIndex(pitch)+1-i>=0)){
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)+1-i];
				}else if((Song.chordTones.length<=Song.getKeyIndex(pitch)+1-i)){
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)+1-i-Song.chordTones.length];
				}else{
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)+1-i+Song.chordTones.length];
				}
			}
		}
		pitch = noteRange[randy.nextInt(noteRange.length)];
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
