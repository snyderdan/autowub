package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class Main {
	public static void main(String[] args){
		try {
			SongRenderer sr = new SongRenderer();
			Note[] testTrack = {
						new Note(NoteType.QUARTER, "c", false, 50),
						new Note(NoteType.QUARTER, "c", false, 50),
						new Note(NoteType.QUARTER, "c", false, 50),
						new Note(NoteType.QUARTER, "c", false, 50),
						new Note(NoteType.QUARTER, "c", false, 50),
						new Note(NoteType.QUARTER, "c", false, 50)
					};
			NoteTrack test = new NoteTrack(testTrack);
			Song s = new Song(new NoteTrack[] {test}, 60, 4);
			System.out.println("Playing, len = " + sr.play(s));
			while(sr.seqencer.isRunning());
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
