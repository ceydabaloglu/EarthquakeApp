package com.example.earthquakeapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EarthquakeController {
    @RequestMapping("/getEarthquakeByDate")
    @ResponseBody
    public String GetEarthquake(@RequestParam("enddate") String enddate, @RequestParam("country") String country , Model m){
        m.addAttribute("deneme yapıyorum");
        return "index";
    }

    @GetMapping("//getEarthquakeByDate")
    public String showData(@PathVariable("enddate") String Enddate, Model m){
        m.addAttribute("deneme yapıyorum");
        return "index";
    }

}

