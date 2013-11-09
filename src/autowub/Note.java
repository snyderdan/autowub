package autowub;


public class Note {
	
	final NoteType nt;
	final String pitch;
	final boolean dotted;
	final int velocity;
	final int octave = 0;
	
	public Note(NoteType type, String pitch, boolean dotted, int velocity){
		nt = type;
		this.pitch = pitch;
		this.dotted = dotted;
		this.velocity = velocity;
	}
	
	public Note(double noteLength, String pitch, boolean dotted, int velocity){
		this(fromLength(noteLength),pitch, dotted, velocity);
	}
	
	
	public static NoteType fromLength(double length){
		if(length >= 1){
			return NoteType.WHOLE;
		}else if(length >= .5){
			return NoteType.HALF;
		}else if(length >= .25){
			return NoteType.QUARTER;
		}else if(length >= .125){
			return NoteType.EIGTH;
		}else{
			return NoteType.SIXTEENTH;
		}
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
			if(Song.keys[i] == pitch){
				return i;
			}
		}
		return -1;
	}
	
	
}
