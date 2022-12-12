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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EarthquakeQueryService {
    private URL Url ;
    public EarthquakeQueryService(){

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

    //Try connection
    public String HttpResponseFromData (String days, String country){
        String Response ="Turkei " ;
        StringBuilder resp = new StringBuilder();
        try {
            var date = LocalDate.now();
            var startDate = date.minusDays(Integer.parseInt(days));
            URL x = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + startDate.toString() +"&endtime=" + date.toString());
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
                    JSONObject earthquake = (JSONObject)features.get(i);
                    JSONObject properties = (JSONObject)earthquake.get("properties");
                    var place = properties.get("place");
                    var mag = properties.get("mag");
                    var timeEarthquake = properties.get("time");
                    Date happenedDate = new Date((long)timeEarthquake);
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String formatted = format.format(happenedDate);
                    resp.append("Place " + place.toString() + " Magnitude " +  mag.toString() + " Date/Time " + formatted + "<br>" );
                }
            }

        }
        catch (MalformedURLException e) {
            System.out.println("Error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resp.toString();
    }
}
