package com.trashmelody.components;

import com.badlogic.ashley.core.Component;

public class HitObjectComponent implements Component {
    public int leftKey, rightKey, upKey, castKey;
    public float health;

    public HitObjectComponent(int leftKey, int rightKey, int upKey, int castKey, float health) {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.castKey = castKey;
        this.health = health;
    }
}
