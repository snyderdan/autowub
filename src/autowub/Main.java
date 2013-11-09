package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class Main {
	public static void main(String[] args){
		try {
			SongRenderer sr = new SongRenderer();
			Note[] testTrack = {
						new Note(NoteType.QUARTER, "c", false, 90),
						new Note(NoteType.QUARTER, "d", false, 90),
						new Note(NoteType.QUARTER, "e", false, 90),
						new Note(NoteType.QUARTER, "f", false, 90),
						new Note(NoteType.QUARTER, "g", false, 90),
						new Note(NoteType.QUARTER, "a", false, 90),
						new Note(NoteType.QUARTER, "b", false, 90),
						new Note(NoteType.QUARTER, "c", false, 90)
					};
			NoteTrack test = new NoteTrack(testTrack);
			Song s = new Song(new NoteTrack[] {test}, 120, 4);
			System.out.println("Playing, len = " + sr.play(s));
			while(sr.seqencer.isRunning()){
				sr.seqencer.getMicrosecondPosition();
			}
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
