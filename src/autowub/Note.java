package autowub;


public class Note {
	static String[] noteToMIDI= {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
	
	
	NoteType nt;
	String pitch;
	boolean dotted;
	
	public Note(NoteType type, String pitch, boolean dotted, int velocity){
		nt = type;
		this.pitch = pitch;
		this.dotted = dotted;
	}
	
	/**
	 * 
	 * @return number of 32nd notes
	 */
	public int noteLength(){
		int base = (dotted ? 3: 2);
		switch(nt){
		case WHOLE:
			base*=2;
		case HALF:
			base*=2;
		case QUARTER:
			base*=2;
		case EIGTH:
			base*=2;
		case SIXTEENTH:
			base*=1;
			break;
		}
		return base;
	}
	
	public int asMidi(){
		for (int i = 0; i < noteToMIDI.length; i++){
			if(noteToMIDI[i] == pitch){
				return i;
			}
		}
		return -1;
	}
	
	
}
