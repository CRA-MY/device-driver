import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationTest {
    private final PrintStreamCapturer capturer = new PrintStreamCapturer();

    DeviceDriver deviceDriver;
    Application application;

    @BeforeEach
    void setUp() {
        deviceDriver = mock(DeviceDriver.class);
        application = new Application(deviceDriver);
        capturer.start(); // sout 캡쳐를 위함
    }

    @AfterEach
    public void tearDown() {
        capturer.stop(); // sout 캡쳐를 위함
        String output = capturer.getOutput();
        System.out.println(output);
    }

    @Test
    void 미션2_ReadAndPrint정상수행() throws ReadFailException {
        when(deviceDriver.read(0x00)).thenReturn((byte) 1);
        when(deviceDriver.read(0x01)).thenReturn((byte) 2);
        when(deviceDriver.read(0x02)).thenReturn((byte) 3);
        when(deviceDriver.read(0x03)).thenReturn((byte) 4);
        when(deviceDriver.read(0x04)).thenReturn((byte) 5);

        application.readAndPrint(0x00, 0x04);

        String output = capturer.getOutput();
        assertThat(output).isEqualTo("12345");
    }

    @Test
    void 미션2_WriteAll정상수행() throws WriteFailException {
        application.writeAll((byte) 1);

        verify(deviceDriver, times(1)).write(0x00, (byte) 1);
        verify(deviceDriver, times(1)).write(0x01, (byte) 1);
        verify(deviceDriver, times(1)).write(0x02, (byte) 1);
        verify(deviceDriver, times(1)).write(0x03, (byte) 1);
        verify(deviceDriver, times(1)).write(0x04, (byte) 1);
    }
}