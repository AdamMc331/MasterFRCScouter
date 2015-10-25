package com.adithyasairam.masterfrcscouter.Backend.Scouting.RecycleRush;

import java.io.Serializable;

/**
 * Created by Adi on 7/14/2015.
 */
public class RRStack implements Comparable<RRStack>, Serializable {
    private static final long serialVersionUID = 201510242305L; /* Date Stamp: 2015-10-24 - 23:05 */

    public int height;
    public boolean canOnTop;
    public boolean canOnTopWithLitter;

    public RRStack(int h, boolean cOTWL, boolean cOT) {
        height = h;
        canOnTopWithLitter = cOTWL;
        canOnTop = cOT;
    }

    public int calculateStackScore() {
        int score = 0;
        score += height * 2;
        if (canOnTopWithLitter) { score += ((height * 4) + 6); }
        else if (canOnTop) { score += (height * 4); }
        return score;
    }

    public String toString() {
        boolean multiline = false;
        String s = "{ ";
        if (multiline) {
            s += " Height: " + height + "\n";
            s += " Has a Can on Top with Litter: " + canOnTopWithLitter + "\n";
            s += " Has a Can on Top: " + canOnTop + "\n";
            s += " Score: " + calculateStackScore() + "\n";
            s += "}";
        } else {
            s += " Height: " + height;
            s += " Has a Can on Top with Litter: " + canOnTopWithLitter;
            s += " Has a Can on Top: " + canOnTop;
            s += " Score: " + calculateStackScore();
            s += "}";
        }
        return s;
    }

    public int compareTo(RRStack other) {
        if (this.calculateStackScore() > other.calculateStackScore()) { return 1; }
        else if (this.calculateStackScore() < other.calculateStackScore()) { return -1; }
        else { return 0; }
    }
}
