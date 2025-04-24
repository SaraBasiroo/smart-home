package classes;

public class Light extends Device {
    private int brightness;

    public Light(String name, Protocol protocol) {
        super(name, protocol);
        this.brightness = 50;
    }
    @Override
    public void setProperty(String property, String value) {
        switch (property) {
            case "status":
                setStatus(value);
                break;
            case "brightness":
                int b = Integer.parseInt(value);
                if (b < 0 || b > 100) throw new IllegalArgumentException("invalid value");
                this.brightness = b;
                break;
            default:
                throw new IllegalArgumentException("invalid property");
        }
    }

    @Override
    public String getInfo() {
        return name + " " + status + " " + brightness + "% " + protocol.getName();
    }
}
