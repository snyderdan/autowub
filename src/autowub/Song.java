package autowub;

public class Song {
	Track soprano, alto, tenor, bass;
	int bpm;
	int beatNote;
	//this confusses me Will
	Note note = new Note(null, null, false, beatNote);
	
	/**
	 * @return number of beats based on number of 32nd notes and beatNote
	 */
	public int beatNum(){
		int beats = (note.noteLength()/2);
		return beats;
	}
	
	void play(){
		
	}
}
