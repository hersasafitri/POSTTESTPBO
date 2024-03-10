import java.util.ArrayList;
import java.util.Scanner;

class Customer {
    String name;
    String phoneNumber;
    String service;

    public Customer(String name, String phoneNumber, String service) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.service = service;
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-15s | %-20s |", name, phoneNumber, service);
    }
}

public class HairSalon {
    public static void main(String[] args) {
        ArrayList<Customer> customerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
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
                    addCustomer(customerList, scanner);
                    break;
                case 2:
                    displayCustomers(customerList);
                    break;
                case 3:
                    updateCustomer(customerList, scanner);
                    break;
                case 4:
                    deleteCustomer(customerList, scanner);
                    break;
                case 5:
                    System.out.println("Program Selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        } while (choice != 5);
    }

    private static void addCustomer(ArrayList<Customer> customerList, Scanner scanner) {
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
    }

    private static void updateCustomer(ArrayList<Customer> customerList, Scanner scanner) {
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

                Customer updatedCustomer = new Customer(newName, newPhoneNumber, newService);
                customerList.set(index - 1, updatedCustomer);

                System.out.println("Data pelanggan berhasil diperbarui!");
            } else {
                System.out.println("Indeks tidak valid.");
            }
        }
    }

    private static void deleteCustomer(ArrayList<Customer> customerList, Scanner scanner) {
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
