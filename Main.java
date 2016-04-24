
import Model.*;

public class Main {
	public static void main(String[] args){
		while( true ){
			try {
				Thread.sleep(10);
			} catch(Exception e){
				e.printStackTrace();
			}
			
			Battlefield.getInstance().update(10);
		}
		
	}
}