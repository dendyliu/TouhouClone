package Controller.Event;

import View.Battlefield;
/**
 * Update Event Class
 * This class will handle the Update Event 
 * @author Anthony on 4/26/16.
 */

public class UpdateEvent extends Event {
	private float timestep;
	/*
        * UpdateEvent Constructor
        * @param s object that will do the event
        * @param timestep time for update.
        */
	public UpdateEvent(Object s, float timestep){
		super(s);
		this.timestep = timestep;
	}
	/*
        * Run the Update Event
        */
	public void run(){
		Battlefield b = (Battlefield) self;
		b.update(timestep);
		b.repaint();
	}
}