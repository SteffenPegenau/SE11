package tud.game;

public class IdGenerator {
	
	private int count=1;
	
	public int createNewId(){
		return count++;
	}

}
