package com.example.hobbie.view;

import com.example.hobbie.model.entities.AppClient;
import com.example.hobbie.model.entities.Hobby;

public class AboViewModel {
    private Long id;
    private AppClient client;
    private Hobby hobby;

    public AboViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppClient getClient() {
        return client;
    }

    public void setClient(AppClient client) {
        this.client = client;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }
}
