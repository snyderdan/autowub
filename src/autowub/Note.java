package autowub;


public class Note {
	
	final NoteType nt;
	final String pitch;
	final boolean dotted;
	final int velocity;
	
	public Note(NoteType type, String pitch, boolean dotted, int velocity){
		nt = type;
		this.pitch = pitch;
		this.dotted = dotted;
		this.velocity = velocity;
	}
	
	/**
	 * 
	 * @return number of 16th notes
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
		for (int i = 0; i < Song.keys.length; i++){
			if(noteToMIDI[i] == pitch){
				return i;
			}
		}
		return -1;
	}
	
	
}
