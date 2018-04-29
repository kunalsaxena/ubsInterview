package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.enums.LampColorType;
import com.ubs.opsit.interviews.exceptions.InvalidTimeException;

public class TimeConverterImpl implements TimeConverter {

    /**
     * Convert input time String to Berlin Clock format
     *
     * @param aTime
     * @return time String in Berlin Clock format
     */
    @Override
    public String convertTime(String aTime) {
        StringBuilder berlinClockBuilder = new StringBuilder();
        String[] timeArr = aTime.split(":");

        validateTime(timeArr);

        convertSecondForBoard(timeArr[2], berlinClockBuilder);
        convertHoursForBoard(timeArr[0], berlinClockBuilder);
        convertMinutesForBoard(timeArr[1], berlinClockBuilder);
        return berlinClockBuilder.toString();
    }

    /**
     * Convert seconds to Berlin Clock Format
     *
     * @param secondStr
     * @param berlinClockBuilder
     */
    private void convertSecondForBoard(String secondStr, StringBuilder berlinClockBuilder) {
        int seconds = Integer.parseInt(secondStr);
        if (seconds % 2 == 0)
            berlinClockBuilder.append(LampColorType.YELLOW.getColorCode());
        else
            berlinClockBuilder.append(LampColorType.OFF.getColorCode());
        berlinClockBuilder.append('\n');
    }

    /**
     * Convert minutes to Berlin Clock Format
     *
     * @param minuteStr
     * @param berlinClockBuilder
     */
    private void convertMinutesForBoard(String minuteStr, StringBuilder berlinClockBuilder) {
        int minutes = Integer.parseInt(minuteStr);
        int noOfOnInFirstRow = minutes / 5;
        int noOfOnInSecRow = minutes % 5;
        for (int i = 1; i <= 11; i++) {
            String colorBlock = ((i % 3 == 0) ? LampColorType.RED.getColorCode() : LampColorType.YELLOW.getColorCode());
            berlinClockBuilder.append(noOfOnInFirstRow-- > 0 ? colorBlock : LampColorType.OFF.getColorCode());
        }
        berlinClockBuilder.append('\n');
        for (int i = 1; i <= 4; i++) {
            berlinClockBuilder.append(noOfOnInSecRow-- > 0 ? LampColorType.YELLOW.getColorCode() : LampColorType.OFF.getColorCode());
        }
    }

    /**
     * Convert hours to Berlin Clock Format
     *
     * @param hourStr
     * @param berlinClockBuilder
     */
    private void convertHoursForBoard(String hourStr, StringBuilder berlinClockBuilder) {
        int hours = Integer.parseInt(hourStr);
        int noOfRedInFirstRow = hours / 5;
        int noOfRedInSecRow = hours % 5;
        for (int i = 1; i <= 4; i++) {
            berlinClockBuilder.append(noOfRedInFirstRow-- > 0 ? LampColorType.RED.getColorCode() : LampColorType.OFF.getColorCode());
        }
        berlinClockBuilder.append('\n');
        for (int i = 1; i <= 4; i++) {
            berlinClockBuilder.append(noOfRedInSecRow-- > 0 ? LampColorType.RED.getColorCode() : LampColorType.OFF.getColorCode());
        }
        berlinClockBuilder.append('\n');
    }

    /**
     * function to validate input time
     *
     * @param timeArr
     */
    private void validateTime(String[] timeArr) {
        if (timeArr.length != 3) {
            throw new InvalidTimeException(AppConstants.INVALID_TIME_FORMAT_ERROR);
        }

        int hours = Integer.parseInt(timeArr[0]);
        int minutes = Integer.parseInt(timeArr[1]);
        int seconds = Integer.parseInt(timeArr[2]);

        if (hours < 0 || hours > 24 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) {
            throw new InvalidTimeException(AppConstants.INVALID_TIME_ERROR);
        }
    }
}
