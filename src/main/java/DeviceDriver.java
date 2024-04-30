/**
 * This class is used by the operating system to interact with the hardware 'FlashMemoryDevice'.
 */
public class DeviceDriver {

    FlashMemoryDevice hardware;

    public DeviceDriver(FlashMemoryDevice hardware) {
        this.hardware = hardware;
    }

    public byte read(long address) throws ReadFailException {
        byte result = hardware.read(address);
        for (int i = 0; i < 4; i++) {
            if (result != hardware.read(address)) {
                throw new ReadFailException("read different information.");
            }
        }
        return result;
    }

    public void write(long address, byte data) {
        // TODO: implement this method
    }
}