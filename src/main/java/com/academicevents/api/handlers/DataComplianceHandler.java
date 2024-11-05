package com.academicevents.api.handlers;

import java.util.*;

public class DataComplianceHandler {
    private static final int USER_NAME_MAX_LENGTH = 100;  //this number is defined in database.
    private static final Set<Character> ALLOWED_CHARS = new HashSet<>(Arrays.asList(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Á',
            'Â', 'À', 'Ã', 'Ä', 'É', 'Ê', 'È', 'Í', 'Ì',
            'Ó', 'Ô', 'Ò', 'Õ', 'Ö', 'Ú', 'Ù', 'Ü', 'Ç', ' '));

    public static boolean checkUserName(String userName){
        if(userName.length() > USER_NAME_MAX_LENGTH){ return false; }
        for(char c : userName.toUpperCase().toCharArray()){ if(!ALLOWED_CHARS.contains(c)){ return false; } }
        return true;
    }

    public static boolean checkCpf(String userCpf){
        return userCpf.replaceAll("\\D", "").length() == 11;
    }

    public static boolean checkPassword(String unhashedPassword) {
        if(unhashedPassword.isBlank()) { return false; }
        if(unhashedPassword.length() <= 10){ return false; }
        return !unhashedPassword.contains(" ");
    }
}
