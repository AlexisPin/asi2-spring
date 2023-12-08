package com.model;

public class RichUserDTO {

    private UserAction action;
    private UserDTO user;

    public RichUserDTO() {
    }

    public RichUserDTO(UserAction userAction, UserDTO userDTO) {
        this.action = userAction;
        this.user = userDTO;
    }

    public UserAction getAction() {
        return action;
    }

    public void setAction(UserAction action) {
        this.action = action;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
