package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class NoteTrack {
	final Note[] notes;
	
	public NoteTrack(Note[] notes){
		this.notes = notes;
	}
	
	public void fillMIDITrack(Track t, int channel) throws InvalidMidiDataException{
		long timestamp = 0;
		for(int i =0; i< notes.length; i++){
			t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));
			t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));
			t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));
		}
	}
	
}
