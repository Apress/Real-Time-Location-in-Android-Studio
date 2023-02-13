package com.radefffactory.realtimelocation;

public class LocationConverter {

    public static String getLocationInDegrees(double latitude, double longitude) {
        //latitude
        int latSeconds = (int) Math.round(latitude * 3600);
        int latDegrees = latSeconds / 3600;
        latSeconds = Math.abs(latSeconds % 3600);
        int latMinutes = latSeconds / 60;
        latSeconds %= 60;
        String latDegree = latDegrees >= 0 ? "N" : "S";

        String latZero = "";
        if (Math.abs(latDegrees) < 100) {
            latZero = "0";
        }

        //longitude
        int lonSeconds = (int) Math.round(longitude * 3600);
        int lonDegrees = lonSeconds / 3600;
        lonSeconds = Math.abs(lonSeconds % 3600);
        int lonMinutes = lonSeconds / 60;
        lonSeconds %= 60;
        String lonDegree = lonDegrees >= 0 ? "E" : "W";

        String lonZero = "";
        if (Math.abs(lonDegrees) < 100) {
            lonZero = "0";
        }

        return "Latitude: " + latDegree + latZero + Math.abs(latDegrees) + "° " + latMinutes + "' " + latSeconds + "\""
                + "\nLongitude: " + lonDegree + lonZero + Math.abs(lonDegrees) + "° " + lonMinutes + "' " + lonSeconds + "\"";
    }

}
