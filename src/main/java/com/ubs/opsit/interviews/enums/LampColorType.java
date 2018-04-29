package com.ubs.opsit.interviews.enums;

/**
 * Enum for Color type of Berlin Clock Lamp
 */
public enum LampColorType {

    OFF("O"), YELLOW("Y"), RED("R");

    private String colorCode;

    LampColorType(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }
}
