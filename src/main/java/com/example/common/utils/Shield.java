package com.example.common.utils;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Shield {

    public static String blindStr(String value) {
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        return policy.sanitize(value)
                .replace("&#34;", "'")
                .replace("&#43;", "+")
                .replace("&#39;", "'")
                .replace("&#61;", "=")
                .replace("&amp;", " & ")
                .replace("&#64;", "@");
    }

    public static Long blindLong(Long value) {
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        return Long.valueOf(policy.sanitize(String.valueOf(value)));
    }

    public static UUID blindUUID(UUID value) {
        return UUID.fromString(blindStr(String.valueOf(value)));
    }

}
