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
    private int groupSize;

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

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

    public boolean isGroupFirstPosition(){
        return mPosition == 0;
    }

    public boolean isGroupLastPosition(){
        return mPosition == (groupSize -1) && mPosition >= 0;
    }
}
