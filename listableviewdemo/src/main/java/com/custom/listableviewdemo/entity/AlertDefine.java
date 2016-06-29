package com.custom.listableviewdemo.entity;

/**
 * Created by tandewei on 2016/6/28.
 */
public class AlertDefine {
    String id;
    String title;
    int category;
    int level;
    String descriptionPattern;
    IRule rule;
    boolean enable = true;    //是否生效

    public AlertDefine(String id, String title, int category, int level, String descriptionPattern, IRule rule, boolean enable) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.level = level;
        this.descriptionPattern = descriptionPattern;
        this.rule = rule;
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescriptionPattern() {
        return descriptionPattern;
    }

    public void setDescriptionPattern(String descriptionPattern) {
        this.descriptionPattern = descriptionPattern;
    }

    public IRule getRule() {
        return rule;
    }

    public void setRule(IRule rule) {
        this.rule = rule;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
