/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.fs;

import com.aurora.forge.logging.AppLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Objects;

public final class FileUtil {
    private FileUtil(){}

    public static void ensureDir(Path p) throws IOException {
        Objects.requireNonNull(p,"path");
        if (Files.exists(p) && Files.isDirectory(p)) return;
        Files.createDirectories(p);
    }

    public static void writeUtf8(Path file, String content) throws IOException {
        Objects.requireNonNull(file,"file");
        Objects.requireNonNull(content,"content");
        ensureDir(file.getParent());
        Files.write(file, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        AppLog.info("Wrote: " + file.toAbsolutePath());
    }
}
 
/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
