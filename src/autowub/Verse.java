package autowub;

import java.util.Random;
import java.util.ArrayList;

public class Verse {
	int numMeasures;
	double numBeats;
	int keyIndex;
	double noteTypes[] = new double[]{.25,.25,.25,.25,.5,.5,1,1,.125,.125,.125,.125,.0625,.0625,.0625};
	ArrayList<Double> trackRhythm = new ArrayList<Double>();
	ArrayList<Double> percRhythm = new ArrayList<Double>();
	ArrayList<Boolean> sopFlow = new ArrayList<Boolean>();
	ArrayList<Boolean> bassFlow = new ArrayList<Boolean>();
	boolean trueOrFalse[] = new boolean[]{true,false};
	String[][] chordProg;
	Note sopranoLine[];
	Note bassLine[];
	Note altoLine[];
	Note tenorLine[];
	String[] I;
	String[] ii;
	String[] iii;
	String[] IV;
	String[] V;
	String[] vi;
	String[] vii;
	String leadingTone;
	
	public void play(){
		
	}
	
	public void create(String key, int pKeyIndex){
		keyIndex = pKeyIndex;
		createRhythm();
		createSoprano();
		createChords();
		createBass();
		createPercussion();
		for(int i = 0; i < trackRhythm.size(); i++){
			System.out.println("Chord: " + chordProg[i][0] + chordProg[i][1] + chordProg[i][2] + " Soprano: " + sopranoLine[i].pitch + " Bass: " + bassLine[i].pitch);
		}
	}
	
	public void createRhythm(){
		fillTriads();
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
				bassFlow.add(!upFlow);
				System.out.println(noteDur);
				beatsLeft = beatsLeft - noteDur;
			}
		}
	}
	
	public void createPercussion(){
		Random randy = new Random();
		for(int i = 0; i<numMeasures; i++){
			if(randy.nextBoolean()){
				for(int j = 0; j<8; j++){
					percRhythm.add(.125);
				}
			}else{
				for(int k = 0; k<4; k++){
					percRhythm.add(.25);
				}
			}
		}
	}
	 
	public void createChords(){
		chordProg = new String[trackRhythm.size()][3];
		chordProg[0] = I;
		for(int i = 1; i<chordProg.length; i++){
			chordProg[i] = pickChord(chordProg[i-1],sopranoLine[i].pitch);
//			chordProg[i] = pickChord(chordProg[i-1], chordWithRoot(Song.chords[Song.getKeyIndex(sopranoLine[i].pitch)][0]),
//					chordWithRoot(Song.chords[Song.getKeyIndex(sopranoLine[i].pitch)][1]),
//					chordWithRoot(Song.chords[Song.getKeyIndex(sopranoLine[i].pitch)][2])); //Song.chords[Song.getKeyIndex(sopranoLine[i].pitch)];
		}
//		for(int i = 0; i<chordProg.length; i++){
//			
//		}
	}
	
	public String[] pickChord(String[] lastChord, String root){
		String[] optionOne = I;
		String[] optionTwo = I;
		String[] optionThree = I;
		for(int i = 0; i<Song.chords.length; i++){
			if(root.equals(Song.chords[i][0])){
				optionOne = Song.chords[i];
			}else if(root.equals(Song.chords[i][1])){
				optionTwo = Song.chords[i];
			}else if(root.equals(Song.chords[i][2])){
				optionThree = Song.chords[i];
			}
		}
		System.out.println("last chord: " + lastChord[0] + lastChord[1] + lastChord[2]);
		System.out.println("root note: " + root);
		System.out.println("optionOne chord: " + optionOne[0] + optionOne[1] + optionOne[2]);
		System.out.println("optionTwo chord: " + optionTwo[0] + optionTwo[1] + optionTwo[2]);
		System.out.println("optionThree chord: " + optionThree[0] + optionThree[1] + optionThree[2]);
		ArrayList<String[]> chords = new ArrayList<String[]>();
		String chord[] = I;
		Random randy = new Random();
		if(lastChord.equals(I)){
			chord = Song.chords[randy.nextInt(Song.chords.length)];
			return chord;
		}
		if(lastChord.equals(ii)){
			if(optionOne.equals(V) || optionTwo.equals(V) || optionThree.equals(V)){
				chords.add(V);
			}
			if(optionOne.equals(vii) || optionTwo.equals(vii) || optionThree.equals(vii)){
				chords.add(vii);
			}
		}
		if(lastChord.equals(iii)){
			if(optionOne.equals(ii) || optionTwo.equals(ii) || optionThree.equals(ii)){
				chords.add(ii);
			}
			if(optionOne.equals(IV) || optionTwo.equals(IV) || optionThree.equals(IV)){
				chords.add(IV);
			}
			if(optionOne.equals(V) || optionTwo.equals(V) || optionThree.equals(V)){
				chords.add(V);
			}
			if(optionOne.equals(vi) || optionTwo.equals(vi) || optionThree.equals(vi)){
				chords.add(vi);
			}
		}
		if(lastChord.equals(IV)){
			if(optionOne.equals(I) || optionTwo.equals(I) || optionThree.equals(I)){
				chords.add(I);
			}
			if(optionOne.equals(ii) || optionTwo.equals(ii) || optionThree.equals(ii)){
				chords.add(V);
			}
			if(optionOne.equals(V) || optionTwo.equals(V) || optionThree.equals(V)){
				chords.add(V);
			}
			if(optionOne.equals(vii) || optionTwo.equals(vii) || optionThree.equals(vii)){
				chords.add(vii);
			}
		}
		if(lastChord.equals(V)){
			if(optionOne.equals(I) || optionTwo.equals(I) || optionThree.equals(I)){
				chords.add(I);
			}
			if(optionOne.equals(vi) || optionTwo.equals(vi) || optionThree.equals(vi)){
				chords.add(V);
			}
		}
		if(lastChord.equals(vi)){
			if(optionOne.equals(ii) || optionTwo.equals(ii) || optionThree.equals(ii)){
				chords.add(ii);
			}
			if(optionOne.equals(IV) || optionTwo.equals(IV) || optionThree.equals(IV)){
				chords.add(IV);
			}
			if(optionOne.equals(V) || optionTwo.equals(V) || optionThree.equals(V)){
				chords.add(V);
			}
		}
		if(lastChord.equals(vii)){
			return I;
		}
		System.out.println(chords.size());
		if(chords.size()==0){
			return optionOne;
		}
		chord = chords.get(randy.nextInt(chords.size()));
		return chord;
	}
	
//	public String[] pickChord(String[] lastChord, String[] one, String[] two, String[] three){
//		System.out.println("last chord: " + lastChord[0] + lastChord[1] + lastChord[2]);
//		System.out.println("one chord: " + one[0] + one[1] + one[2]);
//		System.out.println("two chord: " + two[0] + two[1] + two[2]);
//		System.out.println("three chord: " + three[0] + three[1] + three[2]);
//		ArrayList<String[]> chords = new ArrayList<String[]>();
//		String chord[] = I;
//		Random randy = new Random();
//		if(lastChord.equals(I)){
//			chord = Song.chords[randy.nextInt(Song.chords.length)];
//			return chord;
//		}
//		if(lastChord.equals(ii)){
//			if(one.equals(V) || two.equals(V) || three.equals(V)){
//				chords.add(V);
//			}
//			if(one.equals(vii) || two.equals(vii) || three.equals(vii)){
//				chords.add(vii);
//			}
//		}
//		if(lastChord.equals(iii)){
//			if(one.equals(ii) || two.equals(ii) || three.equals(ii)){
//				chords.add(ii);
//			}
//			if(one.equals(IV) || two.equals(IV) || three.equals(IV)){
//				chords.add(IV);
//			}
//			if(one.equals(V) || two.equals(V) || three.equals(V)){
//				chords.add(V);
//			}
//			if(one.equals(vi) || two.equals(vi) || three.equals(vi)){
//				chords.add(vi);
//			}
//		}
//		if(lastChord.equals(IV)){
//			if(one.equals(I) || two.equals(I) || three.equals(I)){
//				chords.add(I);
//			}
//			if(one.equals(ii) || two.equals(ii) || three.equals(ii)){
//				chords.add(V);
//			}
//			if(one.equals(V) || two.equals(V) || three.equals(V)){
//				chords.add(V);
//			}
//			if(one.equals(vii) || two.equals(vii) || three.equals(vii)){
//				chords.add(vii);
//			}
//		}
//		if(lastChord.equals(V)){
//			if(one.equals(I) || two.equals(I) || three.equals(I)){
//				chords.add(I);
//			}
//			if(one.equals(vi) || two.equals(vi) || three.equals(vi)){
//				chords.add(V);
//			}
//		}
//		if(lastChord.equals(vi)){
//			if(one.equals(ii) || two.equals(ii) || three.equals(ii)){
//				chords.add(ii);
//			}
//			if(one.equals(IV) || two.equals(IV) || three.equals(IV)){
//				chords.add(IV);
//			}
//			if(one.equals(V) || two.equals(V) || three.equals(V)){
//				chords.add(V);
//			}
//		}
//		if(lastChord.equals(vii)){
//			return I;
//		}
//		System.out.println(chords.size());
//		if(chords.size()==0){
//			return I;
//		}
//		chord = chords.get(randy.nextInt(chords.size()));
//		return chord;
//	}
	
	public void createSoprano(){
		sopranoLine = new Note[trackRhythm.size()];
		sopranoLine[0] = new Note(trackRhythm.get(0),pickNoteFromChord(I),false);
		sopranoLine[sopranoLine.length-1] = new Note(trackRhythm.get(sopranoLine.length-1),pickNoteFromChord(I),false);
		sopranoLine[sopranoLine.length-2] = new Note(trackRhythm.get(sopranoLine.length-2),pickNoteFromChord(V),false);
		//System.out.println(Song.getKeyIndex("c"));
		for(int i = 1; i<trackRhythm.size()-2; i++){
			if(sopranoLine[i-1].pitch.equals(leadingTone)){
				sopranoLine[i] = new Note(trackRhythm.get(i),I[0],false);
			}else{
				sopranoLine[i] = new Note(trackRhythm.get(i),pickNotePitchSop(sopranoLine[i-1].pitch,sopFlow.get(i)),false);
			}
		}
		for(int j = 0; j<sopranoLine.length; j++){
			System.out.println(sopranoLine[j].pitch);
		}
	}
	
	public void createAltoAndTenor(){
		String[] notesUsed = new String[2];
		altoLine = new Note[trackRhythm.size()];
		altoLine[0] = new Note(trackRhythm.get(0),pickNoteFromChord(I),false);
		tenorLine = new Note[trackRhythm.size()];
		tenorLine[0] = new Note(trackRhythm.get(0),pickNoteFromChord(I),false);
	}
	
	public void createBass(){
		bassLine = new Note[trackRhythm.size()];
		bassLine[0] = new Note(trackRhythm.get(0),pickNoteFromChord(I),false,Note.defaultVelocity,Note.defaultOctave-3);
		bassLine[bassLine.length-1] = new Note(trackRhythm.get(bassLine.length-1),pickNoteFromChord(I),false,Note.defaultVelocity,Note.defaultOctave-3);
		bassLine[bassLine.length-2] = new Note(trackRhythm.get(bassLine.length-2),pickNoteFromChord(V),false,Note.defaultVelocity,Note.defaultOctave-3);
		for(int i = 1; i<trackRhythm.size()-2; i++){
			//bassLine[i] = new Note(trackRhythm.get(i),pickNoteFromChord(chordProg[i]),false,100,Note.defaultOctave-3);
			if(bassLine[i-1].pitch.equals(leadingTone)){
				bassLine[i] = new Note(trackRhythm.get(i),I[0],false);
			}else{
				bassLine[i] = new Note(trackRhythm.get(i),chordProg[i][0],false,100,Note.defaultOctave-3);
			}
		}
//		for(int i = 1; i<trackRhythm.size(); i++){
//			bassLine[i] = new Note(trackRhythm.get(i),pickNotePitchBass(bassLine[i-1].pitch,bassFlow.get(i)),false);
//		}
	}
	
	public String pickNoteFromChord(String[] chord){
		Random randy = new Random();
		String note = chord[randy.nextInt(chord.length)];
		return note;
	}
	
	public String pickNoteFromChord(String[] chord,String[] notesUsed){
		Random randy = new Random();
		String note = chord[randy.nextInt(chord.length)];
		return note;
	}
	
	public String pickNotePitchBass(String startingPitch, boolean upFlow){
		Random randy = new Random();
		String pitch = startingPitch;
		//System.out.println(Song.getKeyIndex(pitch));
		String[] noteRange = new String[7];
		if(upFlow){
			for(int i = 0; i<noteRange.length; i++){
				if((Song.chordTones.length>Song.getKeyIndex(pitch)-2+i) && (Song.getKeyIndex(pitch)-2+i>=0)){
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)-2+i];
				}else if((Song.chordTones.length<=Song.getKeyIndex(pitch)-2+i)){
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)-2+i-Song.chordTones.length];
				}else{
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)-2+i+Song.chordTones.length];
				}
			}
		}else{
			for(int i = 0; i<noteRange.length; i++){
				if((Song.chordTones.length>Song.getKeyIndex(pitch)+2-i) && (Song.getKeyIndex(pitch)+2-i>=0)){
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)+2-i];
				}else if((Song.chordTones.length<=Song.getKeyIndex(pitch)+2-i)){
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)+2-i-Song.chordTones.length];
				}else{
					noteRange[i] = Song.chordTones[Song.getKeyIndex(pitch)+2-i+Song.chordTones.length];
				}
			}
		}
		pitch = noteRange[randy.nextInt(noteRange.length)];
		return pitch;
	}
	
	public String pickNotePitchSop(String startingPitch, boolean upFlow){
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
	
	public String[] chordWithRoot(String root){
		for(int i = 0; i<Song.chords.length; i++){
			if(root.equals(Song.chords[i][0])){
				return Song.chords[i];
			}
		}
		return I;
	}
	
	public void fillTriads(){
		I = Song.chords[0];
		ii = Song.chords[1];
		iii = Song.chords[2];
		IV = Song.chords[3];
		V = Song.chords[4];
		vi = Song.chords[5];
		vii = Song.chords[6];
		leadingTone = vii[0];
	}
	
	public Verse(int pNumMeasures, int pNumBeats){
		numMeasures = pNumMeasures;
		numBeats = pNumBeats;
	}
}
