package Model.Event;

import Model.Battlefield;
/**
 * Created by Anthony on 4/26/16.
 */

public class UpdateEvent extends Event {
	private float timestep;
	
	public UpdateEvent(Object s, float timestep){
		super(s);
		this.timestep = timestep;
	}
	
	public void run(){
		Battlefield b = (Battlefield) self;
		b.update(timestep);
		b.repaint();
	}
}