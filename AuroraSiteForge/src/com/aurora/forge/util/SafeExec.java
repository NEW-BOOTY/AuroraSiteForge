/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.util;

import com.aurora.forge.logging.AppLog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class SafeExec {
    private SafeExec(){}

    public static void touch(Path p){
        try {
            if (p==null) return;
            if (Files.exists(p)) return;
            Files.createDirectories(p.getParent());
            Files.createFile(p);
        } catch (IOException e) {
            AppLog.warn("touch failed: " + e.getMessage());
        }
    }
}
 
/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
