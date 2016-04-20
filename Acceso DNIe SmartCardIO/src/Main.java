

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import org.bouncycastle.util.encoders.Hex;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author toni
 */
public class Main 
{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception
    {
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
         String password = "123456" ;
         
        
        System.out.println("El usuario es: "+user+"  El Dni es: "+dni+"  y la contraseña:  "+password);
        
          MessageDigest md;
        byte[]buffer,digest;
        String hash="";
        
	 
        try {     
            //SHA-1
            buffer=password.getBytes();
            md= MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            digest = md.digest();
          //  System.out.println(Hex.encodeHex(digest));
            
           for(byte aux : digest) {
            int b = aux & 0xff;
                if (Integer.toHexString(b).length() == 1) hash += "0";
                hash += Integer.toHexString(b);
                    }
            
        } catch (NoSuchAlgorithmException e) {
            //Error
        }
    
     System.out.println("La nueva clave es:" + hash);
        
       
        
    
        
        String userAgent = "Mozilla/5.0 (X11; Linux x86_64; rv:26.0) Gecko/20100101 Firefox/26.0";
        String address = "http://localhost/dnie/autentica.php";
        String forSending = user;
        String forSending1 = dni;
        String forSending2 = hash;
        String charset = "UTF-8";

        String stringToSend = URLEncoder.encode(forSending, charset);
        String stringToSend1 = URLEncoder.encode(forSending1, charset);
        String stringToSend2 = URLEncoder.encode(forSending2, charset);
        URL URL = new URL(address);
        HttpURLConnection connection = (HttpURLConnection)URL.openConnection();
        connection.addRequestProperty("User-Agent", userAgent);
        
        //Para poder escribir datos a la URL
        connection.setDoOutput(true);

        // Indicamos el tipo de request, POST en este caso
        connection.setRequestMethod("POST");

        // Indicamos un timeout de 10 segundos
        connection.setReadTimeout(10*1000);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        //out.write("user=" + stringToSend );
         //System.out.println(stringToSend);
        out.write("user="+stringToSend+"&dni="+stringToSend1+"&password="+stringToSend2);
        //out.write("$dni=" + stringToSend1);
        //out.write("$password=" + stringToSend2);
        out.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));
        String response;
        while((response = in.readLine()) != null)
       //     System.out.println(response);
            if(response.contains("Bienvenido Sr.")==true)
          {
                System.out.println(response);
           }
        in.close();
        
        
        
        
        

       
        
    }
  
}
