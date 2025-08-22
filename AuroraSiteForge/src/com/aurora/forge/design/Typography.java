/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */

package com.aurora.forge.design;

import com.aurora.forge.util.Randomizer;
import java.util.HashMap;
import java.util.Map;

public final class Typography {
    private final String heading;
    private final String body;
    private final String mono;

    public Typography(String heading, String body, String mono){
        this.heading = heading; this.body = body; this.mono = mono;
    }

    public static Typography random(Randomizer r){
        String[] headings = {"Poppins","Montserrat","Manrope"};
        String[] bodies = {"Inter","Sora","Roboto"};
        String[] monos = {"JetBrains Mono","Fira Code","IBM Plex Mono"};
        return new Typography(
                headings[Math.abs(r.nextInt(headings.length))],
                bodies[Math.abs(r.nextInt(bodies.length))],
                monos[Math.abs(r.nextInt(monos.length))]
        );
    }

    public String headingFont(){ return heading; }
    public String fontFamily(){ return body; }
    public String monoFont(){ return mono; }

    public Map<String,String> toMap(){
        Map<String,String> m = new HashMap<>();
        m.put("heading", heading); m.put("body", body); m.put("mono", mono);
        return m;
    }
}

/*
 * Copyright © 2025 Devin B. Royal.
 * All Rights Reserved.
 */
