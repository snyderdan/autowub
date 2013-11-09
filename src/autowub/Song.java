package generation;

import javax.sound.midi.*;

import autowub.NoteTrack;

import java.util.Random;

public class Song {
	Chorus chorus = new Chorus();
	Verse verse = new Verse(16,4);
	Bridge bridge = new Bridge();
	Ending ending = new Ending();
	public static String[] keys = new String[]{"c","c#","d","d#","e","f","f#","g","g#","a","a#","b"};
	static String[] natKeys = new String[]{"c","d","e","f","g","a","b"};
	static String[] sharpOrder = new String[]{"c","g","d","a","e","b","f#","c#"};
	static String[] flatOrder = new String[]{"f","a#","d#","g#"};
	String key;
	int keyIndex;
	String[][] chords = new String[7][3];
	String I[] = new String[3];
	String ii[] = new String[3];
	String iii[] = new String[3];
	String IV[] = new String[3];
	String V[] = new String[3];
	String vi[] = new String[3];
	String vii[] = new String[3];
	
	NoteTrack[] tracks = new NoteTrack[4];
	public int bpm;
	int beatNote;
	
	Song(NoteTrack[] tracks, int bpm, int beatNote){
		this.tracks = tracks;
		this.bpm = bpm;
		this.beatNote = beatNote;
	}
	
	Sequence getSequence() {
		Sequence s = null;
		try {
			s = new Sequence(Sequence.PPQ, 4);
			Track t = s.createTrack();
			for (int i = 0; i < tracks.length; i++) {
				tracks[i].fillMIDITrack(t, i);
			}
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	
	public static void main(String[] args){
		Song wub = new Song();
//		int[] notes;
//		int note = 72;
//		try{
//			Synthesizer synth = MidiSystem.getSynthesizer();
//			synth.open();
//			MidiChannel channel = synth.getChannels()[0];
//			
//			channel.noteOn(note, 50);
//			channel.noteOn(76, 50);
//			channel.noteOn(79, 50);
//		} catch(MidiUnavailableException e){
//			e.printStackTrace();
//		}
		wub.play();
	}
	
	public Song(){
		pickKey();
		System.out.println(key);
		int natIndex = 0;
		//System.out.println(keys[keyIndex].substring(0, 1));
		for(int i = 0; i<natKeys.length; i++){
			if(natKeys[i].equals(keys[keyIndex].substring(0, 1))){
				natIndex = i;
				break;
			}
		}
		for(int j=0; j<chords.length; j++){
			if(natIndex+j<natKeys.length){
				chords[j][0] = natKeys[natIndex+j];
			}else{
				chords[j][0] = natKeys[natIndex+j-natKeys.length];
			}
			//chords[j][0] = natKeys[natIndex+j];
			if(natIndex+2+j<natKeys.length){
				chords[j][1] = natKeys[natIndex+2+j];
			}else if(natIndex+2+j<natKeys.length*2){
				chords[j][1] = natKeys[natIndex+2+j-natKeys.length];
			}else{
				chords[j][1] = natKeys[natIndex+j-natKeys.length];
			}
			if(natIndex+4+j<natKeys.length){
				chords[j][2] = natKeys[natIndex+4+j];
			}else if(natIndex+4+j<natKeys.length*2){
				chords[j][2] = natKeys[natIndex+4+j-natKeys.length];
			}else{
				chords[j][2] = natKeys[natIndex+4+j-(natKeys.length*2)];
			}
			for(int k=0; k<chords[j].length;k++){
				System.out.print(chords[j][k]);
			}
			System.out.print("\n");
		}
//		for(int i=0; i<chords.length; i++){
//			if(keyIndex+i<keys.length){
//				chords[i][0] = keys[keyIndex+i];
//			}else{
//				
//			}
//			chords[i][1] = keys[keyIndex+4+i];
//			chords[i][2] = keys[keyIndex+7+i];
////			for(int j=0; j<chords[i].length; j++){
////				chords[i][j] =  
////			}
//		}
	}
	
	public void pickKey(){
		Random randy = new Random();
		keyIndex = randy.nextInt(12);
		key = keys[keyIndex];
	}
	
	public void play(){
		verse.play();
		chorus.play();
		verse.play();
		chorus.play();
		bridge.play();
		chorus.play();
		ending.play();
	}
}
