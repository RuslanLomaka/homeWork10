import java.io.*;

public class FileReaderAndWriter {
    private static final String PHONE_REGEX = "^\\(\\d{3}\\) \\d{3}-\\d{4}$|^\\d{3}-\\d{3}-\\d{4}$";
    //                                               only  (xxx) xxx-xxxx OR xxx-xxx-xxxx    are acceptable formats
    public void writeToFile(String text, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder readAllTextFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }



    public StringBuilder readFromFileFormattedPhoneNumbers(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (this.checkFormat(line)) {
                    sb.append(line).append("\n");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    private boolean checkFormat(String sample) {
        return sample.matches(PHONE_REGEX);
    }
}

class InputOutputSanBox {
    public static void main(String[] args) {
        //     DON'T     FORGET     TO     CUSTOMIZE    YOUR     DESIRED     FILEPATH
        String filePathWindows = "C:\\Users\\Ruslan\\Desktop\\output.txt";
        String filePathMac = "~/Desktop/output.txt";
        String filePathLinux = "/home/ruslan/Desktop/output.txt";
        String filePath;

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            System.out.println("Operating System: Windows");
            filePath = filePathWindows;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            System.out.println("Operating System: Linux/Unix");
            filePath = filePathLinux;
        } else if (os.contains("mac")) {
            System.out.println("Operating System: macOS");
            filePath = filePathMac;
        } else {
            System.out.println("Operating System: Unknown");
            filePath = "output.txt";
        }

        System.out.println("--===||| This is Input Output test |||===--");

        String[] phoneNumbers = new String[]{"987-123-4567", "123 456 7890", "(123) 456-7890"};
        FileReaderAndWriter rw = new FileReaderAndWriter();
        StringBuilder sb = new StringBuilder();

        for (String num : phoneNumbers) {
            sb.append(num).append("\n");
        }

        rw.writeToFile(sb.toString(), filePath);
        System.out.println("--===||| Wrote phone numbers to file: " + filePath + "|||===--");
        sb.delete(0, sb.length());

        sb = rw.readAllTextFromFile(filePath);
        System.out.println("--===||| Unfiltered phone numbers list: |||===--");
        System.out.println(sb);
        sb.delete(0, sb.length());

        sb = rw.readFromFileFormattedPhoneNumbers(filePath);
        System.out.println("--===||| Filtered phone numbers list according to format requirements: |||===--");
        System.out.println(sb);
        sb.delete(0, sb.length());
    }
}