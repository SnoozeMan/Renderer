package com.base.engine;

public class MainComponent {
    private static final int WIDTH = 1280; // 800
    private static final int HEIGHT = 720; // 600
    private static final String TITLE = "Renderer";
    private static final double FRAME_CAP = 5000;

    private Game game;
    private boolean isRunning;

    private MainComponent() {
        RenderUtility.initializeGraphics();
        System.out.println("OpenGL version: " + RenderUtility.getOpenGLVersion());
        game = new Game();
        isRunning = false;
    }

    public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        run();
    }

    public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
    }

    private void run() {
        int frames = 0;
        long frameCounter = 0;
        final double frameTime = 1.0 / FRAME_CAP;
        long lastTime = Time.getTime();
        double unprocessedTime = 0;
        int counter = 0;
        while(isRunning) {
            boolean render = false;
            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = Time.getTime();
            unprocessedTime += passedTime / (double)Time.SECOND;
            frameCounter += passedTime;
            while (unprocessedTime > frameTime) {
                counter++;
                render = true;
                unprocessedTime -= frameTime;
                if (Window.isCloseRequested()) {
                    stop();
                }
                Time.setDelta(frameTime);
                game.input();
                Input.update();
                game.update();

                if (frameCounter >= Time.SECOND) {
                    System.out.println(counter);
                    counter = 0;
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if (render) {
                render();
                frames++;
            }
            else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        cleanup();
    }

    private void render() {
        RenderUtility.clearScreen();
        game.render();
        Window.render();
        Window.swapBuffers();
    }

    private void cleanup() {
        Window.dispose();
    }

    public static void main(String[] args) {
        Window.createWindow(WIDTH, HEIGHT, TITLE);
        MainComponent game = new MainComponent();
        game.start();
    }
}
