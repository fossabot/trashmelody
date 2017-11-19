package com.trashmelody.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Music;
import com.trashmelody.constants.Constants;
import com.trashmelody.entities.HitObjectEntity;
import com.trashmelody.models.Score;
import io.vavr.collection.Queue;

public class ScanLineComponent implements Component {
    public float delay = Constants.PRE_DISPATCH_TIME;
    public float velocity;
    public float elapsedTime = -delay;
    public Score score = new Score();
    public Music music;
    public State state = State.Ready;
    public Queue<HitObjectEntity> activeHitObjects = Queue.empty();

    public enum State {
        Ready, Playing, Pause, Stop
    }

    public ScanLineComponent(Music music, float velocity) {
        this.music = music;
        this.velocity = velocity;
    }
}
