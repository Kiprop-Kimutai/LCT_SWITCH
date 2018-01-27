package compas.RestService;

import com.google.gson.Gson;
import compas.device.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by CLLSDJACKT013 on 26/01/2018.
 */
@Configuration
public class RestServiceConfig {
    private Logger logger = LoggerFactory.getLogger(RestServiceConfig.class);
    @Value("http://localhost:3000")
    public String serviceIP_PORT;


    public String RestServiceConfiguration(String endpoint, String requestMethod, String input){
         String fullURL= String.format(serviceIP_PORT+""+endpoint);
         logger.info("Final URL::::" +fullURL);
        String responseString = "";
        String current;

        try{
            URL url = new URL(fullURL);
            /*****Get some basic info of the supplied url********/
            System.out.println("URL is::::" +url.toString());
            System.out.println("Protocol is ::::::"+url.getProtocol());
            System.out.println("Authority is :::::"+url.getProtocol());
            System.out.println("Port is "+url.getPort());
            System.out.println("Default port is :::::"+url.getDefaultPort());
            System.out.println("Path is ::::::"+url.getPath());
            System.out.println("File is ::::::" +url.getFile());

            URLConnection connection = url.openConnection();
            if(requestMethod == "POST"){
                connection.setDoOutput(true);
            }
            //connection.setDoOutput(true);
            HttpsURLConnection httpsURLConnection =null;
            HttpURLConnection httpURLConnection =null;
            /******determine the instance of url connection obtained*****/
            if(connection instanceof HttpURLConnection){
                httpURLConnection = (HttpURLConnection) connection;
                //System.out.println("Connecting to URL "+httpURLConnection.getURL());
                httpURLConnection.setRequestMethod(requestMethod); //set request method
                //System.out.println("Http method" +httpURLConnection.getRequestMethod());
                //System.out.println("URL content type::::"+httpURLConnection.getContentType());
                //System.out.println("URL content::"+httpURLConnection.getContent());
                //System.out.println("Response code:::"+httpURLConnection.getResponseCode());

                if(requestMethod == "GET"){
                    logger.info("-----------------HATHAWAY-----------------");
                    //httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.setRequestProperty("Accept","application/json");

                    if(httpURLConnection.getResponseCode() != (200)){
                        throw new RuntimeException("Failed: Http Error code::"+httpURLConnection.getResponseCode());
                    }

                    BufferedReader dataIn = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while((current = dataIn.readLine()) != null){
                       System.out.println(current);
                        responseString += current;
                    }
                    logger.info("Response from URL >>>" +responseString);
                    httpURLConnection.disconnect();
                    return responseString;

                }
                else if(requestMethod == "POST"){
                    logger.info("--------------HATHAWAY POST---------------");
                    //httpURLConnection.setDoOutput(true);
                   // httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type","application/json");
                   // httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    //String deviceJSON = new Gson().toJson(device);
                    os.write(input.getBytes());
                    os.flush();

                    if(httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK){
                            throw new RuntimeException("Failed::Http Error code::" +httpURLConnection.getResponseCode());
                    }
                    BufferedReader dataIn = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while((current = dataIn.readLine()) != null){
                        responseString += current;
                    }
                    logger.info("Response from URL >>>>>" +responseString);
                    httpURLConnection.disconnect();
                    return responseString;
                }
                httpURLConnection.disconnect();
                return responseString;
            }
            else if(connection instanceof HttpsURLConnection){
                httpsURLConnection = (HttpsURLConnection)connection;
                System.out.println("Connecting to URL::"+httpsURLConnection.getURL());
                System.out.println("Content type:::"+httpsURLConnection.getContentType());
                System.out.println("Content ::::"+httpURLConnection.getContent());
                System.out.println("Response code :::"+httpsURLConnection.getResponseCode());
                System.out.println("Response message::::"+httpsURLConnection.getResponseMessage());

                if(requestMethod == "GET"){
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.setRequestProperty("Accept","application/json");

                    if(httpsURLConnection.getResponseCode() != 200){
                        throw new RuntimeException("Failed:: Response code ::"+httpsURLConnection.getResponseCode());
                    }
                    BufferedReader dataIn = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while((current = dataIn.readLine()) != null){
                        responseString += current;
                    }
                    System.out.println("Response from URL" +responseString);
                }
                else if (requestMethod == "POST"){
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.setRequestMethod("POST");
                    httpsURLConnection.setRequestProperty("Content-Type","application/json");

                    OutputStream os = httpURLConnection.getOutputStream();
                    //String deviceJSON = new Gson().toJson(device);
                    os.write(input.getBytes());
                    os.flush();
                    if(httpsURLConnection.getResponseCode() != HttpsURLConnection.HTTP_CREATED){
                        throw new RuntimeException(("Failed::: Response code::"+httpsURLConnection.getResponseCode()));
                    }
                    BufferedReader dataIn = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while((current = dataIn.readLine()) != null){
                        responseString += current;
                    }
                    System.out.println("Response from URL" +responseString);

                }
                httpsURLConnection.disconnect();
                return responseString;
            }
            else{
                System.err.println("Supply valid url");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return responseString;
    }
}
