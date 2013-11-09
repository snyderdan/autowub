package autowub;


public class Note {
	
	final NoteType nt;
	final String pitch;
	final boolean dotted;
	final int velocity;
	final int octave;
	final static int defaultOctave = 5;
	
	public Note(NoteType type, String pitch, boolean dotted, int velocity, int octave){
		nt = type;
		this.pitch = pitch;
		this.dotted = dotted;
		this.velocity = velocity;
		this.octave = octave;
	}
	
	public Note(double noteLength, String pitch, boolean dotted, int velocity, int octave){
		this(fromLength(noteLength), pitch, dotted, velocity, octave);
	}
	
	public Note(NoteType type, String pitch, boolean dotted, int velocity){
		this(type, pitch, dotted, velocity, defaultOctave);
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
		for (int i = 0; i < Song.keys.length; i++){
			if(Song.keys[i] == pitch){
				return i + 11*octave;
			}
		}
		return -1;
	}
	
	
}
