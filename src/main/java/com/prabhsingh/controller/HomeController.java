package com.prabhsingh.controller;

import com.prabhsingh.beans.Time;
import com.prabhsingh.dao.Client;
import org.springframework.web.bind.annotation.*;

/**
 * Created by prabh on 2016-11-04.
 */
@RestController
public class HomeController {

    @RequestMapping(value = "/", produces = "application/json")
    public String home() {
        return "You shouldn't be accessing this page";
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/getPhoneCount/last30days", produces = "application/json")
    public String getPhoneCountByMonth() {
        Client bg = new Client();
        Time t = new Time();
        String query = "SELECT\n" +
                "  user_dim.device_info.mobile_brand_name AS brand,\n" +
                "  user_dim.device_info.mobile_model_name AS phone,\n" +
                "  COUNT(DISTINCT(user_dim.device_info.mobile_model_name)) AS phoneCount\n" +
                "FROM\n" +
                "  `com_GetThinkn_Thinkn_IOS.app_events_*`\n" +
                "WHERE\n" +
                "  _TABLE_SUFFIX BETWEEN '" + t.getLast30Days() + "' AND '" + t.getPreviousDay() + "'" +
                "GROUP BY\n" +
                "  brand,\n" +
                "  phone\n" +
                "LIMIT\n" +
                "  1000";

        return bg.printResult(bg.getResult(query));
    }

}