package com.cs673olsum24.promanager.utils;

import org.json.simple.JSONObject;

public class ProjectTaskUtils {
  // Public constants for default values

  public static final String DEFAULT_TASK_ID = "NA";
  public static final String DEFAULT_PROJECT_ID = "NA";
  public static final String DEFAULT_TASK_NAME = "NA";

  
  public static final String DEFAULT_DESCRIPTION = "NA";

  public static final String DEFAULT_STATUS = "NA";

  public static final String DEFAULT_PRIORITY = "NA";
  public static final String DEFAULT_ASSIGNED_USER_ID = "NA";
  public static final Long DEFAULT_DATE = 0L;
  
  
  

  // Utility method to safely convert an object to a Long
  public static Long safelyConvertToLong(Object obj) {
    return obj instanceof Integer ? ((Integer) obj).longValue() : (Long) obj;
  }

  // Utility method to safely get a string from JSONObject with a default value
  public static String safelyGetString(JSONObject json, String key, String defaultValue) {
    return json.getOrDefault(key, defaultValue).toString();
  }
}