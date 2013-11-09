package autowub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

public class SongRenderer {
	Sequencer seq;
	public SongRenderer() throws MidiUnavailableException{
		seq = MidiSystem.getSequencer();
	}
	
	private HashMap<ShortMessage, Long> processTrack(Track t, int chan, Song s) throws InvalidMidiDataException{
		HashMap<ShortMessage, Long> notes = new HashMap<ShortMessage, Long>();
		for(Note note : t.notes){
			ShortMessage on = new ShortMessage();
			on.setMessage(ShortMessage.NOTE_ON, chan, note.asMidi(), note.velocity);
			notes.put(on, getNoteTimeStamp(note));
			ShortMessage off = new ShortMessage();
			off.setMessage(ShortMessage.NOTE_OFF, chan, note.asMidi(), note.velocity);
			notes.put(off, getNoteTimeStamp(note));
			
		}
		return notes;
	}
	
	private long getNoteTimeStamp(Note n){
		return 0;
	}
	
	
	public void play(Song s) throws MidiUnavailableException{
		seq.stop();
		seq.setMicrosecondPosition(0);
		seq.setTempoInBPM(s.bpm);
		for(int i = 0; i < s.tracks.length; i++){
			HashMap<ShortMessage, Long> processed = new HashMap<ShortMessage, Long>();
			try {
				processed = processTrack(s.tracks[i], i, s );
				for(Entry<ShortMessage, Long> entry : processed.entrySet()){
					rcv.send(entry.getKey(), 100+entry.getValue());;
				}
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
