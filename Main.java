
import Model.Battlefield;

public class Main {
	public static void main(String[] args){
		java.awt.EventQueue.invokeLater(() -> {
			Battlefield b = new Battlefield();
		});

		
		/*
		while( true ){
			
			b.update(0.02f);
			b.repaint();
			try { Thread.sleep(20); } catch(Exception ex){}
			
		}*/
	}
}