package com.padingpading.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 基础历史实体类
 */
public class OperationContent<T extends BaseEntity> {

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 创建集合
     */
    private List<T> creates = new LinkedList<T>();
    /**
     * 更新集合
     */
    private List<ContentPair<T>> updates = new LinkedList<ContentPair<T>>();
    /**
     * 删除集合
     */
    private List<T> deletes = new LinkedList<T>();

    public OperationContent create(T obj) {
        creates.add(obj);
        return this;
    }

    public OperationContent update(T oldObj, T newObj) {
        ContentPair itemPair = new ContentPair(oldObj, newObj);
        updates.add(itemPair);
        return this;
    }

    public OperationContent delete(T obj) {
        deletes.add(obj);
        return this;
    }

    public boolean hasContent() {
        return !creates.isEmpty() || !updates.isEmpty() || !deletes.isEmpty();
    }

    public String build() {
        // 因为事务第一段提交并没有更新时间,所以build时统一更新
        Date now = new Date();
        for (T var : creates) {
            var.setUpdateTime(now);
        }
        for (ContentPair var : updates) {
            T newest = (T) var.newest;
            newest.setUpdateTime(now);
        }
        for (T var : deletes) {
            var.setUpdateTime(now);
        }
        // JSON 格式化成字符串
        return gson.toJson(this);
    }

    /**
     * 组
     */
    class ContentPair<T> {

        T old; // 老
        T newest; // 新

        public ContentPair(T old, T newest) {
            this.old = old;
            this.newest = newest;
        }
    }
}
