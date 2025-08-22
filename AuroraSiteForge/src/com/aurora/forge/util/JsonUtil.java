/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.util;

import java.util.*;

public final class JsonUtil {
    private JsonUtil(){}

    public static String toPretty(Map<String, ?> map){
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String k = keys.get(i);
            sb.append("  \"").append(k).append("\": ");
            Object v = map.get(k);
            sb.append(prettyValue(v, "  "));
            if (i < keys.size()-1) sb.append(",");
            sb.append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    private static String prettyValue(Object v, String indent){
        if (v == null) return "null";
        if (v instanceof String) return "\"" + v.toString().replace("\"","\\\"") + "\"";
        if (v instanceof Number || v instanceof Boolean) return v.toString();
        if (v instanceof Map<?,?>) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            ((Map<?,?>)v).forEach((k,val) -> {
                sb.append("\"").append(k).append("\":\"").append(String.valueOf(val)).append("\",");
            });
            if (sb.charAt(sb.length()-1)==',') sb.setLength(sb.length()-1);
            sb.append("}");
            return sb.toString();
        }
        return "\"" + v.toString().replace("\"","\\\"") + "\"";
    }
}
 
/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
