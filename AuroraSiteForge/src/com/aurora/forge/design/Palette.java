/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.design;

import com.aurora.forge.util.Randomizer;
import java.util.HashMap;
import java.util.Map;

public final class Palette {
    private final String primary;
    private final String secondary;
    private final String accent;
    private final String background;

    public Palette(String primary, String secondary, String accent, String background) {
        this.primary = primary;
        this.secondary = secondary;
        this.accent = accent;
        this.background = background;
    }

    public static Palette random(Randomizer r) {
        return new Palette(r.pickColor(), r.pickColor(), r.pickColor(), r.pickColor());
    }

    public String primaryHex(){ return primary; }
    public String secondaryHex(){ return secondary; }
    public String accentHex(){ return accent; }
    public String backgroundHex(){ return background; }

    public Map<String,String> toMap(){
        Map<String,String> m = new HashMap<>();
        m.put("primary", primary);
        m.put("secondary", secondary);
        m.put("accent", accent);
        m.put("background", background);
        return m;
    }
}

/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
