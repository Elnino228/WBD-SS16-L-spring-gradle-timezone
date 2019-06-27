package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {

    @PostMapping("/worldclock")
    public String getTimeByTimezone (ModelMap modelMap, @RequestParam(value = "city",required = false,defaultValue ="Asia/Ho_Chi_Minh") String city){
        //get current time at local
        Date date=new Date();
        //get timezone by the local
        TimeZone local=TimeZone.getDefault();
        //get timezone by the specified city
        TimeZone locate =TimeZone.getTimeZone(city);
        //calculate the current time in the specified city
        long locate_time=date.getTime()+(locate.getRawOffset()-local.getRawOffset());
        //reset the date by locale_time
        date.setTime(locate_time);
        //set data sent to view
        modelMap.addAttribute("city",city);
        modelMap.addAttribute("date",date);

        return "index";
    }
}
