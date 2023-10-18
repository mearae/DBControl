import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScanManager {
    static Scanner scanner = new Scanner(System.in);

    public static String scan(String key){
        String value = null;

        try {
            System.out.println(key+" : ");

            value = scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("잘못 입력");
        }
        return value;
    }
}
