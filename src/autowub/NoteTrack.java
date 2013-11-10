package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.Patch;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class NoteTrack {
	final Note[] notes;
	final int instrument;
	
	public NoteTrack(Note[] notes, int instrument){
		this.notes = notes;
		this.instrument = instrument;
	}
	
	
	public void fillMIDITrack(Track t, int channel, int bpm) throws InvalidMidiDataException{
		long timestamp = 0;
		t.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0), 0));
		for(int i =0; i< notes.length; i++){
			if(notes[i].pitch == null){
				timestamp += notes[i].noteLength();
			}else if(notes[i].isperc){
				t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));
				t.add(new MidiEvent(new ShortMessage(ShortMessage.POLY_PRESSURE, channel, notes[i].asMidi(), 100 ), timestamp+2));
//				int intensity = 100
				timestamp +=  notes[i].noteLength();
				t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));

			}else{
				t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));
				t.add(new MidiEvent(new ShortMessage(ShortMessage.POLY_PRESSURE, channel, notes[i].asMidi(), 80 ), timestamp+2));
				timestamp += notes[i].noteLength();
				t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));
				t.add(new MidiEvent(new ShortMessage(ShortMessage.POLY_PRESSURE, channel, notes[i].asMidi(), 0 ), timestamp+2));
			}
		}
	}
}
