package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

public class Song {
	NoteTrack[] tracks = new NoteTrack[4];
	int bpm;
	int beatNote;

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
}
