import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeviceDriverTest {

    FlashMemoryDevice hardware;
    DeviceDriver deviceDriver;

    @BeforeEach
    void setUp() {
        hardware = mock(FlashMemoryDevice.class);
        deviceDriver = new DeviceDriver(hardware);
    }

    @Test
    void 미션1_Read정상수행() throws ReadFailException {
        deviceDriver.read(0xFF);
        verify(hardware, times(5)).read(0xFF);
    }

    @Test
    void 미션1_Read결과를반환() throws ReadFailException {
        when(hardware.read(0xFF)).thenReturn((byte) 10);
        byte result = deviceDriver.read(0xFF);
        assertThat(result).isEqualTo((byte) 10);
    }

    @Test
    void 미션1_Read에5번값이다르면예외() {
        when(hardware.read(0xFF))
                .thenReturn((byte) 10)
                .thenReturn((byte) 20);

        assertThatThrownBy(() -> {
            deviceDriver.read(0xFF);
        }).isInstanceOf(ReadFailException.class)
                .hasMessageContaining("different");
    }

    @Test
    void 미션1_Write정상수행() throws WriteFailException {
        when(hardware.read(0xFF)).thenReturn((byte) 0xFF);
        deviceDriver.write(0xFF, (byte) 10);
        verify(hardware, times(1)).write(0xFF, (byte) 10);
    }

    @Test
    void 미션1_Write시값이있다면예외() {
        when(hardware.read(0xFF)).thenReturn((byte) 10);
        assertThatThrownBy(() -> {
            deviceDriver.write(0xFF, (byte) 10);
        }).isInstanceOf(WriteFailException.class)
                .hasMessageContaining("already");

        verify(hardware, times(0)).write(0xFF, (byte) 10);
    }
}