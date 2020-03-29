package com.mrwalker.firstgame.Converter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mrwalker.firstgame.Config.Config;
import com.mrwalker.firstgame.auxiliary.Position2;
import com.mrwalker.firstgame.auxiliary.Size2;

public class Converter {
    private static final String TAG = Converter.class.getSimpleName();

    private static final int tileWidth = Config.tileWidth, tileHeight = Config.tileHeight;
    private static Size2 sizeIsometricMap = null;

    public static void setMapIsometricSize(Size2 isometricSize){
        sizeIsometricMap = isometricSize;
    }

    public static Position2 cartesianToIsometric(Position2 position){
        Vector2 point = new Vector2(position.getX(), position.getY());
        point.x /= tileWidth;
        point.y = (point.y - tileHeight / 2f) / tileHeight + point.x;
        point.x -= point.y - point.x;
        if (sizeIsometricMap != null){
            return new Position2(point.x * tileWidth / 2f, sizeIsometricMap.getHeight() - point.y * tileHeight);
        } else {
            Gdx.app.error(TAG, "Size of Isometric map has not been set! Y-axis reverse!");
            return new Position2(point.x * tileWidth / 2f, point.y * tileHeight);
        }
    }

    public static Position2 isometricToCartesian(Position2 position){
        Vector2 point = new Vector2(position.getX(), position.getY());
        point.x = position.getY() + position.getX();
        point.y = (position.getY() - position.getX()) / 2f + 16;
        return new Position2(point.x, point.y);
    }
}

