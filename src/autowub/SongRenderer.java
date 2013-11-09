package autowub;

import java.util.ArrayList;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

public class SongRenderer {
	Song song = new Song();
	Synthesizer synth;
	public SongRenderer() throws MidiUnavailableException{
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
	
	/*
	 * @return elapsed time for set amount of beats
	 */
	private long getNoteTimeStamp(Note n){
		//5 just random number
		long timeStamp = song.beatNum() * 5;
		return timeStamp;
	}
	
	
	public void play(Song s) throws MidiUnavailableException{
		Receiver rcv = synth.getReceiver();
//		ArrayList<ShortMessage> midiNotes = processTrack(s.soprano);
		
	}
}
