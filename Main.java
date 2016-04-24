
import Model.*;

public class Main {
	public static void main(Strings[] args){
		Battlefield b = new Battlefield();
		while( true ){
			try { Thread.sleep(10); } catch(e){}
			
			b.update(10);
		}
		
	}
}