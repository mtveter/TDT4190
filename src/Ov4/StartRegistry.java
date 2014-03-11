package Ov4;
import java.rmi.registry.*;

public class StartRegistry
{
  /**
   * Starts up a new RMI registry on the local host, and binds a RegistryProxy object to it.
   * The RegistryProxy object offers the same functionality as the registry itself, except
   * it also allows non-local objects to bind to it.
   *
   * @param args Command line parameters, the first parameter specifies the port of the RMI registry.
   */
  public static void main(String[] args)
  {
    if (true) {
      try {
        Registry r = LocateRegistry.createRegistry(Integer.parseInt("1111"));
        r.bind("RegistryProxy", new RegistryProxyImpl());
        System.out.println("RMI registry is now running on port " + "1111" + '.');
      } catch (Exception re) {
      }
    }
    else {
      System.out.println("You must specify a port number for this registry!");
    }
  }
}
