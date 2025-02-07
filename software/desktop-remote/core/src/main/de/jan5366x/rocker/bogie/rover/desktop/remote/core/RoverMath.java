package de.jan5366x.rocker.bogie.rover.desktop.remote.core;

import com.badlogic.gdx.math.Vector2;

public class RoverMath {

    public static Tuple2<Vector2, Float> circleFromThreePoints(Vector2 point1, Vector2 point2, Vector2 point3) {
        return circleFromThreePoints(point1.x, point1.y, point2.x, point2.y,  point3.x, point3.y);
    }

    public static Tuple2<Vector2, Float> circleFromThreePoints(float x1, float y1, float x2, float y2, float x3, float y3) {
        float x12 = x1 - x2;
        float x13 = x1 - x3;
        float y12 = y1 - y2;
        float y13 = y1 - y3;
        float y31 = y3 - y1;
        float y21 = y2 - y1;
        float x31 = x3 - x1;
        float x21 = x2 - x1;

        float sx13 = (float)(Math.pow(x1, 2) - Math.pow(x3, 2));
        float sy13 = (float)(Math.pow(y1, 2) - Math.pow(y3, 2));

        float sx21 = (float)(Math.pow(x2, 2) - Math.pow(x1, 2));
        float sy21 = (float)(Math.pow(y2, 2) - Math.pow(y1, 2));

        float f = (sx13 * x12 + sy13 * x12 + sx21 * x13 + sy21 * x13) / (2 * (y31 * x12 - y21 * x13));
        float g = (sx13 * y12 + sy13 * y12 + sx21 * y13 + sy21 * y13) / (2 * (x31 * y12 - x21 * y13));

        float c = -(float)Math.pow(x1, 2) - (float)Math.pow(y1, 2) - 2 * g * x1 - 2 * f * y1;

        float resultX = -g;
        float resultY = -f;

        float radius = (float)Math.sqrt( resultX * resultX + resultY * resultY - c);
        return new Tuple2<>(new Vector2(resultX, resultY), radius);
    }
}
