package org.hammerhead226.masterfrcscouter.model.Stronghold.GameComponents;

import android.os.Parcel;

import com.hannesdorfmann.parcelableplease.ParcelBagger;

/**
 * Created by AdiSai on 1/12/16.
 */
public class GameComponentBagger implements ParcelBagger<GameComponent> {
    @Override public void write(GameComponent value, Parcel out, int flags) {
        out.writeSerializable(value.getClass());
        out.writeString(value.getGameMode());
        out.writeInt(value.getScore());
    }

    @Override public GameComponent read(Parcel in) {
        Class<? extends GameComponent> clazz = (Class<? extends GameComponent>)in.readSerializable();
        String gameMode = in.readString();
        int score = in.readInt();
        try {
            return clazz.getConstructor(String.class, int.class).newInstance(gameMode, score);
        }
        catch(Exception e) { e.printStackTrace(); }
        return null;
    }
}
