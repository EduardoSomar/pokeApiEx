package com.example.apipokee.modelos;

public class VersionGroupDetail {
    private long levelLearnedAt;
    private Species moveLearnMethod;
    private Species versionGroup;

    public long getLevelLearnedAt() { return levelLearnedAt; }
    public void setLevelLearnedAt(long value) { this.levelLearnedAt = value; }

    public Species getMoveLearnMethod() { return moveLearnMethod; }
    public void setMoveLearnMethod(Species value) { this.moveLearnMethod = value; }

    public Species getVersionGroup() { return versionGroup; }
    public void setVersionGroup(Species value) { this.versionGroup = value; }
}
