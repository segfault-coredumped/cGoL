package pkgCSC133Driver;

import pkgSlRenderer.GoLRenderer;
import pkgSlUtils.PingPongManager;
import pkgSlUtils.SlWindowManager;

import static pkgCSC133Driver.SlSpot.*;

public class CSC133Driver {
    public static void main(String[] my_args) {
        // instance of pingpong manager
        PingPongManager pp = new PingPongManager(BOARDSIZE,BOARDSIZE);
        pp.showLiveArr();
        pp.fillNNNNextArr();
        pp.swapArr();
        System.out.println();
        pp.showLiveArr();
        // instance of renderer
        GoLRenderer my_re = new GoLRenderer();
        SlWindowManager.get().initGLFWWindow(WIN_WIDTH, WIN_HEIGHT, "CSUS CSC133");
        my_re.initOpenGL(SlWindowManager.get());

        final int FRAME_DELAY = 200;
        my_re.render(FRAME_DELAY);

    }
}
