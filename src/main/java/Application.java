public class Application {

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
        long addressStart = 0x00;
        long addressEnd = 0x04;
        for (long address = addressStart; address <= addressEnd; address++) {
            deviceDriver.write(address, value);
        }
    }
}
