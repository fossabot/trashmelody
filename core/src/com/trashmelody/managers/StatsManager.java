package com.trashmelody.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class StatsManager {

    @Inject
    public StatsManager() {
        getPreferences();
    }

    private Preferences preferences = getPreferences();

    /* Preference current HashMaps
    Key | Value Type
   currentStage | integer   // For setting the current stage
   stage1Score | integer // For stage 1 high score
   stage2Score | integer // For stage 2 high score
   stage3Score | integer // For stage 3 high score
   stage4Score | integer // For stage 4 high score
   stage5Score | integer // For stage 5 high score
   stage6Score | integer // For stage 6 high score
   currentMusicVolume |  float // For user preferred music volume
   currentMusicTrack | String // For user current music track
   recurrentUser | boolean // For checking that user is recurrent user or not
*/

    protected Preferences getPreferences() {
        preferences = Gdx.app.getPreferences("TrashMelody");
        preferences = Gdx.app.getPreferences("TrashMelody");

        // Initialize default data
        if (!getRecurrentUser()) {
            setCurrentStage(1);
            for (int i = 1; i <= 6; i++) {
                String n = String.valueOf(i);
                resetStageScore(n);
            }
            setCurrentMusicVolume(0.3F);
            setCurrentMusicTrack("MUSIC_BG1");
            setRecurrentUser();

            setStageStats("stage1", "perfect", 0);
            setStageStats("stage1", "perfect", 0);
            setStageStats("stage1", "good", 0);
            setStageStats("stage1", "nice", 0);
            setStageStats("stage1", "miss", 0);
            setStageStats("stage1", "combo", 0);
            setStageStats("stage1", "score", 0);
        }
        preferences.flush();
        return preferences;
    }

    public int getCurrentStage() {
        return preferences.getInteger("currentStage");
    }

    public void setCurrentStage(int currentStageID) {
        preferences.putInteger("currentStage", currentStageID);
    }

    public void setCurrentMusicVolume(float volume) {
        preferences.putFloat("currentMusicVolume", volume);
        preferences.flush();
    }

    public float getCurrentMusicVolume() {
        return preferences.getFloat("currentMusicVolume");
    }

    public void setCurrentMusicTrack(String musicTrack) {
        preferences.putString("currentMusicTrack", musicTrack);
        preferences.flush();
    }

    public String getCurrentMusicTrack() {
        return preferences.getString("currentMusicTrack");
    }

    // Methods to set the highscore stage score ---------------------------------------------------
    public void setStageScore(String stageID, int score) {
        if (stageID.startsWith("stage") && stageID.endsWith("Score")) {
            if (getStageScore(stageID) < score) {
                preferences.putInteger(stageID, score);
                preferences.flush();
            }
        }
    }

    public int getStageScore(String stageID) {
        return preferences.getInteger(stageID);
    }

    public int getScore(String beatmapId, String attribute) {
        return preferences.getInteger(beatmapId + attribute);
    }

    private void resetStageScore(String stageID) {
        setStageScore(stageID, 0);
    }

    public void setDevelopmentMode() {
        preferences.putBoolean("developMode", !(getDevelopmentMode()));
    }

    public boolean getDevelopmentMode(){
        return preferences.getBoolean("developMode");
    }

    // Other StatsManager utility methods
    public boolean getRecurrentUser() {
        return preferences.getBoolean("recurrentUser");
    }

    public void setRecurrentUser() {
        preferences.putBoolean("recurrentUser", true);
    }

    public void setStageStats(String stageID,String type,int value){
        stageID += type;
        preferences.putInteger(stageID,value);
        preferences.flush();
    }

    public int getStageStatus(String stageID,String type){
        stageID += type;
        return preferences.getInteger(stageID);
    }

}

