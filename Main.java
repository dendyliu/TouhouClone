
import Model.*;

public class Main {
	public static void main(String[] args){
		Battlefield b = new Battlefield();
		while( true ){
			try {
				Thread.sleep(20);
			} catch(Exception e){
				e.printStackTrace();
			}
			
			b.update(0.02f);
		}
		
	}
}