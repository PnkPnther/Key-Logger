package handlers;

public class KeyStorage {

    //This class handles storage of logged keys
    private int keyCode;
    private boolean pressed;
    private long systemTimePressedMillis;

    // Constructor for KeyStorage
    public KeyStorage(int keyCode, boolean pressed, long systemTimePressedMillis) {
        this.keyCode = keyCode;
        this.pressed = pressed;
        this.systemTimePressedMillis = systemTimePressedMillis;
    }

    // Handles request for logged keys
    public int getKeyCode() {
        return keyCode;
    }

    public boolean isPressed() {
        return pressed;
    }

    @Override
    public String toString() {
        return "handlers.KeyStorage{" +
                "keyCode=" + keyCode +
                ", pressed=" + pressed +
                ", systemTimePressedMillis=" + systemTimePressedMillis +
                '}';
    }
}
