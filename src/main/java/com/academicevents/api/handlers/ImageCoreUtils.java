package com.academicevents.api.handlers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ImageCoreUtils {

    public static boolean isValidFormat(String base64Image){
        final String JPEG_HEADER = "data:image/jpeg;base64,";
        final String PNG_HEADER = "data:image/png;base64,";
        return base64Image != null &&
                ((base64Image.substring(0, JPEG_HEADER.length()).contains(JPEG_HEADER)) ||
                 (base64Image.substring(0, PNG_HEADER.length()).contains(PNG_HEADER)));
    }

    public static String clampHeader(String base64Image){
        return (isValidFormat(base64Image) ? base64Image.split(",")[1] : null);
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
