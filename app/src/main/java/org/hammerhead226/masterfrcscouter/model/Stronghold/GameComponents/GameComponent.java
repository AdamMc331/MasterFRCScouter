package org.hammerhead226.masterfrcscouter.model.Stronghold.GameComponents;

import java.io.Serializable;

/**
 * Created by AdiSai on 1/10/16.
 */
public abstract class GameComponent implements Serializable {
    private static final long serialVersionUID = 20160101101900L; /* Date Stamp: 2016-01-10 - 19:00 */
    private String gameMode;
    private int score;

    public GameComponent(String gameMode) {
        this.gameMode = gameMode;
        score = calculateScore();
    }

    public GameComponent(String gameMode, int score) {
        this.gameMode = gameMode;
        this.score = score;
    }

    public abstract int calculateScore();

    public int getScore() { return score; }

    public String getGameMode() { return gameMode; }

    @Override public abstract String toString();
}
