package com.example.earthquakeapp;

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
import java.util.Date;
import java.util.Locale;


public class EarthquakeQueryService {

    public EarthquakeQueryService(){

    }

    //Try connection
    public String HttpResponseFromData (String days, String country){
        StringBuilder resp = new StringBuilder();
        try{
            var date = LocalDate.now();
            var startDate = date.minusDays(Integer.parseInt(days));
            URL x = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + startDate.toString() +"&endtime=" + date.toString());
            HttpURLConnection conn = (HttpURLConnection) x.openConnection();
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            if (inputStream != null) {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                String jsonString = responseStrBuilder.toString(); //assign your JSON String here
                JSONObject obj = new JSONObject(jsonString);
                JSONArray features = (JSONArray) obj.get("features");
                for(int i = 0 ; i < features.length(); i++){
                    JSONObject earthquake = (JSONObject)features.get(i);
                    JSONObject geometry = (JSONObject)earthquake.get("geometry");
                    JSONObject properties = (JSONObject)earthquake.get("properties");
                    var place = properties.get("place");
                    String countrySubbed = place.toString().substring(place.toString().lastIndexOf(",") + 2, place.toString().length());
                    if(isCountry(countrySubbed)){
                        String EarthQuakeCountry = countrySubbed;
                        if(EarthQuakeCountry.compareTo(country) == 0 ){
                            var mag = properties.get("mag");
                            var timeEarthquake = properties.get("time");
                            Date happenedDate = new Date((long)timeEarthquake);
                            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String formatted = format.format(happenedDate);
                            resp.append("Country "+ EarthQuakeCountry + " Place " + place.toString() + " Magnitude " +  mag.toString() + " Date/Time " + formatted + "<br>" );
                        }
                    }
                    else{
                        //This means this is a state of USA.
                        if(country.compareTo("United States") == 0 && place != null){
                            var mag = properties.get("mag");
                            var timeEarthquake = properties.get("time");
                            Date happenedDate = new Date((long)timeEarthquake);
                            DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String formatted = format.format(happenedDate);
                            resp.append("Country "+ "United States" + " Place " + place.toString() + " Magnitude " +  mag.toString() + " Date/Time " + formatted + "<br>" );
                        }
                    }
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

    public boolean isCountry(String country){
        String[] countryCodes = Locale.getISOCountries();
        for(String countryCode : countryCodes){
            Locale locale = new Locale("",countryCode);
            String code = locale.getCountry();
            String name = locale.getDisplayCountry();
            if(country.compareTo(name) == 0){
                return true;
            }
        }
        return false;
    }
}
