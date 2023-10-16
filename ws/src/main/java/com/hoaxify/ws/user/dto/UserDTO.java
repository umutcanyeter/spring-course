package com.hoaxify.ws.user.dto;

import com.hoaxify.ws.user.User;

public class UserDTO {
    long id;
    String username;
    String email;
    String image;

    public UserDTO(User user) {
        setId(user.getId());
        setImage(user.getImage());
        setEmail(user.getEmail());
        setUsername(user.getUsername());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
