package com.example.kinopoisk.logic.auxiliaryClasses;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class AuxiliaryMethods {
    public static boolean areMultipartFilesEmpty(MultipartFile[] files){
        return files.length == 0 || Arrays.stream(files).allMatch(MultipartFile::isEmpty);
    }

    public static String createRedirectionToPreviousPage(HttpServletRequest request){
        String refer = request.getHeader("Referer");
        return String.format("redirect:%s",refer);
    }
}
