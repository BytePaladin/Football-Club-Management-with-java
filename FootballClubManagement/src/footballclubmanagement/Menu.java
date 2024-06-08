package footballclubmanagement;

import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
public class Menu {

	public static void main(String[] args) {
		//selection menu
		Scanner input=new Scanner(System.in);
		System.out.println("\t\tWelcome to Football Club Management:");
		System.out.println("\t=================================================\t");
		System.out.println("MENU:\nLogin as:");
		System.out.println(" 1.Manager"
				+ "\n 2.Coach"
				+ "\n 3.Player");
		int selectionInput=input.nextInt();
		switch(selectionInput)
		{
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
			default:System.out.println("INVALID INPUT.");
			break;
		}
		
		
	}

}
