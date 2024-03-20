import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Customer {
    private String name;
    private String phoneNumber;
    private String service;

    public Customer(String name, String phoneNumber, String service) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-15s | %-20s |", name, phoneNumber, service);
    }
}

public class HairSalon {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Customer> customerList = new ArrayList<>();
        int choice;

        do {
            clearScreen();
            System.out.println("Salon Rambut - Menu:");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Tampilkan Data Pelanggan");
            System.out.println("3. Perbarui Data Pelanggan");
            System.out.println("4. Hapus Pelanggan");
            System.out.println("5. Keluar");

            System.out.print("Pilih menu (1-5): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCustomer(customerList);
                    break;
                case 2:
                    displayCustomers(customerList);
                    break;
                case 3:
                    updateCustomer(customerList);
                    break;
                case 4:
                    deleteCustomer(customerList);
                    break;
                case 5:
                    System.out.println("Program Selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
            System.out.println("KETIK ENTER UNTUK MELANJUTKAN");
            clearBuffer();
        } while (choice != 5);
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Pembersihan layar gagal.");
        }
    }

    private static void clearBuffer() {
        scanner.nextLine();
    }

    private static void addCustomer(ArrayList<Customer> customerList) {
        scanner.nextLine();
        System.out.print("Masukkan Nama Pelanggan: ");
        String name = scanner.nextLine();

        System.out.print("Masukkan Nomor Telepon: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Jenis Layanan: ");
        String service = scanner.nextLine();

        Customer newCustomer = new Customer(name, phoneNumber, service);
        customerList.add(newCustomer);

        System.out.println("Pelanggan berhasil ditambahkan!");
    }

    private static void displayCustomers(ArrayList<Customer> customerList) {
        if (customerList.isEmpty()) {
            System.out.println("Tidak ada data pelanggan.");
        } else {
            System.out.println("+----+----------------------+-----------------+----------------------+");
            System.out.println("| No | Nama Pelanggan       | No. Telepon     | Jenis Layanan         |");
            System.out.println("+----+----------------------+-----------------+----------------------+");
            int index = 1;
            for (Customer customer : customerList) {
                System.out.printf("| %-2d %s%n", index++, customer);
            }
            System.out.println("+----+----------------------+-----------------+----------------------+");
        }
        clearBuffer();
    }

    private static void updateCustomer(ArrayList<Customer> customerList) {
        displayCustomers(customerList);

        if (!customerList.isEmpty()) {
            System.out.print("Masukkan indeks pelanggan yang ingin diperbarui: ");
            int index = scanner.nextInt();

            if (index >= 1 && index <= customerList.size()) {
                scanner.nextLine();
                System.out.println("Data Pelanggan Lama: ");
                System.out.println(customerList.get(index - 1));

                System.out.print("Masukkan Nama Pelanggan Baru: ");
                String newName = scanner.nextLine();

                System.out.print("Masukkan Nomor Telepon Baru: ");
                String newPhoneNumber = scanner.nextLine();

                System.out.print("Jenis Layanan Baru: ");
                String newService = scanner.nextLine();

                customerList.get(index - 1).setName(newName);
                customerList.get(index - 1).setPhoneNumber(newPhoneNumber);
                customerList.get(index - 1).setService(newService);

                System.out.println("Data pelanggan berhasil diperbarui!");
            } else {
                System.out.println("Indeks tidak valid.");
            }
        }
    }

    private static void deleteCustomer(ArrayList<Customer> customerList) {
        displayCustomers(customerList);

        if (!customerList.isEmpty()) {
            System.out.print("Masukkan indeks pelanggan yang ingin dihapus: ");
            int index = scanner.nextInt();

            if (index >= 1 && index <= customerList.size()) {
                Customer deletedCustomer = customerList.remove(index - 1);
                System.out.println("Pelanggan berhasil dihapus: ");
                System.out.println(deletedCustomer);
            } else {
                System.out.println("Indeks tidak valid.");
            }
        }
    }
}
