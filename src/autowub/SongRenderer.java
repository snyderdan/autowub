package autowub;

import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class SongRenderer {
	Sequencer seqencer;
	public SongRenderer() throws MidiUnavailableException{
		seqencer = MidiSystem.getSequencer();
	}
	
	public long play(Song s) throws MidiUnavailableException, InvalidMidiDataException{
		if(!seqencer.isOpen()){
			seqencer.open();
		}
		seqencer.stop();
		seqencer.setMicrosecondPosition(0);
		seqencer.setTempoInBPM(s.bpm);
		Sequence seq = s.getSequence();
		seqencer.setSequence(seq);
		seqencer.start();
		return seq.getTracks()[0].ticks();
	}
	
}
