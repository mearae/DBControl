import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScanManager {
    private static Scanner scanner = new Scanner(System.in);

    public static String scan(String key){
        String value = null;

        try {
            System.out.print(key + " : ");

            value = scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("잘못 입력하셨습니다.");
        }
        return value;
    }
}
