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
        readValidation(address, result);
        return result;
    }

    public void write(long address, byte data) throws WriteFailException {
        byte result = getReadResult(address);
        writeValidation(result);
        hardware.write(address, data);
    }

    private void readValidation(long address, byte result) throws ReadFailException {
        for (int i = 0; i < 4; i++) {
            if (result != hardware.read(address)) {
                throw new ReadFailException("read different information.");
            }
        }
    }

    private void writeValidation(byte result) throws WriteFailException {
        if (result != (byte) 0xFF) {
            throw new WriteFailException("already other values");
        }
    }

    private byte getReadResult(long address) throws WriteFailException {
        byte result = 0;
        try {
            result = read(address);
        } catch (Exception ignored) {
            throw new WriteFailException("Read fail");
        }
        return result;
    }
}