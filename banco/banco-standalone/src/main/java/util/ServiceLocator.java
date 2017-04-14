package util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {
private static final String APP_NAME = "banco6";
private static final String MODULE_NAME = "banco-negocio6";
private static final String DISTINCT_NAME = "";
/**
* MEtodo par aubicar el EJB remoto
* @param nombreClase, nombre completo de la clase
* @param interfaceRemota, .class de la interface remota.
* @return
* @throws NamingException
*/
public static Object buscarEJB(String nombreClase,String interfaceRemota)
throws NamingException{
Properties prop = new Properties();
prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
Context context = new InitialContext(prop);
return context.lookup("ejb:" + APP_NAME + "/" +
MODULE_NAME + "/"+
DISTINCT_NAME + "/" +
nombreClase + "!" +
interfaceRemota
);
}
}