package org.hammerhead226.masterfrcscouter.model.RecycleRush;

import java.io.Serializable;

/**
 * Created by Adi on 7/14/2015.
 */
public class RRStack implements Comparable<RRStack>, Serializable {
    private static final long serialVersionUID = 201510242305L; /* Date Stamp: 2015-10-24 - 23:05 */

    public int height;
    public boolean canOnTopWithLitter;
    public boolean canOnTop;

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
        String s = "";
        s += height + "-";
        s += canOnTopWithLitter + "-";
        s += canOnTop;
        s += ";";
        return s;
    }

    public int compareTo(RRStack other) {
        if (this.calculateStackScore() > other.calculateStackScore()) { return 1; }
        else if (this.calculateStackScore() < other.calculateStackScore()) { return -1; }
        else { return 0; }
    }
}
