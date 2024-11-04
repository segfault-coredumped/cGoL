package pkgSlUtils;

import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class SlWindowManager {
    private long windowHandle;

    // point of access for windowhandle
    public long getWindowHandle() {
        return windowHandle;
    }



    private static SlWindowManager instance;

    // singleton
    // single instance of winmanager
    // static so i can keep singular instance throughout
    public static SlWindowManager get() {
        if (instance == null) {
            instance = new SlWindowManager();
        }
        return instance;
    }

    // encapsulating window creation
    public void initGLFWWindow(int width, int height, String title) {
        makeWindow(width, height, title);
    }

    // build the window instance
    private void makeWindow(int width, int height, String title) {
        // has to first check if glfw was initalized - crashes without
        if (!glfwInit()) {
            throw new IllegalStateException("GLFW not initialized");
        }
        // make window based on spot specs
        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);

        // specific window -> all gl calls will use this window
        glfwMakeContextCurrent(windowHandle);
        GL.createCapabilities();
        // show window
        glfwShowWindow(windowHandle);
    }
}
