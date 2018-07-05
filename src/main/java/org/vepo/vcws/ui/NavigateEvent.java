package org.vepo.vcws.ui;

public class NavigateEvent {
    private final String path;

    public NavigateEvent(String path) {
        this.path = path;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }
}