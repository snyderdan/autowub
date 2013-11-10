package autowub;


public class Note {
	
	final NoteType nt;
	String pitch;
	final boolean dotted;
	final int velocity;
	final int octave;
	final static int defaultOctave = 6;
	final static int defaultVelocity = 80;
	
	public Note(NoteType type, String pitch, boolean dotted, int velocity, int octave){
		nt = type;
		this.pitch = pitch;
		if(pitch.equals("e#")){
			this.pitch = "f";
		}else if(pitch.equals("b#")){
			this.pitch = "c";
		}
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
	
	public Note(NoteType type, String pitch, boolean dotted){
		this(type, pitch, dotted, defaultVelocity, defaultOctave);
	}
	
	public Note(double noteLength, String pitch, boolean dotted){
		this(fromLength(noteLength),pitch, dotted, defaultVelocity);
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
			if(Song.keys[i].equals(pitch)){
				return i + 11*octave;
			}
		}
		System.out.println("invalid pitch " + pitch);
		return -1;
	}
	
	
}
