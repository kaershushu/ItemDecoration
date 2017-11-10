package com.example.administrator.itemdecoration.bean;

/**
 * Created by lengwei on 2017/11/9.
 *
 * @Description
 */
public class GroupInfo {

    private String mGroupId;
    private String mTitle;
    private int mPosition;

    public String getGroupId() {
        return mGroupId;
    }

    public void setGroupId(String groupId) {
        mGroupId = groupId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public boolean isFirstPosition(){
        return mPosition == 0;
    }
}
