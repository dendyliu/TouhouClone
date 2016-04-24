
import Model.*;

public class Main {
	public static void main(String[] args){
		Battlefield b = new Battlefield();
		while( true ){
			try {
				Thread.sleep(10);
			}
			catch(Exception e){
			}
			
			b.update(10);
		}
		
	}
}