package compas.RestService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by CLLSDJACKT013 on 26/01/2018.
 */
public class RestService {
    @Autowired
    private RestServiceConfig restServiceConfig;
    public RestService(){
        try{
            URL url = new URL(restServiceConfig.serviceIP_PORT);
            /*****Get some basic info of the supplied url********/
            System.out.println("URL is::::" +url.toString());
            System.out.println("Protocol is ::::::"+url.getProtocol());
            System.out.println("Authority is :::::"+url.getProtocol());
            System.out.println("Port is "+url.getPort());
            System.out.println("Default port is :::::"+url.getDefaultPort());
            System.out.println("Path is ::::::"+url.getPath());
            System.out.println("File is ::::::" +url.getFile());

            URLConnection connection = url.openConnection();
            HttpsURLConnection httpsURLConnection =null;
            HttpURLConnection httpURLConnection =null;
            /******determine the instance of url connection obtained*****/
            if(connection instanceof HttpURLConnection){
                httpURLConnection = (HttpURLConnection) connection;
                System.out.println("Connecting to URL "+httpURLConnection.getURL());
                System.out.println("URL content type::::"+httpURLConnection.getContentType());
                System.out.println("URL content::"+httpURLConnection.getContent());
                System.out.println("Http method" +httpsURLConnection.getRequestMethod());
                System.out.println("Response code:::"+httpURLConnection.getResponseCode());

                String urlString = "";
                String current;
                //read the URL connection's input stream
                BufferedReader dataIn = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while((current = dataIn.readLine()) != null){
                    urlString += current;
                }
                System.out.println("Response from URL" +urlString);
            }
            else if(connection instanceof HttpsURLConnection){
                httpsURLConnection = (HttpsURLConnection)connection;
                System.out.println("Connecting to URL::"+httpsURLConnection.getURL());
                System.out.println("Content type:::"+httpsURLConnection.getContentType());
                System.out.println("Content ::::"+httpURLConnection.getContent());
                System.out.println("Response code :::"+httpsURLConnection.getResponseCode());
                System.out.println("Response message::::"+httpsURLConnection.getResponseMessage());

                String urlString = "";
                String current;
                BufferedReader dataIn = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                while((current = dataIn.readLine()) != null){
                    urlString += current;
                }
                System.out.println("Response from URL::"+urlString);
            }
            else{
                System.err.println("Supply valid url");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
