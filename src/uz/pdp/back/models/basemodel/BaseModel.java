package uz.pdp.back.models.basemodel;

import java.util.UUID;

public abstract class BaseModel {
    private String id;

    public BaseModel() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}