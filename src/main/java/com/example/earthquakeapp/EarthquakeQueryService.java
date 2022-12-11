package com.example.earthquakeapp;

import org.apache.coyote.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeQueryService {
    private URL Url ;
    public EarthquakeQueryService(URL url){
        Url = url;
    }
    public List<Earthquake> findEarthquakes(){
        List<Earthquake> elist = new ArrayList<Earthquake>();
        return elist;
    }

    public String convertStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int ch; (ch = is.read()) != -1; ) {
            sb.append((char) ch);
        }
        return sb.toString();
    }
    public String HttpResponse (){
        String Response =" " ;
        //HttpURLConnection  conn = null;
        //InputStream istream = null;
        
        try{
            URL x = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + "2022-01-01" +"&endtime=" +"2022-01-02");
            HttpURLConnection conn = (HttpURLConnection) x.openConnection();
            conn.connect();
            int responseCode = conn.getResponseCode();
            InputStream inputStream = conn.getInputStream();
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                String jsonString = responseStrBuilder.toString() ; //assign your JSON String here
                JSONObject obj = new JSONObject(jsonString);
                JSONArray features = (JSONArray) obj.get("features");
                for(int i = 0 ; i < features.length(); i++){
                    JSONObject asd = (JSONObject)features.get(i);
                    JSONObject properties = (JSONObject)asd.get("properties");
                    System.out.println("asdd");
                }
                System.out.println("asdd");
                //JSONParser x = new Json.createParser(inputStr);
//                elist.add(new Earthquake(responseStrBuilder.toString()));
            }

            System.out.println("asdd");

        }
        catch (MalformedURLException e) {
            System.out.println("Error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Hello world!");
        return Response;
    }


}
