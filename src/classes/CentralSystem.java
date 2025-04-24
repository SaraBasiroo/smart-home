package classes;

import java.util.*;

public class CentralSystem {
    private Map<String, Device> devices = new LinkedHashMap<>();
    private List<Rule> rules = new ArrayList<>();

    public String addDevice(String type, String name, String protocol) {
        if (devices.containsKey(name)) return "duplicate device name";

        Protocol proto = switch (protocol) {
            case "WiFi" -> new WiFi();
            case "Bluetooth" -> new Bluetooth();
            default -> null;
        };

        if (proto == null) return "invalid input";

        Device device;
        switch (type) {
            case "light" -> device = new Light(name, proto);
            case "thermostat" -> device = new Thermostat(name, proto);
            default -> {
                return "invalid input";
            }
        }

        devices.put(name, device);
        return "device added successfully";
    }

    public String setDevice(String name, String property, String value) {
        if (!devices.containsKey(name)) return "device not found";
        try {
            devices.get(name).setProperty(property, value);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "device updated successfully";
    }

    public String removeDevice(String name) {
        if (!devices.containsKey(name)) return "device not found";
        devices.remove(name);
        rules.removeIf(rule -> rule.getDeviceName().equals(name));
        return "device removed successfully";
    }

    public String listDevices() {
        if (devices.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Device d : devices.values()) {
            sb.append(d.getInfo()).append("\n");
        }
        return sb.toString().trim();
    }

    public String addRule(String name, String time, String action) {
        if (!devices.containsKey(name)) return "device not found";
        if (!time.matches("\\d{2}:\\d{2}") || !isValidTime(time)) return "invalid time";
        if (!action.equals("on") && !action.equals("off")) return "invalid action";
        for (Rule r : rules) {
            if (r.getDeviceName().equals(name) && r.getTime().equals(time)) return "duplicate rule";
        }
        rules.add(new Rule(name, time, action));
        return "rule added successfully";
    }

    private boolean isValidTime(String time) {
        try {
            String[] parts = time.split(":");
            int h = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            return h >= 0 && h < 24 && m >= 0 && m < 60;
        } catch (Exception e) {
            return false;
        }
    }

    public String checkRules(String time) {
        if (!time.matches("\\d{2}:\\d{2}") || !isValidTime(time)) return "invalid time";
        for (Rule r : rules) {
            if (r.getTime().equals(time)) {
                devices.get(r.getDeviceName()).setStatus(r.getAction());
            }
        }
        return "rules checked";
    }

    public String listRules() {
        if (rules.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Rule r : rules) {
            sb.append(r).append("\n");
        }
        return sb.toString().trim();
    }
}
