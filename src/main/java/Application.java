public class Application {

    public static final int ADDRESS_START = 0x00;
    public static final int ADDRESS_END = 0x04;
    DeviceDriver deviceDriver;

    public Application(DeviceDriver deviceDriver) {
        this.deviceDriver = deviceDriver;
    }


    public void readAndPrint(long addressStart, long addressEnd) throws ReadFailException {
        for (long address = addressStart; address <= addressEnd; address++) {
            byte result = deviceDriver.read(address);
            System.out.print(result);
        }
    }


    public void writeAll(byte value) throws WriteFailException {
        for (long address = ADDRESS_START; address <= (long) ADDRESS_END; address++) {
            deviceDriver.write(address, value);
        }
    }
}
