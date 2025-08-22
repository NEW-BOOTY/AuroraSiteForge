/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.logging;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class AppLog {
    private static final DateTimeFormatter F = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private AppLog(){}

    private static String ts(){ return ZonedDateTime.now().format(F); }

    public static void banner(String s){ System.out.println("\n=== " + s + " ===\n"); }
    public static void info(String s){ System.out.println("[INFO " + ts() + "] " + s); }
    public static void success(String s){ System.out.println("[OK   " + ts() + "] " + s); }
    public static void warn(String s){ System.err.println("[WARN " + ts() + "] " + s); }
    public static void error(String s){ System.err.println("[ERR  " + ts() + "] " + s); }
    public static void trace(Throwable t){
        if (t==null) return;
        System.err.println("[TRACE " + ts() + "] " + t.toString());
        for (StackTraceElement e : t.getStackTrace()) System.err.println("   at " + e);
        if (t.getCause() != null && t.getCause() != t) trace(t.getCause());
    }
}

/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
