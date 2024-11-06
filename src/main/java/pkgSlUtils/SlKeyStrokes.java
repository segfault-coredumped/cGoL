package pkgSlUtils;

import static org.lwjgl.glfw.GLFW.*;

public class SlKeyStrokes {

    private static boolean[] keyPressed = new boolean[GLFW_KEY_LAST];

    private static SlKeyStrokes my_instance;

    // code from professor
    public static void keyCallback(long my_window, int key, int scancode, int action, int modifier_key) {
        if (action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        if (keyCode < get().keyPressed.length) {
            return get().keyPressed[keyCode];
        }
        else {
            return false;
        }
    }

    // call this function to receive one event for repeated presses:
    public static void resetKeypressEvent(int keyCode) {
        if (my_instance != null && keyCode < get().keyPressed.length) {
            my_instance.keyPressed[keyCode] = false;
        }
    }

    // need a getter for instance
    private static SlKeyStrokes get(){
        return my_instance;
    }
}

