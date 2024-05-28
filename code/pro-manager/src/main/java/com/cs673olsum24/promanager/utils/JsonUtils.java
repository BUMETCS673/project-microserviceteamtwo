package com.cs673olsum24.promanager.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtils {
private static final Logger LOGGER = LogManager.getLogger(JsonUtils.class);
	
	@SuppressWarnings("unchecked")
	public static List<Object> output(List<Object[]> data) throws JsonProcessingException, ParseException
	{
		List<Object> out = new ArrayList<>();
    	for (Object[] result : data) {
    		
    		ObjectMapper mapper = new ObjectMapper();
    		JSONObject obj = new JSONObject();
    		for (int i = 0;i<result.length;i++)
    		{
    			String jsonString = mapper.writeValueAsString(result[i]);
        		JSONObject tempobj = (JSONObject) new JSONParser().parse(jsonString);    	
        		if(tempobj!=null)
        		{
        			obj.putAll(tempobj);    			
        		}			
    			
    		}
    		out.add(obj); 
    		
    	}
    		
    	return out;	
	}
	
	public static JSONObject convertPayload(Map<String, Object> payload){
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString ="";
		JSONObject jsonObject = new JSONObject();
		try {
			jsonString = objectMapper.writeValueAsString(payload);
		} catch (JsonProcessingException e) {
			LOGGER.warn(e);;
		}
		
		try {
			Object obj = new JSONParser().parse(jsonString);
			jsonObject = (JSONObject) obj;
		} catch (ParseException e) {
			LOGGER.warn(e);
		}
		
		return jsonObject;		
	}
	
	public static List<String> getLast30Dates(){
    	Date end = new Date();	
    	Calendar calendar1 = new GregorianCalendar();
    	calendar1.setTimeZone(TimeZone.getTimeZone("GMT"));
    	calendar1.setTime(end);
    	calendar1.add(Calendar.DATE, -30);
    	Date initial = calendar1.getTime();		
    	List<String> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(initial);

        while (calendar.getTime().getTime() <= end.getTime()){
            Date result = calendar.getTime();
            SimpleDateFormat gmtDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            dates.add(gmtDateFormat.format(result));
            calendar.add(Calendar.DATE, 1);       
        }      
        return dates;
    }

}
