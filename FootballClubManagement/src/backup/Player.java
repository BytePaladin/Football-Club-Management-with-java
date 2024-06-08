package footballclubmanagement;

import java.util.Scanner;
public class Player extends Person {
	private int jersey;

	public Player(String name,String password,long ID,int jersey)
	{
		super(name,ID);
		this.jersey=jersey;
	}

	public int getJersey()
	{
		return jersey;
	}

	public void setJersey(int jersey)
	{
		this.jersey = jersey;
	}
	
	

}
