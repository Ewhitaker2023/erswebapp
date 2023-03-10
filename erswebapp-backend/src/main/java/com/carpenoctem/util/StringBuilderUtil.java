package com.carpenoctem.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringBuilderUtil {

    private StringBuilderUtil() {
        super();
    }

    public static StringBuilder build(InputStream is) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())));

        int c = 0;
        while ((c = reader.read()) != -1) {
            textBuilder.append((char) c);
        }

        return textBuilder;
    }
}
