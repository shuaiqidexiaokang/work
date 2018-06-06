/**
 * Copyright (C) 2016-2017 Hangzhou xjhh Co. Ltd.
 * All right reserved.
 *
 * @author: Simon.lee
 * date: 2017-04-14 17:22
 */
package com.xjhh.framework.utils;

import com.xjhh.framework.base.pojo.PTreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Easy UI TreeHelper
 */
public class TreeHelper {
    /**
     * 将角色封装成树开始
     *
     * @param list
     * @param pid  父id
     */
    public static List<Map<String, Object>> FnCreateTree(List<PTreeNode> list, String pid) {
        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = null;
            PTreeNode t = list.get(i);
            if (!StrUtil.isBlankOrNull(pid)) {
                map = new HashMap<String, Object>(16);
                map.put("id", t.getId());
                map.put("text", t.getText());
                map.put("level", t.getLevel());
                map.put("pid", t.getPid());
                map.put("state", t.getState());
                map.put("islastlevel", t.isLastLevel());
                map.put("isLeaf", t.isLeaf());
                if (t.getAttributes() != null && t.getAttributes().size() > 0) {
                    map.put("attributes", t.getAttributes());
                }
            } else if (StrUtil.isNull(pid) || StrUtil.isBlank(pid.trim())) {
                if (StrUtil.isNull(t.getPid()) || StrUtil.isBlank(t.getPid())) {
                    map = new HashMap<String, Object>(16);
                    map.put("id", t.getId());
                    map.put("text", t.getText());
                    map.put("level", t.getLevel());
                    map.put("pid", t.getPid());
                    map.put("state", t.getState());
                    map.put("islastlevel", t.isLastLevel());
                    map.put("isLeaf", t.isLeaf());
                    if (t.getAttributes() != null && t.getAttributes().size() > 0) {
                        map.put("attributes", t.getAttributes());
                    }
                    map.put("children", FnCreateChildren(list, t.getId()));
                }

            } else if (t.getId() != null && t.getId().trim().equals(pid.trim())) {
                map = new HashMap<String, Object>(16);
                map.put("id", t.getId());
                map.put("text", t.getText());
                map.put("level", t.getLevel());
                map.put("pid", t.getPid());
                map.put("state", t.getState());
                map.put("islastlevel", t.isLastLevel());
                map.put("isLeaf", t.isLeaf());
                if (t.getAttributes() != null && t.getAttributes().size() > 0) {
                    map.put("attributes", t.getAttributes());
                }
                map.put("children", FnCreateChildren(list, t.getId()));
            }
            if (map != null) {
                treeList.add(map);
            }
        }
        return treeList;
    }

    /**
     * 递归生成子结点
     *
     * @param list 列表
     * @param pid  父ID
     * @return
     */
    private static Object FnCreateChildren(List<PTreeNode> list, String pid) {
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < list.size(); j++) {
            Map<String, Object> map = null;
            PTreeNode treeChild = list.get(j);
            if (treeChild.getPid() != null) {
                if (treeChild.getPid().trim().equals(pid.trim())) {
                    map = new HashMap<String, Object>(16);
                    map.put("id", treeChild.getId());
                    map.put("text", treeChild.getText());
                    map.put("level", treeChild.getLevel());
                    map.put("pid", treeChild.getPid());
                    map.put("islastlevel", treeChild.isLastLevel());
                    map.put("isLeaf", treeChild.isLeaf());
                    ////map.Add("state", treeChild.state);
                    if (treeChild.getAttributes() != null && treeChild.getAttributes().size() > 0) {
                        map.put("attributes", treeChild.getAttributes());
                    }
                    map.put("children", FnCreateChildren(list, treeChild.getId()));
                    if (((List) map.get("children")).size()== 0) {
                        map.put("checked", treeChild.isChecked());
                    }
                }
                if (map != null) {
                    childList.add(map);
                }
            }
        }
        return childList;
    }
}
