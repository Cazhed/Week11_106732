package week11.zachary.id.ac.umn;

public class User {
    private String namaDepan;
    private String namaBelakang;
    private String jenisKelamin;
    private String alamat;
    private String username;
    private String password;

    public User(String namaDepan, String namaBelakang, String jenisKelamin, String alamat, String username, String password) {
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
    }

    // Method login untuk mengecek kecocokan kredensial
    public boolean login(String usernameInput, String passwordInput) {
        return this.username.equals(usernameInput) && this.password.equals(passwordInput);
    }

    public String getNamaLengkap() {
        return namaDepan + " " + namaBelakang;
    }
    
    // Getter (jika diperlukan untuk validasi lain)
    public String getUsername() { return username; }
}
