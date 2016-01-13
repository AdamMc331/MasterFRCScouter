package org.hammerhead226.masterfrcscouter.model.Stronghold.GameComponents;

import android.os.Parcel;

import com.hannesdorfmann.parcelableplease.ParcelBagger;

/**
 * Created by AdiSai on 1/12/16.
 */
public class GameComponentArrayBagger implements ParcelBagger<GameComponent[]> {
    @Override public void write(GameComponent[] value, Parcel out, int flags) {
        for (GameComponent gameComponent : value) {
            out.writeSerializable(gameComponent.getClass());
            out.writeString(gameComponent.getGameMode());
            out.writeInt(gameComponent.getScore());
        }

    }

    @Override public GameComponent[] read(Parcel in) {
        GameComponent[] gameComponents = new GameComponent[in.dataSize() / 3];
        for (int i = 0; i < gameComponents.length; i++) {
            Class<? extends GameComponent> clazz = (Class<? extends GameComponent>)in.readSerializable();
            String gameMode = in.readString();
            int score = in.readInt();
            try {
                gameComponents[i] =  clazz.getConstructor(String.class, int.class).newInstance(gameMode, score);
            }
            catch(Exception e) { e.printStackTrace(); }
        }
        return gameComponents;
    }
}
