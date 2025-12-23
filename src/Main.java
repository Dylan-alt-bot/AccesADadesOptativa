import java.io.*;
import java.util.*;

public class Main {

    static final String

            FILE_PakMain = "./resources/PackageMaintainer.txt",
            FILE_BugPak = "./resources/rcBugPackage.txt",
            PakMain = Main.class.getResource(FILE_PakMain).getFile(),
            BugPak = Main.class.getResource(FILE_BugPak).getFile();

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.ENGLISH);

        File mailMain = new File("emailMaintainer.txt");

        String[] packages = new String[0];
        HashSet<String> mainNames = new HashSet<>();
        HashSet<String> mainEmails = new HashSet<>();

        BufferedWriter bwMailMain;
        try {
            bwMailMain = new BufferedWriter(new FileWriter(mailMain));
        } catch (IOException e) {
            System.out.println("An error occurred when writing to emailMaintainer.txt.");
            throw new RuntimeException(e);
        }

        try {

            BufferedReader brBugPak = new BufferedReader(new FileReader(BugPak));

            brBugPak.readLine();
            String regBugPak = brBugPak.readLine();

            System.out.println("Please input the target bug ID to generate an email to send to the corresponding package maintainers: ");

            int bugID = sc.nextInt();

            //Searching BugID match in rcBugPackage

            while (regBugPak != null) {

                int bugFind = Integer.parseInt(regBugPak.split(";")[0]);

                if (bugID == bugFind) {

                    packages = regBugPak.split("[,;]");

                }

                regBugPak = brBugPak.readLine();

            }

            //Searching for packaging matches in PackageMaintainer

            for (int i = 1; i < packages.length; i++) {

                try (BufferedReader brPakMain = new BufferedReader(new FileReader(PakMain))) {

                    String regPakMain = brPakMain.readLine();

                    while (regPakMain != null) {

                        if (packages[i].equals(regPakMain.split(";")[0])) {

                            mainNames.add(regPakMain.split(";")[1]);
                            mainEmails.add(regPakMain.split(";")[2]);

                            //test with 670292 for triplicate maintainer/emails

                            break;

                        }

                        regPakMain = brPakMain.readLine();

                    }
                }
            }

            //EMAIL OUTPUT

            System.out.println("Email generated successfully!");

            bwMailMain.write("To: ");

            for (int i = 1; i <= mainEmails.size(); i++) {

                    bwMailMain.write(String.join(", ", mainEmails));

                    if (mainEmails.size() == 1) {
                        break;
                    }

            }

            bwMailMain.newLine();
            bwMailMain.newLine();

            bwMailMain.write("Dear ");

            for (int i = 1; i <= mainNames.size(); i++) {
                bwMailMain.write(String.join(", ", mainNames));
                if (mainNames.size() == 1) {
                    break;
                }
            }

            bwMailMain.newLine();
            bwMailMain.newLine();

            bwMailMain.write("You have a new bug: ");

            for (int i = 1; i < packages.length; i++) {

                bwMailMain.write(packages[i]);

                if (packages.length > 1 && packages.length - 1 != i) {

                    bwMailMain.write(", ");

                }

            }

            bwMailMain.write(" - RC bug number #" + bugID);

            bwMailMain.newLine();

            bwMailMain.write("Please, fix it as soon as possible.");

            bwMailMain.newLine();
            bwMailMain.newLine();

            bwMailMain.write("Cheers.");

            bwMailMain.close();

        } catch (Exception e) {
            System.out.println("Something went wrong during email generation. Please consult the error message in emailMaintainer.txt for more details.");
            try {
                bwMailMain.write("ERROR: " + e.getMessage());
                bwMailMain.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
