import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Script {

    public static Robot keyboard;

    public static void main(String[] args) throws AWTException {
        keyboard = new Robot();
        keyboard.keyPress(KeyEvent.VK_WINDOWS);
        keyboard.keyPress(KeyEvent.VK_E);
        keyboard.keyRelease(KeyEvent.VK_E);
        keyboard.keyRelease(KeyEvent.VK_WINDOWS);
    }
}
