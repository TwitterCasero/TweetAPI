package com.twittercasero.tweets.infrastructure.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextExtractorUtil {

    public static List<String> extractStrings(String text, String regex) {

        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            matches.add(matcher.group().substring(1));
        }

        return matches;
    }
}