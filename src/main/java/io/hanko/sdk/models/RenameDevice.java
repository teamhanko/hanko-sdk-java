package io.hanko.sdk.models;

public class RenameDevice {
    private String newName;

    public RenameDevice(String newName) {
        this.newName = newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }
}
