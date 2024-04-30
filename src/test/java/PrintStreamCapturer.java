import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrintStreamCapturer {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private final PrintStream newOut = new PrintStream(baos);

    public void start() {
        System.setOut(newOut);
    }

    public void stop() {
        System.setOut(originalOut);
    }

    public String getOutput() {
        return baos.toString();
    }
}
