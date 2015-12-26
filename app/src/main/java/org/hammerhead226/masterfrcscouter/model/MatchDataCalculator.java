package org.hammerhead226.masterfrcscouter.model;

import java.io.Serializable;

/**
 * Created by Adi on 12/18/2015.
 */
public abstract class MatchDataCalculator<M extends Match> implements Comparable<M>, Serializable {
    private static final long serialVersionUID = 201512252000L; /* Date Stamp: 2015-12-25 - 20:00 */
    public M match;

    public MatchDataCalculator(M match) { this.match = match; }

    public abstract String[] getHeaders();
    public abstract String[] getData();
    public abstract int calculateThisRobotsAproxAutonScore();
    public abstract int calculateThisRobotsAproxTeleopScore();
    public abstract int calculateThisRobotsAproxCOOPScore();
    public abstract int calculateThisRobotsAproxTotalScore();
    public abstract int compareTo(M other);
}
