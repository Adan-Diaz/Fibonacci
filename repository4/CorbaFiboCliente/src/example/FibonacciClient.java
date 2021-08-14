package example;

import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class FibonacciClient {
	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			Fibonacci fiboImp = FibonacciHelper.narrow(ncRef.resolve_str("Fibonacci"));
			
			//Capturamos la entrada del usuario
			Scanner c = new Scanner(System.in);
			System.out.println("PC> - Generadador de Fibonacci -");
			while (true) {
				System.out.println("PC> Ingrese el numero [x:salir]:");
				String input = c.nextLine();
				if(input.contentEquals("x")) 
					break;
				int numero = Integer.parseInt(input);
				String response = fiboImp.generar(numero);
				System.out.println("Fibonacci de " + numero + " es " + response);
				
				System.out.println("---------***************------");				
			}
		}catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
