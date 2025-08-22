/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public final class Randomizer {
    private final Random rng;
    private static final String[] COLORS = {
        "#0f172a","#0b1220","#0b3a2e","#07203c","#111827",
        "#6ee7ff","#a78bfa","#f472b6","#60a5fa","#34d399",
        "#ffb86c","#f59e0b","#ef4444","#10b981","#7c3aed"
    };

    public Randomizer(long seed){
        SecureRandom sr = new SecureRandom();
        long mix = seed ^ sr.nextLong();
        this.rng = new Random(mix);
    }

    public int nextInt(int bound){ if (bound<=0) return 0; return rng.nextInt(bound); }
    public String pickColor(){ return COLORS[nextInt(COLORS.length)]; }
    public String uuid(){ return UUID.randomUUID().toString(); }
}
 
/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
