package com.example.QuoraApp.utils;

import java.time.LocalDateTime;

public class CursorUtils {
  
  public static boolean isValidCursor(String cursor){
    if(cursor == null || cursor.isEmpty()){
      return false;
    }

    try {
      LocalDateTime.parse(cursor); // Try to parse the cursor as a LocalDateTime, if it fails, it will throw an exception
      return true;
    } catch (Exception e) {
      // TODO: handle exception
      return false;
    }
  }

  public static LocalDateTime parseCursor(String cursor){
    if(!isValidCursor(cursor)){
      throw new IllegalArgumentException("Invalid cursor");
    }

    return LocalDateTime.parse(cursor);
  }
}
