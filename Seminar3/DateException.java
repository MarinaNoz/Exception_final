package Seminar3;

import java.time.format.DateTimeParseException;

public class DateException extends DateTimeParseException{

    public DateException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
       
    }
    
    
}
