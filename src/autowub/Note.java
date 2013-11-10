package autowub;


public class Note {
	
	final NoteType nt;
	String pitch;
	final boolean isperc;
	boolean isdotted = false;
	final int velocity;
	final int octave;
	final static int defaultOctave = 7;
	final static int defaultVelocity = 80;
	
	public Note(NoteType type, String pitch, boolean perc, int velocity, int octave){
		nt = type;
		this.pitch = pitch;
		if(pitch.equals("e#")){
			this.pitch = "f";
		}else if(pitch.equals("b#")){
			this.pitch = "c";
		}
		this.isperc = perc;
		this.velocity = velocity;
		this.octave = octave;
	}
	
	public Note(double noteLength, String pitch, boolean perc, int velocity, int octave){
		this(fromLength(noteLength), pitch, perc, velocity, octave);
		
	}	
	
	public Note(double noteLength, String pitch, boolean perc, int velocity, int octave, boolean isDotted){
		this(fromLength(noteLength), pitch, perc, velocity, octave);
		this.isdotted = isDotted;
//		System.out.println
	}	
	
	public Note(NoteType type, String pitch, boolean perc, int velocity){
		this(type, pitch, perc, velocity, defaultOctave);
	}
	
	public Note(double noteLength, String pitch, boolean perc, int velocity){
		this(fromLength(noteLength),pitch, perc, velocity);
	}
	
	public Note(NoteType type, String pitch, boolean perc){
		this(type, pitch, perc, defaultVelocity, defaultOctave);
	}
	
	public Note(double noteLength, String pitch, boolean perc){
		this(fromLength(noteLength),pitch, perc, defaultVelocity);
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
		}else {
			return NoteType.SIXTEENTH;
		}
	}
	
	/**
	 * 
	 * @return number of 32nd notes
	 */
	public int noteLength(){
		int base = (isdotted ? 3:2);
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
