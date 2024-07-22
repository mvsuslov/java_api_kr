import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Laptop {
    private String brand;
    private int ram; // in GB
    private int hdd; // in GB
    private String os;
    private String color;

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop [brand=" + brand + ", ram=" + ram + "GB, hdd=" + hdd + "GB, os=" + os + ", color=" + color + "]";
    }

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Silver"));
        laptops.add(new Laptop("HP", 32, 1024, "Windows", "Gray"));
        laptops.add(new Laptop("Asus", 16, 512, "Windows", "White"));
        laptops.add(new Laptop("Lenovo", 8, 256, "Linux", "Black"));

        Scanner scanner = new Scanner(System.in);

        Map<String, String> stringCriteria = new HashMap<>();
        Map<String, Integer> intCriteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Введите минимальное значение ОЗУ (в GB): ");
                intCriteria.put("ram", scanner.nextInt());
                break;
            case 2:
                System.out.print("Введите минимальное значение объема ЖД (в GB): ");
                intCriteria.put("hdd", scanner.nextInt());
                break;
            case 3:
                System.out.print("Введите операционную систему: ");
                stringCriteria.put("os", scanner.nextLine().toLowerCase());
                break;
            case 4:
                System.out.print("Введите цвет: ");
                stringCriteria.put("color", scanner.nextLine().toLowerCase());
                break;
            default:
                System.out.println("Неверный выбор");
                scanner.close();
                return;
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, intCriteria, stringCriteria);
        if (filteredLaptops.isEmpty()) {
            System.out.println("Нет ноутбуков, соответствующих указанным критериям.");
        } else {
            System.out.println("Ноутбуки, соответствующие указанным критериям:");
            filteredLaptops.forEach(System.out::println);
        }

        scanner.close();
    }

    private static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Integer> intCriteria, Map<String, String> stringCriteria) {
        return laptops.stream().filter(laptop -> {
            for (Map.Entry<String, Integer> entry : intCriteria.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (laptop.getRam() < entry.getValue()) {
                            return false;
                        }
                        break;
                    case "hdd":
                        if (laptop.getHdd() < entry.getValue()) {
                            return false;
                        }
                        break;
                }
            }
            for (Map.Entry<String, String> entry : stringCriteria.entrySet()) {
                switch (entry.getKey()) {
                    case "os":
                        if (!laptop.getOs().toLowerCase().equals(entry.getValue())) {
                            return false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().toLowerCase().equals(entry.getValue())) {
                            return false;
                        }
                        break;
                }
            }
            return true;
        }).collect(Collectors.toSet());
    }
}
