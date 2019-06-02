package com.twq.preparser;

public class WebLogPreParser {

    public static PreParsedLog parse(String line){
        if (line.startsWith("#")){
            return  null;
        }else {
            PreParsedLog preParsedLog = new PreParsedLog();
            String[] temps = line.split(" ");
            preParsedLog.setServerTime(temps[0]+" "+temps[1]);
            preParsedLog.setServerIp(temps[2]);
            preParsedLog.setMethod(temps[3]);
            preParsedLog.setUriStem(temps[4]);
            String queryString = temps[5];
            preParsedLog.setQueryString(queryString);
            String[] queryStringTemps = queryString.split("&");
            String command = queryStringTemps[1].split("=")[1];
            preParsedLog.setCommand(command);
            String profileIdStr = queryStringTemps[2].split("=")[1];
            preParsedLog.setProfileId(getProfileId(profileIdStr));
            preParsedLog.setServerPort(Integer.parseInt(temps[6]));
            preParsedLog.setClientIp(temps[8]);
            preParsedLog.setUserAgent(temps[9].replace("+"," "));
            String date = preParsedLog.getServerTime().replace("-","");
            preParsedLog.setDay(Integer.parseInt(date.substring(0,8)));
            preParsedLog.setMonth(Integer.parseInt(date.substring(0,6)));
            preParsedLog.setYear(Integer.parseInt(date.substring(0,4)));

            return preParsedLog;
        }


    }

    public static int getProfileId(String profileIdStr){
        return Integer.valueOf(profileIdStr.substring(profileIdStr.indexOf("-")+1));
    }
}
