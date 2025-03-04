package Utils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptDecryptPassword {
	private static final String secretKey = "";
    // Method to generate AES Key
//    private static SecretKey generateKey() throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128); // 128-bit AES encryption
//        return keyGenerator.generateKey();
//    }

    // Encrypt the password
    public static String encrypt(String password) throws Exception {
    	SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(),"AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secret_key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Encoding to store as string
    }

    // Decrypt the password
    public static String decrypt(String encryptedPassword) throws Exception {
    	SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(),"AES");
    	Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secret_key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String password = "";
        
        // Generate key for encryption/decryption
        //SecretKey secretKey = generateKey();
        
        
        // Encrypt password
        String encryptedPassword = encrypt(password);
        System.out.println("Encrypted Password: " + encryptedPassword);
        
        // Decrypt password
        String decryptedPassword = decrypt(encryptedPassword);
        System.out.println("Decrypted Password: " + decryptedPassword);

        // Simulate using the decrypted password in Selenium
        // For example, login to a website using Selenium WebDriver (optional)
        // WebDriver driver = new ChromeDriver();
        // driver.get("https://example.com");
        // WebElement passwordField = driver.findElement(By.name("password"));
        // passwordField.sendKeys(decryptedPassword);
    }
}
