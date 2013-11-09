package autowub;


public class Note {
	static String[] noteToMIDI= {"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
	
	
	NoteType nt;
	String pitch;
	boolean dotted;
	
	public Note(NoteType type, String pitch, boolean dotted){
		nt = type;
		this.pitch = pitch;
		this.dotted = dotted;
	}
	
	
}
