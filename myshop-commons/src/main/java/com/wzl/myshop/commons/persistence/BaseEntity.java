package com.wzl.myshop.commons.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by thinkpad on 2018/9/12.
 */
public abstract class BaseEntity implements Serializable {
    private Long id;
    private Date created;
    private Date updated;

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

    public BaseEntity() {
    }

    public BaseEntity(Long id, Date created, Date updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
