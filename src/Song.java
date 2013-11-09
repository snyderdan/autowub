import javax.sound.midi.*;

import java.util.Random;

public class Song {
	Chorus chorus = new Chorus();
	Verse verse = new Verse(16,4);
	Bridge bridge = new Bridge();
	Ending ending = new Ending();
	static String[] keys = new String[]{"c","c#","d","d#","e","f","f#","g","g#","a","a#","b"};
	static String[] natKeys = new String[]{"c","d","e","f","g","a","b"};
	static String[] sharpNum = new String[]{"c","g","d","a","e","b","f#","c#"};
	static String[] flatNum = new String[]{"c","f","a#","d#","g#"};
	static String[]	sharpOrder = new String[]{"f#","c#","g#","d#","a#","e#","b#"};
	static String[]	flatOrder = new String[]{"a#","d#","g#","d#"};
	String key;
	int keyIndex;
	String[][] chords = new String[7][3];
//	String I[] = new String[3];
//	String ii[] = new String[3];
//	String iii[] = new String[3];
//	String IV[] = new String[3];
//	String V[] = new String[3];
//	String vi[] = new String[3];
//	String vii[] = new String[3];
	
	public static void main(String[] args){
		Song wub = new Song();
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
		System.out.println("Key: " + key);
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
		}
		int accCount = -1;
		for(int a = 0; a<sharpNum.length; a++){
			if(key.equals(sharpNum[a])){
				accCount = a;
				for(int b = 0; b<accCount; b++){
					for(int c = 0; c<chords.length; c++){
						for(int d = 0; d<chords[c].length; d++){
							if(chords[c][d].equals(sharpOrder[b].substring(0, 1))){
								chords[c][d] = sharpOrder[b];
							}
						}
					}
				}
				break;
			}
		}
		if(accCount<0){
			for(int e=0; e<flatNum.length; e++){
				if(key.equals(flatNum[e])){
					accCount = e;
					for(int f = 0; f<accCount; f++){
						for(int g = 0; g<chords.length; g++){
							for(int h = 0; h<chords[g].length; h++){
								if(chords[g][h].equals(flatOrder[f].substring(0, 1))){
									chords[g][h] = flatOrder[f];
								}
							}
						}
					}
					break;
				}
			}
		}
		for(int j=0; j<chords.length; j++){
			for(int k=0; k<chords[j].length;k++){
				System.out.print(chords[j][k]);
			}
			System.out.print("\n");
		}

	}
	
	public void pickKey(){
		Random randy = new Random();
		keyIndex = randy.nextInt(12);
		key = keys[keyIndex];
	}
	
	public void play(){
		verse.create(key, keyIndex);
		verse.play();
		chorus.play();
		verse.play();
		chorus.play();
		bridge.play();
		chorus.play();
		ending.play();
	}
}
