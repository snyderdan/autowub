package autowub;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;

public class SongRenderer {
	Sequencer seqencer;
	Synthesizer synth;
	public SongRenderer() throws MidiUnavailableException{
		seqencer = MidiSystem.getSequencer();
		synth = MidiSystem.getSynthesizer();
		synth.getReceiver();
		Instrument[] instruments = synth.getAvailableInstruments();
		for(int i = 0; i< instruments.length; i++){
			System.out.println(instruments[i].getName()+ ", " + i);
		}
		if(!seqencer.isOpen()){
			seqencer.open();
		}
	}
	
	public long play(Song s) throws MidiUnavailableException, InvalidMidiDataException{
		seqencer.stop();
		seqencer.setMicrosecondPosition(0);
		seqencer.setTempoInBPM(s.bpm);
		Sequence seq = s.getSequence();
		seqencer.setSequence(seq);
		seqencer.start();
		return seq.getTracks()[0].ticks();
	}
	
	public void write(){
		Encoding ae = AudioFormat.Encoding.PCM_UNSIGNED;
		int rate = 44100;//khz
		int samplesize = 16;
		int channels = 1;
		boolean bigEndian = true;
		AudioFormat fmt = new AudioFormat(ae, rate, samplesize, channels,(samplesize/8) * channels,rate ,bigEndian); 
		File out = new File("out.pcm");
//		out.
	}
	
}
