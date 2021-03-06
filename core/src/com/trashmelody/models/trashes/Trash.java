package com.trashmelody.models.trashes;

public class Trash {
    private String name, description;
    private TrashType type;
    private String texturePath;
    private String rawTexturePath;
    private int unlockAt;

    public Trash(String name,
                 String description,
                 String texturePath,
                 String rawTexturePath,
                 TrashType type,
                 int unlockAt) {
        this.name = name;
        this.description = description;
        this.texturePath = texturePath;
        this.type = type;
        this.unlockAt = unlockAt;
        this.rawTexturePath = rawTexturePath;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public TrashType getType() {
        return this.type;
    }

    public boolean isDangerous() {
        return this.type == TrashType.Dangerous;
    }

    public boolean isWet() {
        return this.type == TrashType.Wet;
    }

    public boolean isRecycle() {
        return this.type == TrashType.Recycle;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public String getRawTexturePath() {
        return rawTexturePath;
    }

    public int getUnlockAt() {
        return unlockAt;
    }
}
