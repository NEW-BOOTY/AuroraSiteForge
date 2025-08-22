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

public final class ReactGenerator implements FrontendGenerator {

    private final Randomizer rand;
    private final Palette palette;
    private final Typography typography;
    private final String backendPort;

    public ReactGenerator(Randomizer rand, Palette palette, Typography typography, String backendPort) {
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
            .hero{ max-width:900px; margin:0 auto; }
            """.formatted(palette.primaryHex(), palette.secondaryHex(), palette.backgroundHex(), typography.fontFamily());
        FileUtil.writeUtf8(outDir.resolve("styles.css"), css);

        String html = """
            <!doctype html>
            <html lang="en">
            <head>
              <meta charset="utf-8"/>
              <meta name="viewport" content="width=device-width,initial-scale=1"/>
              <title>React — %s</title>
              <link rel="stylesheet" href="./styles.css"/>
            </head>
            <body>
              <div id="root"></div>
              <script src="./index.js"></script>
            </body>
            </html>
            """.formatted(brand);
        FileUtil.writeUtf8(outDir.resolve("index.html"), html);

        String js = """
            const App = () => React.createElement("div", {className:"hero"}, [
              React.createElement("h1", {key:1}, "%s"),
              React.createElement("p", {key:2}, "Seed: %s"),
              React.createElement("p", {key:3}, "API: http://localhost:%s/api")
            ]);
            ReactDOM.createRoot(document.getElementById("root")).render(React.createElement(App));
            """.formatted(brand, seed, backendPort);
        // Use minimal CDN-based index.js that expects React + ReactDOM to be loaded by the user if desired.
        // To keep everything local and simple we include small wrapper that uses UMD from CDN
        String loader = """
            // Minimal loader to add React/ReactDOM UMD from CDN in the browser
            (function(){
              if (!window.React || !window.ReactDOM) {
                var r = document.createElement('script'); r.src='https://unpkg.com/react@18/umd/react.production.min.js'; r.crossOrigin='';
                var d = document.createElement('script'); d.src='https://unpkg.com/react-dom@18/umd/react-dom.production.min.js'; d.crossOrigin='';
                document.head.appendChild(r); document.head.appendChild(d);
                r.onload = function(){ setTimeout(function(){ %s }, 50); };
              } else { %s }
            })();
            """.formatted(js.replace("\n","\\n"), js.replace("\n","\\n"));
        FileUtil.writeUtf8(outDir.resolve("index.js"), loader);

        // marker
        Files.createFile(outDir.resolve(".aurora-ok"));
        AppLog.info("React generated at " + outDir.toAbsolutePath());
    }
}

/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
