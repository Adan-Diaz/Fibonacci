package example;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class FibonacciServer {
	public static void main(String args[]) {
		try {
			//crear instancia del ORB
			ORB orb = ORB.init(args, null);
			//crear instancia a POA
			POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			//activar POA Manager
			rootPoa.the_POAManager().activate();
			
			//instancia de clase fibnacci
			FibonacciImp fiboImp = new FibonacciImp();
			// obtiene la referencia de la clase FibonacciImp (servart)
			Object ref = rootPoa.servant_to_reference(fiboImp);
			Fibonacci href = FibonacciHelper.narrow(ref);
			
			//Obtener referencia del servicio de directorios
			Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt rootContext = NamingContextExtHelper.narrow(objRef);
			
			NameComponent nameComponent[] = rootContext.to_name("Fibonacci");
			rootContext.rebind(nameComponent, href);
			
			//Listo para recibir peticiones
			System.out.println("Servidor > listo y en espera");
			orb.run();
			
		}catch(Exception ex) {
			System.err.println("Error :,v : " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
