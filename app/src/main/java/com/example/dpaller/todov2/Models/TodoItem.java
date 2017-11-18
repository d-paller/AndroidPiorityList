package com.example.dpaller.todov2.Models;

/**
 * Created by dpall on 11/15/2017.
 */

public class TodoItem {
    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_tag() {
        return _tag;
    }

    public void set_tag(String _tag) {
        this._tag = _tag;
    }

    public Integer get_priority() {
        return _priority;
    }

    public void set_priority(Integer _priority) {
        this._priority = _priority;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public Boolean get_isDone() {
        return _isDone;
    }

    public void set_isDone(Boolean _isDone) {
        this._isDone = _isDone;
    }

    private String _title;
    private String _tag;
    private Integer _priority;
    private String _description;
    private Boolean _isDone;

    public TodoItem(String title, String tag, int priority, String description){
        _title = title;
        _tag = tag;
        _priority = priority;
        _description = description;
    }
}
