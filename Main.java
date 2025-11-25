package week11.zachary.id.ac.umn;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> listOfUser = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialize();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nMenu Utama");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan : ");
            
            String input = scanner.nextLine();
            
            switch (input) {
                case "1":
                    try {
                        handleLogin();
                    } catch (ExcessiveFailedLoginException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    handleSignUp();
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public static void initialize() {
        User admin = new User(
            "John", 
            "Doe", 
            "L", 
            "Jl. Merpati No. 1 RT 1 RW 1, Banten", 
            "admin", 
            "admin"
        );
        listOfUser.add(admin);
    }

    public static void handleLogin() throws ExcessiveFailedLoginException {
        System.out.println("\nMenu Login");
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Username : ");
            String username = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();

            try {
                boolean isFound = false;
                User loggedInUser = null;

                for (User user : listOfUser) {
                    if (user.login(username, password)) {
                        isFound = true;
                        loggedInUser = user;
                        break;
                    }
                }

                if (!isFound) {
                    throw new AuthenticationException("Username / password salah");
                } else {
                    System.out.println("Selamat Datang! " + loggedInUser.getNamaLengkap());
                    return;
                }

            } catch (AuthenticationException e) {
                System.out.println(e.getMessage());
                attempts++;
            }
        }

        throw new ExcessiveFailedLoginException("Anda telah mencapai jumlah batas login");
    }

    public static void handleSignUp() {
        System.out.println("\nMenu Lihat Sign Up");
        
        System.out.print("Nama Depan : ");
        String namaDepan = scanner.nextLine();
        
        System.out.print("Nama Belakang : ");
        String namaBelakang = scanner.nextLine();
        
        System.out.print("Jenis Kelamin (L/P) : ");
        String jenisKelamin = scanner.nextLine();
        
        System.out.print("Alamat : ");
        String alamat = scanner.nextLine();

        String username;
        while (true) {
            System.out.print("Username : ");
            username = scanner.nextLine();
            if (username.length() > 8) {
                break;
            } else {
                System.out.println("Username harus lebih dari 8 karakter");
            }
        }

        String password;
        while (true) {
            System.out.print("Password : ");
            password = scanner.nextLine();
            
            if (isValidPassword(password)) {
                break;
            } else {
                System.out.println("Password harus mengandung huruf besar, angka, minimum 6 karakter dan maksimum 16 karakter");
            }
        }

        User newUser = new User(namaDepan, namaBelakang, jenisKelamin, alamat, username, password);
        listOfUser.add(newUser);
        
        System.out.println("User telah berhasil didaftarkan");
    }

    private static boolean isValidPassword(String password) {
        if (password.length() < 6 || password.length() > 16) {
            return false;
        }
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");

        return hasUppercase && hasDigit;
    }
}