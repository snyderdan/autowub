package autowub;


import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class SongRenderer {
	Sequencer seq;
	public SongRenderer() throws MidiUnavailableException{
		seq = MidiSystem.getSequencer();
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
