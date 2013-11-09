package autowub;

import javax.sound.midi.*;

import autowub.NoteTrack;

import java.util.Random;

public class Song {
	Chorus chorus = new Chorus();
	Verse verse = new Verse(16,4);
	Bridge bridge = new Bridge();
	Ending ending = new Ending();
	static String[][] natChords = new String[][]{{"c","e","g"},{"d","f","a"},{"e","g","b"},{"f","a","c"},{"g","b","d"},{"a","c","e"},{"b","d","f"}};
	static String[] keys = new String[]{"c","c#","d","d#","e","f","f#","g","g#","a","a#","b"};
	static String[] natKeys = new String[]{"c","d","e","f","g","a","b"};
	static String[] sharpNum = new String[]{"c","g","d","a","e","b","f#","c#"};
	static String[] flatNum = new String[]{"c","f","a#","d#","g#"};
	static String[]	sharpOrder = new String[]{"f#","c#","g#","d#","a#","e#","b#"};
	//static String[]	flatOrder = new String[]{"a#","d#","g#","c#"};
	static String[]	flatOrder = new String[]{"b","e","a","d"};
	static String[] chordTones = new String[7];
	String key;
	int keyIndex;
	static String[][] chords = new String[7][3];
//	String I[] = new String[3];
//	String ii[] = new String[3];
//	String iii[] = new String[3];
//	String IV[] = new String[3];
//	String V[] = new String[3];
//	String vi[] = new String[3];
//	String vii[] = new String[3];
	
	NoteTrack[] tracks;
	public int bpm;

	public double bass, tenor, alto, soprano;
	
	Song(NoteTrack[] tracks, int bpm){
	//arbitrary doubles to change song elements (to be given meaning soon), will probably change
	
	
		this.tracks = tracks;
		this.bpm = bpm;
	}

	Sequence getSequence() {
		Sequence s = null;
		try {
			s = new Sequence(Sequence.PPQ, 8);
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
		wub.play();
	}
	
	public Song(){
		pickKey();
		System.out.println("Key: " + key);
		boolean sharps = false;
		//assigns index in natural array
		int natIndex = 0;
		for(int i = 0; i<natKeys.length; i++){
			if(natKeys[i].equals(keys[keyIndex].substring(0, 1))){
				natIndex = i;
				break;
			}
		}
		int sharpCount = 0;
		//check if sharps or flats
		for(int i = 0; i<sharpNum.length; i++){
			if(key.equals(sharpNum[i])){
				sharps = true;
				sharpCount = i;
				break;
			}
		}
		//assigns sharps where necessary
		if(sharps){
			for(int j=0; j<chords.length; j++){
				if(natIndex+j<natChords.length){
					chords[j] = natChords[natIndex+j];
				}else{
					chords[j] = natChords[natIndex+j-natChords.length];
				}
				for(int k=0; k<chords[j].length; k++){
					for(int a=0; a<sharpCount; a++){
						//System.out.println(chords[j][k] + "  " + sharpNum[a]);
						if(chords[j][k].equals(sharpOrder[a].substring(0, 1))){
							chords[j][k] = sharpOrder[a];
						}
					}
				}
			}
		}else{
			int flatCount = 0;
			for(int i=0; i<flatNum.length; i++){
				if(key.equals(flatNum[i])&&key.contains("#")){
					flatCount = i;
					break;
				}else if(key.equals(flatNum[i])&&(!key.contains("#"))){
					flatCount = i;
					natIndex--;
					break;
				}
			}
			for(int j=0; j<chords.length; j++){
				if(natIndex+1+j<natChords.length){
					chords[j] = natChords[natIndex+1+j];
				}else{
					chords[j] = natChords[natIndex+1+j-natChords.length];
				}
				for(int k=0; k<chords[j].length; k++){
					for(int a=0; a<flatCount; a++){
						if(chords[j][k].equals(flatOrder[a])){
							int index = 0;
							for(int b=0; b<keys.length; b++){
								if(flatOrder[a].equals(keys[b])){
									index = b;
									break;
								}
							}
							chords[j][k] = keys[index-1];
						}
					}
				}
			}
		}
		for(int i=0; i<chordTones.length; i++){
			chordTones[i] = chords[i][0];
		}
		for(int i=0; i<chords.length; i++){
			for(int j=0; j<chords[i].length; j++){
				System.out.print(chords[i][j]);
			}
			System.out.print("\n");
		}
		verse.create(key, keyIndex);
		tracks = new NoteTrack[1];
		tracks[0] = new NoteTrack(verse.sopranoLine, 33);
	}
	
	public static int getKeyIndex(String note){
		int index = -1;
		for(int i = 0; i<chordTones.length; i++){
			if(note.equals(chordTones[i])){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static int getNatIndex(String note){
		int index = -1;
		for(int i = 0; i<natKeys.length; i++){
			if(note.equals(natKeys[i])){
				index = i;
				break;
			}
		}
		return index;
	}
	
	public void pickKey(){
		Random randy = new Random();
		keyIndex = randy.nextInt(keys.length);
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
