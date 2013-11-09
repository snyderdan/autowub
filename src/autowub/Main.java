package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class Main {
	public static void main(String[] args){
		try {
			SongRenderer sr = new SongRenderer();
			Note[] soprano = new Note[Song.keys.length];
			Note[] alto = new Note[Song.keys.length];
			Note[] tenor = new Note[Song.keys.length];
			Note[] bass = new Note[Song.keys.length];
			for(int i=0;i<soprano.length;i++){
				soprano[i] = new Note(NoteType.EIGTH, Song.keys[i], false, Note.defaultVelocity, Note.defaultOctave);
				alto[i] = new Note(NoteType.EIGTH, Song.keys[i], false, Note.defaultVelocity, Note.defaultOctave - 1);
				tenor[i] = new Note(NoteType.QUARTER, Song.keys[i], false, Note.defaultVelocity, Note.defaultOctave -2);
				bass[i] = new Note(NoteType.QUARTER, Song.keys[i], false, Note.defaultVelocity, Note.defaultOctave -3);
			}
			
			Song s = new Song(new NoteTrack[] {
					new NoteTrack(soprano, 64),
//					new NoteTrack(alto, 80),
//					new NoteTrack(tenor, 93),
//					new NoteTrack(bass, 62),
				}, 180);
			System.out.println("Playing, len = " + sr.play(s));
			while(sr.seqencer.isRunning()){
				sr.seqencer.getMicrosecondPosition();//wts don't know why I need this
			}
			sr.write();
			sr.seqencer.close();
			System.out.println("Done");
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
