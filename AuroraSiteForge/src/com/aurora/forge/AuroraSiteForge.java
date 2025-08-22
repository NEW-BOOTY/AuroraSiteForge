/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge;

import com.aurora.forge.design.Palette;
import com.aurora.forge.design.Typography;
import com.aurora.forge.gen.FrontendGenerator;
import com.aurora.forge.gen.ReactGenerator;
import com.aurora.forge.gen.AngularGenerator;
import com.aurora.forge.gen.VueGenerator;
import com.aurora.forge.logging.AppLog;
import com.aurora.forge.util.JsonUtil;
import com.aurora.forge.util.Randomizer;
import com.aurora.forge.util.SafeExec;
import com.aurora.forge.fs.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

public final class AuroraSiteForge {

    private AuroraSiteForge() {}

    public static void main(String[] args) {
        AppLog.banner("AuroraSiteForge — Simplified Generator");

        Map<String, String> cli = parseArgs(args);
        String outArg = cli.getOrDefault("out", "output");
        String brand = cli.getOrDefault("brand", "Aurora Crafted");
        String slug = cli.getOrDefault("slug", brand.replaceAll("\\s+","-").toLowerCase(Locale.ROOT));
        String framework = cli.getOrDefault("framework", "react").toLowerCase(Locale.ROOT);
        String backendPort = cli.getOrDefault("port", "8080");
        long seed = cli.containsKey("seed") ? parseLongOrDefault(cli.get("seed"), 1337L) : 1337L;

        Path outDir = Paths.get(outArg).toAbsolutePath().normalize();
        AppLog.info("Output dir: " + outDir);
        AppLog.info("Brand: " + brand + " slug=" + slug);
        AppLog.info("Framework: " + framework);
        AppLog.info("Backend port: " + backendPort);
        AppLog.info("Seed: " + seed);

        try {
            FileUtil.ensureDir(outDir);

            Randomizer rand = new Randomizer(seed);
            Palette palette = Palette.random(rand);
            Typography typography = Typography.random(rand);

            Map<String,Object> global = new LinkedHashMap<>();
            global.put("brand", brand);
            global.put("slug", slug);
            global.put("seed", seed);
            global.put("timestampIso", Instant.now().toString());
            global.put("palette", palette.toMap());
            global.put("typography", typography.toMap());
            global.put("backendPort", backendPort);

            FileUtil.writeUtf8(outDir.resolve("design-manifest.json"), JsonUtil.toPretty(global));

            Path feRoot = outDir.resolve("frontends");
            FileUtil.ensureDir(feRoot);

            // Choose generator
            FrontendGenerator gen;
            switch (framework) {
                case "angular":
                    gen = new AngularGenerator(rand, palette, typography, backendPort);
                    break;
                case "vue":
                    gen = new VueGenerator(rand, palette, typography, backendPort);
                    break;
                default:
                    gen = new ReactGenerator(rand, palette, typography, backendPort);
            }

            Path target = feRoot.resolve(framework + "-site");
            gen.generate(target, global);

            // optional marker
            SafeExec.touch(target.resolve(".aurora-ok"));

            AppLog.success("Generation complete: " + target.toAbsolutePath());
            AppLog.info("Next steps: Open the generated directory and serve statically or inspect files.");
        } catch (Throwable t) {
            AppLog.error("Fatal: " + t.getMessage());
            AppLog.trace(t);
            System.exit(1);
        }
    }

    private static Map<String,String> parseArgs(String[] args){
        Map<String,String> m = new HashMap<>();
        for (String a : args) {
            if (a == null) continue;
            int i = a.indexOf('=');
            if (i>0) m.put(a.substring(0,i).trim().toLowerCase(Locale.ROOT), a.substring(i+1).trim());
        }
        return m;
    }

    private static long parseLongOrDefault(String s, long def) {
        try { return Long.parseLong(s); } catch (Exception e) { return def; }
    }
}

/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
