package classes;

public abstract class Device {
    protected String name;
    protected String status;
    protected Protocol protocol;

    public Device(String name, Protocol protocol) {
        this.name = name;
        this.status = "off";
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setStatus(String status) throws IllegalArgumentException {
        if (!status.equals("on") && !status.equals("off")) {
            throw new IllegalArgumentException("invalid value");
        }
        this.status = status;
    }

    public abstract void setProperty(String property, String value) throws IllegalArgumentException;
    public abstract String getInfo();
}