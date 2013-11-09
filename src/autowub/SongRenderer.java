package autowub;

import java.util.ArrayList;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Sequencer;

public class SongRenderer {
	Sequencer seq;
	Song song = new Song();
	Synthesizer synth;
	public SongRenderer() throws MidiUnavailableException{
		seq = MidiSystem.getSequencer();
		synth = MidiSystem.getSynthesizer();
	}
	
	private ArrayList<ShortMessage> processTrack(Track t, int chan, int beatNote, int bpm){
		ArrayList<ShortMessage> notes = new ArrayList<ShortMessage>();
		for(Note note : t.notes){
			ShortMessage sm = new ShortMessage();
//			sm.setMessage(ShortMessage.NOTE_ON,	chan, data1, data2);
//			notes.add()
		}
		return notes;
	}
	
	public void play(Song s) throws MidiUnavailableException{
		Receiver rcv = synth.getReceiver();
//		ArrayList<ShortMessage> midiNotes = processTrack(s.soprano);
	
	}
		
	public void play(Song s) throws MidiUnavailableException, InvalidMidiDataException{
		if(!seq.isOpen()){
			seq.open();
		}
		seq.stop();
		seq.setMicrosecondPosition(0);
		seq.setTempoInBPM(s.bpm);
		seq.setSequence(s.getSequence());
		seq.start();
	}
}
