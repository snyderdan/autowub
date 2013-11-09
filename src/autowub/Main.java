package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class Main {
	public static void main(String[] args){
		try {
			SongRenderer sr = new SongRenderer();
//			sr.play(new Song());
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
