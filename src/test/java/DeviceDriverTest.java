import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class DeviceDriverTest {

    @Mock
    FlashMemoryDevice hardware;

    @Test
    public void read_From_Hardware() {
//        FlashMemoryDevice hardware = null;
        DeviceDriver driver = new DeviceDriver(hardware);
        byte data = driver.read(0xFF);
        assertEquals(0, data);
    }

    @Test
    void Read5번이모두같은경우() {

    }
}