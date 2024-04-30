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
    void 미션1_Read5번을수행한다() throws ReadFailException {
        deviceDriver.read(0xFF);
        verify(hardware, times(5)).read(0xFF);
    }

    @Test
    void 미션1_Read5번의결과를반환한다() throws ReadFailException {
        when(hardware.read(0xFF)).thenReturn((byte) 10);
        byte result = deviceDriver.read(0xFF);
        assertThat(result).isEqualTo((byte) 10);
    }

    @Test
    void 미션1_5번의값이다르면에러를반환한다() {
        when(hardware.read(0xFF))
                .thenReturn((byte) 10)
                .thenReturn((byte) 20);

        assertThatThrownBy(() -> {
            deviceDriver.read(0xFF);
        }).isInstanceOf(ReadFailException.class)
                .hasMessageContaining("different");
    }

    @Test
    void 미션1_Write한다() throws WriteFailException {
        when(hardware.read(0xFF)).thenReturn((byte) 0xFF);
        deviceDriver.write(0xFF, (byte) 10);
        verify(hardware, times(1)).write(0xFF, (byte) 10);
    }

    @Test
    void 미션1_Write시값이있다면예외를발생한다() {
        when(hardware.read(0xFF)).thenReturn((byte) 10);
        assertThatThrownBy(() -> {
            deviceDriver.write(0xFF, (byte) 10);
        }).isInstanceOf(WriteFailException.class)
                .hasMessageContaining("already");

        verify(hardware, times(0)).write(0xFF, (byte) 10);
    }
}