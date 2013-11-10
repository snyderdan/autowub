package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class Main {
	public static void main(String[] args){
		SongRenderer sr = null;
		try {
			sr = new SongRenderer();
//			Note[] soprano = new Note[Song.keys.length];
//			Note[] alto = new Note[Song.keys.length];
//			Note[] tenor = new Note[Song.keys.length];
//			Note[] bass = new Note[Song.keys.length];
//			for(int i=0;i<soprano.length;i++){
//				soprano[i] = new Note(NoteType.EIGTH, Song.keys[i], false, Note.defaultVelocity, Note.defaultOctave);
//				alto[i] = new Note(NoteType.EIGTH, Song.keys[i], false, Note.defaultVelocity, Note.defaultOctave - 1);
//				tenor[i] = new Note(NoteType.QUARTER, Song.keys[i], false, Note.defaultVelocity, Note.defaultOctave -2);
//				bass[i] = new Note(NoteType.QUARTER, Song.keys[i], false, Note.defaultVelocity, Note.defaultOctave -3);
//			}
			
//			Song s = new Song(new NoteTrack[] {
//					new NoteTrack(soprano, 64),
//					new NoteTrack(alto, 80),
//					new NoteTrack(tenor, 93),
//					new NoteTrack(bass, 62),
//				}, 180);
			Song s = new Song();
			s.bpm = 160;
//			System.out.println("Playing, len = " + sr.play(s));
			sr.write(s);
			sr.wait(30);
			while(sr.seqencer.isRunning()){
				System.out.println(sr.seqencer.getMicrosecondPosition() + " / " + sr.seqencer.getMicrosecondLength());
//				sr.seqencer.getMicrosecondPosition();//wts don't know why I need this
			}
			
			
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sr.seqencer.close();
			System.out.println("Done");
		}
	}
}
