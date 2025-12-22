import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static final String

            FILE_PakMain = "./recursos/PackageMaintainer.txt",
            FILE_BugPak = "./recursos/rcBugPackage.txt",
            PakMain = Main.class.getResource(FILE_PakMain).getFile(),
            BugPak = Main.class.getResource(FILE_BugPak).getFile();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.ENGLISH);

        File mailMain = new File("emailMaintainer.txt");

        String[] packages = new String[0];
        ArrayList<String> mainNames = new ArrayList<>();
        ArrayList<String> mainEmails = new ArrayList<>();

        try {

            BufferedReader brPakMain = new BufferedReader(new FileReader(PakMain));
            BufferedReader brBugPak = new BufferedReader(new FileReader(BugPak));
            BufferedWriter bwMailMain = new BufferedWriter(new FileWriter(mailMain));

            String regPakMain = brPakMain.readLine();
            String regBugPak = brBugPak.readLine();

            System.out.println("Please input the target bug ID to generate an email to send to the corresponding package maintainers: ");

            int bugID = sc.nextInt();

            while (regPakMain != null && regBugPak != null) {

                if (bugID == Integer.parseInt(regBugPak.split(";")[0])) {

                    packages = regBugPak.split("[,;]");

                }

                    String PakMainFind = regPakMain.split(";")[0];

                for (int i = 1; i < packages.length; i++) {

                    if (packages[i].equals(PakMainFind)) {

                        for (int k = 1; k < packages.length; k++) {


                        }
                    }
                }





            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
