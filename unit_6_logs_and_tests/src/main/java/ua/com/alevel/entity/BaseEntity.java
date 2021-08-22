package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseEntity {

    protected int id;
    protected Date created;
    protected boolean available;

    public BaseEntity() {
        this.created = new Date();
    }
}
