package ua.com.alevel.task_3;

public enum Flag {
    IS_VALIDATE(false);

    private boolean state;

    Flag(boolean initialState) {
        this.state = initialState;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
