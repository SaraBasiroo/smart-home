package classes;

public class Thermostat extends Device {
    private int temperature;

    public Thermostat(String name, Protocol protocol) {
        super(name, protocol);
        this.temperature = 20;
    }

    @Override
    public void setProperty(String property, String value) {
        switch (property) {
            case "status":
                setStatus(value);
                break;
            case "temperature":
                int t = Integer.parseInt(value);
                if (t < 10 || t > 30) throw new IllegalArgumentException("invalid value");
                this.temperature = t;
                break;
            default:
                throw new IllegalArgumentException("invalid property");
        }
    }

    @Override
    public String getInfo() {
        return name + " " + status + " " + temperature + "C " + protocol.getName();
    }
}
