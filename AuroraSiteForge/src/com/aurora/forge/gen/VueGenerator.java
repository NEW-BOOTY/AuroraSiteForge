/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.gen;

import com.aurora.forge.design.Palette;
import com.aurora.forge.design.Typography;
import com.aurora.forge.util.Randomizer;
import com.aurora.forge.fs.FileUtil;
import com.aurora.forge.logging.AppLog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public final class VueGenerator implements FrontendGenerator {

    private final Randomizer rand;
    private final Palette palette;
    private final Typography typography;
    private final String backendPort;

    public VueGenerator(Randomizer rand, Palette palette, Typography typography, String backendPort) {
        this.rand = rand; this.palette = palette; this.typography = typography; this.backendPort = backendPort;
    }

    @Override
    public void generate(Path outDir, Map<String,Object> global) throws IOException {
        FileUtil.ensureDir(outDir);
        String brand = String.valueOf(global.getOrDefault("brand","Aurora"));
        String seed = String.valueOf(global.getOrDefault("seed",""));

        String css = """
            :root{ --primary: %s; --secondary: %s; --bg: %s; }
            body{ margin:0; font-family: %s; background:var(--bg); color:var(--secondary); padding:2rem; }
            """.formatted(palette.primaryHex(), palette.secondaryHex(), palette.backgroundHex(), typography.fontFamily());
        FileUtil.writeUtf8(outDir.resolve("styles.css"), css);

        String html = """
            <!doctype html>
            <html lang="en">
            <head>
              <meta charset="utf-8"/>
              <meta name="viewport" content="width=device-width,initial-scale=1"/>
              <title>Vue — %s</title>
              <link rel="stylesheet" href="./styles.css"/>
            </head>
            <body>
              <div id="app"></div>
              <script src="https://unpkg.com/vue@3/dist/vue.global.prod.js"></script>
              <script src="./main.js"></script>
            </body>
            </html>
            """.formatted(brand);
        FileUtil.writeUtf8(outDir.resolve("index.html"), html);

        String mainJs = """
            const App = { data(){ return { title: "%s", seed: "%s", api: "http://localhost:%s/api" } },
              template: `<div class="hero"><h1>{{ title }}</h1><p>Seed: {{ seed }}</p><p>API: {{ api }}</p></div>` };
            Vue.createApp(App).mount('#app');
            """.formatted(brand, seed, backendPort);
        FileUtil.writeUtf8(outDir.resolve("main.js"), mainJs);

        Files.createFile(outDir.resolve(".aurora-ok"));
        AppLog.info("Vue generated at " + outDir.toAbsolutePath());
    }
}

/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
