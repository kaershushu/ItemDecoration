package com.example.administrator.itemdecoration.callback;

import com.example.administrator.itemdecoration.bean.GroupInfo;

/**
 * Created by lengwei on 2017/11/11.
 *
 * @Description
 */
public class GroupImpl implements GroupCallback {

    @Override
    public GroupInfo group(int position) {
        int index = position % 5;
        int groupId = position / 5;
        GroupInfo info = new GroupInfo();
        info.setGroupSize(5);
        info.setGroupId(groupId + "");
        info.setPosition(index);
        info.setTitle(groupId + "");
        return info;
    }
}
