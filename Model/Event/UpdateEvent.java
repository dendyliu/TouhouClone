package Model.Event;

import Model.Battlefield;

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