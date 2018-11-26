package scripts;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.util.Scanner;

public class FileRead {
    public static void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\rpesaladenne\\Desktop\\Rajitha\\IdeaProjects\\MyProject\\src\\scripts\\gamstop_data.csv"));
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            System.out.println(string);
            Thread.sleep(1000);
        }
    }
}
