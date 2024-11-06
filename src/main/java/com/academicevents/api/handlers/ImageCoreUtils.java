package com.academicevents.api.handlers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ImageCoreUtils {

    public static boolean isValidFormat(String base64Image){
        final String JPEG_HEADER = "data:image/jpeg;base64,";
        final String PNG_HEADER = "data:image/png;base64,";
        if(base64Image == null || base64Image.length() < 23){ return false; }
        boolean isHeaderValid = (base64Image.startsWith(JPEG_HEADER)) || (base64Image.startsWith(PNG_HEADER));
        base64Image = clampHeader(base64Image);
        try {
            decodeImage(base64Image);
        } catch (IllegalArgumentException e) { return false; }
        return isHeaderValid;
    }

    public static String clampHeader(String base64Image){
        return (base64Image.split(",")[1]);
    }

    public static byte[] decodeImage(String clampedBase64Image){
        return Base64.getDecoder().decode(clampedBase64Image);
    }

    public static BufferedImage getImage(byte[] imageByte)throws Exception{
        return ImageIO.read(new ByteArrayInputStream(imageByte));
    }

    public static int[] getDimensions(BufferedImage buffImage){
        return new int[]{buffImage.getWidth(), buffImage.getHeight()};
    }

}
