package com.example.earthquakeapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EarthquakeController {
    @RequestMapping("/getEarthquakeByDate")
    @ResponseBody
    public String GetEarthquake(@RequestParam("enddate") String enddate, @RequestParam("country") String country , Model m){
        EarthquakeQueryService service =new EarthquakeQueryService();
        return service.HttpResponseFromData(enddate ,country);
    }



}

