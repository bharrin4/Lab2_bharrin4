package com.a40333.bharrin4.lab2_bharrin4;

import java.io.Serializable;

/**
 * Created by User on 3/1/2017.
 */

public class Team implements Serializable {
    String gameTime;
    String gameLocation;
    String opposingName;
    String opposingLogo;
    String opposingMascot;
    String opposingRec;
    String scoreID;
    String finalString;
    String ndName;
    String ndMascot;
    String ndRec;
    String ndLogo;
    String camera;
    String date;

    //... define all the strings that you need to fill all the TextViews  of activity_detail.

    public Team (String [] gameStats) {
        setOpposingName(gameStats[3]);
        setOpposingLogo(gameStats[2]);
        setGameTime(gameStats[0]);
        setGameLocation(gameStats[1]);
        setOpposingMascot(gameStats[4]);
        setOpposingRec(gameStats[5]);
        setScoreID(gameStats[6]);
        setFinalString(gameStats[7]);
        setNdName(gameStats[8]);
        setNdMascot(gameStats[9]);
        setNdRec(gameStats[10]);
        setNdLogo(gameStats[11]);
        setCamera(gameStats[12]);
        setDate(gameStats[13]);
    }

    public void setOpposingName(String opposing_name) {
        this.opposingName = opposing_name;
    }

    public String getOpposingName() {
        return this.opposingName;
    }

    public void setOpposingLogo(String opposing_logo) {
        this.opposingLogo = opposing_logo;
    }

    public String getOpposingLogo() {
        return this.opposingLogo;
    }

    public void setOpposingMascot(String opposing_mascot) {
        this.opposingName = opposing_mascot;
    }

    public String getOpposingMascot() {
        return this.opposingMascot;
    }

    public void setOpposingRec(String opposing_rec) {
        this.opposingRec = opposing_rec;
    }

    public String getOpposingRec() {
        return this.opposingRec;
    }

    public void setGameTime(String game_time) {
        this.gameTime = game_time;
    }

    public String getGameTime() {
        return this.gameTime;
    }

    public void setGameLocation(String game_location) {
        this.gameLocation = game_location;
    }

    public String getGameLocation() {
        return this.gameLocation;
    }

    public void setScoreID(String score_id) {
        this.scoreID = score_id;
    }

    public String getScoreID() {
        return this.scoreID;
    }

    public void setFinalString(String final_string) {
        this.finalString = final_string;
    }

    public String getFinalString() {
        return this.finalString;
    }

    public void setNdName(String nd_name) {
        this.ndName = nd_name;
    }

    public String getNdName() {
        return this.ndName;
    }

    public void setNdMascot(String nd_mascot) {
        this.ndName = nd_mascot;
    }

    public String getNdMascot() {
        return this.ndMascot;
    }

    public void setNdRec(String nd_rec) {
        this.ndRec = nd_rec;
    }

    public String getNdRec() {
        return this.ndRec;
    }

    public void setNdLogo(String nd_logo) {
        this.ndLogo = nd_logo;
    }

    public String getNdLogo() {
        return this.ndLogo;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCamera() {
        return this.camera;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }
}
