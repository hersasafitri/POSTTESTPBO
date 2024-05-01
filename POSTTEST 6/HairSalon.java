import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Customer {
    protected String name;
    protected String phoneNumber;
    protected String service;

    public Customer(String name, String phoneNumber, String service) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.service = service;
    }

    public abstract void someAbstractMethod();

    public void updateCustomerInfo(String newName, String newPhoneNumber, String newService) {
        this.name = newName;
        this.phoneNumber = newPhoneNumber;
        this.service = newService;
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-15s | %-20s |", name, phoneNumber, service);
    }
}


class RegularCustomer extends Customer {
    private int loyaltyPoints;

    public RegularCustomer(String name, String phoneNumber, String service, int loyaltyPoints) {
        super(name, phoneNumber, service);
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public void someAbstractMethod() {
        // Implementation for abstract method
    }

    @Override
    public String toString() {
        return super.toString() + " | Loyalty Points: " + loyaltyPoints;
    }
}


class VIPCustomer extends Customer {
    private String membershipLevel;

    public VIPCustomer(String name, String phoneNumber, String service, String membershipLevel) {
        super(name, phoneNumber, service);
        this.membershipLevel = membershipLevel;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @Override
    public void someAbstractMethod() {
        // Implementation for abstract method
    }

    @Override
    public String toString() {
        return super.toString() + " | Membership Level: " + membershipLevel;
    }
}

final class HairSalon {
    private static final Scanner scanner = new Scanner(System.in);
    private static int totalCustomers = 0;

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
            System.out.println("5. Tampilkan Jumlah Pelanggan");
            System.out.println("6. Keluar");

            System.out.print("Pilih menu (1-6): ");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid. Silakan masukkan angka.");
                choice = -1;
                scanner.next(); // Membersihkan input yang salah
            }

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
                    displayCustomerCount();
                    break;
                case 6:
                    System.out.println("Program Selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
            System.out.println("KETIK ENTER UNTUK MELANJUTKAN");
            clearBuffer();
        } while (choice != 6);

        scanner.close(); // Menutup scanner setelah penggunaan selesai
    }

    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ex) {
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

        System.out.print("Apakah pelanggan adalah pelanggan reguler? (ya/tidak): ");
        String isRegular = scanner.nextLine();

        if (isRegular.equalsIgnoreCase("ya")) {
            System.out.print("Masukkan poin loyalitas: ");
            try {
                int loyaltyPoints = scanner.nextInt();
                RegularCustomer newRegularCustomer = new RegularCustomer(name, phoneNumber, service, loyaltyPoints);
                customerList.add(newRegularCustomer);
                totalCustomers++;
                System.out.println("Pelanggan berhasil ditambahkan!");
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid untuk poin loyalitas.");
                scanner.next(); // Membersihkan input yang salah
            }
        } else {
            System.out.print("Masukkan tingkat keanggotaan VIP: ");
            scanner.nextLine();
            String membershipLevel = scanner.nextLine();
            VIPCustomer newVIPCustomer = new VIPCustomer(name, phoneNumber, service, membershipLevel);
            customerList.add(newVIPCustomer);
            totalCustomers++;
            System.out.println("Pelanggan berhasil ditambahkan!");
        }
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

        if (!customerList.get()) {
            System.out.print("Masukkan indeks pelanggan yang ingin diperbarui: ");
            try {
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

                    customerList.get(index - 1).someAbstractMethod();

                    System.out.println("Data pelanggan berhasil diperbarui!");
                } else {
                    System.out.println("Indeks tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid untuk indeks pelanggan.");
                scanner.next();
            }
        }
    }

    private static void deleteCustomer(ArrayList<Customer> customerList) {
        displayCustomers(customerList);

        if (!customerList.isEmpty()) {
            System.out.print("Masukkan indeks pelanggan yang ingin dihapus: ");
            try {
                int index = scanner.nextInt();

                if (index >= 1 && index <= customerList.size()) {
                    Customer deletedCustomer = customerList.remove(index - 1);
                    totalCustomers--;
                    System.out.println("Pelanggan berhasil dihapus: ");
                    System.out.println(deletedCustomer);
                } else {
                    System.out.println("Indeks tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid untuk indeks pelanggan.");
                scanner.next(); // Membersihkan input yang salah
            }
        }
    }

    private static void displayCustomerCount() {
        System.out.println("Jumlah Pelanggan Saat Ini: " + totalCustomers);
        clearBuffer();
    }
}
