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

    /*
     * o valor máximo que uma imagem pode ter é de 5MiB,
     * aproximadamente 5.24Mb. que convertido para
     * bytes fica: 5242880B.
     */
    private static final long MAX_PFP_IMAGE_SIZE = 5242880;
    private static final int MAX_PFP_WIDTH = 1024;

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

    public static boolean checkUserImage(String base64Image){
        if(!ImageCoreUtils.isValidFormat(base64Image)){ return false; }

        base64Image = ImageCoreUtils.clampHeader(base64Image);
        byte[] decodedImage = ImageCoreUtils.decodeImage(base64Image);

        if(decodedImage.length > MAX_PFP_IMAGE_SIZE){ return false; }

        int imageWidth = 0;
        int imageHeight = 0;
        try {
            if(ImageCoreUtils.getImage(decodedImage) == null){ return false; }
            imageWidth = ImageCoreUtils.getDimensions(ImageCoreUtils.getImage(decodedImage))[0];
            imageHeight = ImageCoreUtils.getDimensions(ImageCoreUtils.getImage(decodedImage))[1];
        } catch (Exception e) { throw new RuntimeException(e); }

        if(imageWidth != imageHeight){ return false; }
        return imageWidth < MAX_PFP_WIDTH;
    }
}
