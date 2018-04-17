package tema3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AFDMain {

	public static void main(String[] args) {
		int optiune = 0;
		boolean continuare = true;
		while (continuare) {
			try {
				AFD obj = new AFD();
				obj.citire();
				if(obj.validareAFD()==true)
				{
				obj.start();
				}
				else 
				{
					System.out.println(" there it's a problem with AFD ");
				}
			} catch (Exception e) {
				System.out.println(" an error has been identified ");
			}
			System.out.println(" if you want program to stop, press 1");
			System.out.println(" else  press 2 ");
			try {
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				optiune = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(" error ");
			}
			if (optiune == 1)
				continuare = false;
		}
		
	}
}