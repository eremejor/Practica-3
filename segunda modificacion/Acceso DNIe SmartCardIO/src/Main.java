

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author toni
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        ByteArrayInputStream bais=null;
       //read("cert.cer");
       
      // FileInputStream fis = new FileInputStream("cert.cer");
      
       
      // byte value[] = new byte[fis.available()];
      //   fis.read(value);
      //  bais = new ByteArrayInputStream(value);

        //TODO: Obtener los datos del DNIe
        ObtenerDatos od = new ObtenerDatos();
        String nif = od.LeerNIF();
         String user = nif;
         String dni = od.LeerNIF2();
         int password = 123456 ;
        
        System.out.println("El usuario es: "+nif+"  El Dni es: "+dni+"  y la contraseña:  "+password);
        //TODO: Autenticarse en el servidor
        
    
    }

}
