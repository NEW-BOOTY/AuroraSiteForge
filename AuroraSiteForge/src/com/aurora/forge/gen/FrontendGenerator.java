/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.gen;

import java.nio.file.Path;
import java.util.Map;
import java.io.IOException;

public interface FrontendGenerator {
    void generate(Path outDir, Map<String,Object> global) throws IOException;
}

/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
