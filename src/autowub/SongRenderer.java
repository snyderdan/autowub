package autowub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;

public class SongRenderer {
	Sequencer seqencer;
	Synthesizer synth;
	public SongRenderer() throws MidiUnavailableException{
		seqencer = MidiSystem.getSequencer();
		synth = MidiSystem.getSynthesizer();
		synth.open();
		try {
			Soundbank sb = MidiSystem.getSoundbank(new File("mariopaint.sf2"));
			boolean success = synth.loadAllInstruments(sb);
//			synth.getChannels()[0].programChange(18);
		} catch (InvalidMidiDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public void write(Song s){
//		Encoding ae = AudioFormat.Encoding.PCM_UNSIGNED;
//		int rate = 44100;//khz
//		int samplesize = 16;
//		int channels = 1;
//		boolean bigEndian = true;
//		AudioFormat fmt = new AudioFormat(ae, rate, samplesize, channels,(samplesize/8) * channels,rate ,bigEndian); 
		File out = new File("test.midi");
		try {
			MidiSystem.write(s.getSequence(), 0, out);
			String envp[] = new String[1];
			envp[0] = "PATH=" + System.getProperty("java.library.path");
				Process timidityplayer = Runtime.getRuntime().exec("timidity "+out.getName()+ " -T " + s.bpm);
				Runtime.getRuntime().exec("timidity "+out.getName()+ " -T " + s.bpm + " -Ov -o out.ogg", envp);
				Runtime.getRuntime().exec("timidity "+out.getName()+ " -T " + s.bpm + " -Or1ul -o out.raw", envp);
			try{
				timidityplayer.waitFor();
			}finally{
				timidityplayer.destroy();
			}
//			Runtime.getRuntime().exec("paplay out.ogg");
//			File raw = new File("out.raw");
//			FileReader fr = new  FileReader(raw);
//			Socket sock = new Socket(InetAddress.getByName("10.0.11.70"), 2345);
//			System.out.println("Streaming");
//			while(true){
//				try{
//					char[] sample = new char[2];
//					if(fr.read(sample, 0, sample.length) == -1){
//						break;
//					}
//					sock.getOutputStream().write(String.valueOf(sample).getBytes());
//					sock.getOutputStream().flush();
//				}catch (IOError e){
//					e.printStackTrace();
//				}
//			}
//			System.out.println("Exiting");
//			sock.close();
//			fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
