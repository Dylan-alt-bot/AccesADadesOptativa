import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static final String

            FILE_PakMain = "./resources/PackageMaintainer.txt",
            FILE_BugPak = "./resources/rcBugPackage.txt",
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

            BufferedReader brBugPak = new BufferedReader(new FileReader(BugPak));
            BufferedWriter bwMailMain = new BufferedWriter(new FileWriter(mailMain));

            String regBugPak = brBugPak.readLine();
            regBugPak = brBugPak.readLine();

            System.out.println("Please input the target bug ID to generate an email to send to the corresponding package maintainers: ");

            int bugID = sc.nextInt();

            while (regBugPak != null) {

                int bugFind = Integer.parseInt(regBugPak.split(";")[0]);

                if (bugID == bugFind) {

                    packages = regBugPak.split("[,;]");

                }

                regBugPak = brBugPak.readLine();

            }


            for (int i = 1; i < packages.length; i++) {

                try (BufferedReader brPakMain = new BufferedReader(new FileReader(PakMain))) {

                    String regPakMain = brPakMain.readLine();

                    while (regPakMain != null) {

                        if (packages[i].equals(regPakMain.split(";")[0])) {

                            mainNames.add(regPakMain.split(";")[1]);
                            mainEmails.add(regPakMain.split(";")[2]);

                            break;

                        }

                        regPakMain = brPakMain.readLine();

                    }
                }
            }

            //EMAIL OUTPUT

            System.out.println("Email generated successfully!");
            System.out.print("To: ");

            for (int i = 0; i < mainEmails.size(); i++) {
                System.out.print(mainEmails.get(i) + ", ");
            }

            System.out.println("");

            System.out.print("Dear ");

            for (int i = 0; i < mainNames.size(); i++) {
                System.out.print(mainNames.get(i) + ", ");
            }

            System.out.println(" ");

            System.out.print("You have a new bug: ");

            for (int i = 1; i < packages.length; i++) {
                System.out.print(packages[i] + ", ");
            }

            System.out.println("- RC bug number #" + bugID);
            System.out.println("Please, fix it as soon as possible.");
            System.out.println("Cheers.");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
