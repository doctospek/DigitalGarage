package com.app.digitalgarage;/* Created By Ashwini Saraf on 4/2/2021*/

import java.io.Serializable;

public class Service implements Serializable {

    String id,user_id,created_at;

    public Service() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
