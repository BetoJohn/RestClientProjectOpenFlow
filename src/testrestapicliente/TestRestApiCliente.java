/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrestapicliente;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Beto
 */
public class TestRestApiCliente {

    private static int HTTP_COD_SUCESSO = 200;

    /**
     * /wm/topology/route/<src-dpid>/<src-port>/<dst-dpid>/<dst-port>/json
     * 
     * http://192.168.25.12:8080/wm/topology/route/00:00:00:00:00:00:00:01/1/00:00:00:00:00:00:00:03/1/json
     * 
     * /wm/device/
     * 
     * @param args the command line arguments
     */
    
    /*
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        //getSwitch();
        //postSwitch();
        
        

        try {

            URL url = new URL("http://192.168.25.12:8080/wm/staticflowpusher/list/00:00:00:00:00:00:00:02/json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() != HTTP_COD_SUCESSO) {
                throw new RuntimeException("HTTP error code : " + con.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println("Resposta: " + output);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
//---------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------
        //            HttpClient client = new DefaultHttpClient();
        //            HttpGet request = new HttpGet("http://localhost:8080/TestRestApi/webresources/teste");
        //            HttpResponse response = client.execute(request);
        //            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        //            String line = "";
        //            while ((line = rd.readLine()) != null) {
        //                System.out.println(line);
        //            }
        //
        //            // create HTTP Client
        //            HttpClient httpClient = HttpClientBuilder.create().build();
        //
        //            // Create new getRequest with below mentioned URL
        //            HttpGet getRequest = new HttpGet("http://localhost:8080/TestRestApi/webresources/teste");
        //
        //            // Add additional header to getRequest which accepts application/xml data
        //            getRequest.addHeader("accept", "application/xml");
        //
        //            // Execute your request and catch response
        //            HttpResponse response = httpClient.execute(getRequest);
        //
        //            // Check for HTTP response code: 200 = success
        //            if (response.getStatusLine().getStatusCode() != 200) {
        //                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        //            }
        //
        //            // Get-Capture Complete application/xml body response
        //            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        //            String output;
        //            System.out.println("============Output:============");
        //
        //            // Simply iterate through XML response and show on console.
        //            while ((output = br.readLine()) != null) {
        //                System.out.println(output);
        //            }
    }
    
    */

    private static void getSwitch() {
        try {
            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/TestRestApi/webresources/teste/get");
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n");
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void postSwitch(){
        try {

		Client client = Client.create();

		WebResource webResource = client
		   .resource("http://localhost:8080/TestRestApi/webresources/teste/post");

		String input = "{\"id\":\"00:00:00:00:00:00:00:01\",\"valor\":\"878787878\"}";

		ClientResponse response = webResource.type("application/json")
		   .post(ClientResponse.class, input);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);

	  } catch (Exception e) {

		e.printStackTrace();

	  }
    }

}
