package kr.ac.baekseok.moodio;

import android.app.Application;

import java.util.ArrayList;

public class MoodieApp extends Application {
    private ArrayList<DiaryListActivity.DiaryEntry> diaries;

    private String userId;

    public ArrayList<DiaryListActivity.DiaryEntry> getDiaries() {
        return diaries;
    }

    public void setDiaries(ArrayList<DiaryListActivity.DiaryEntry> diaries) {
        this.diaries = diaries;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
}