import java.io.*;

class FileReaderAndWriter {
    public void writeToFile(String text, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuffer readAllTextFromFile(String filePath) {
        StringBuffer sb = new StringBuffer();
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

    public StringBuffer readAllTextFromFileFormatted(String filePath) {
        StringBuffer sb = new StringBuffer();
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
        //     only  (xxx) xxx-xxxx or xxx-xxx-xxxx    are acceptable formats
        String regex = "^\\(\\d{3}\\) \\d{3}-\\d{4}$|^\\d{3}-\\d{3}-\\d{4}$";
        return sample.matches(regex);
    }
}

public class InputOutputSanbox {
    public static void main(String[] args) {
        System.out.println("--===||| This is Input Output test |||===--");
        String filePath = "C:\\Users\\Ruslan\\Desktop\\output.txt";
        String[] phoneNumbers = new String[]{"987-123-4567", "123 456 7890", "(123) 456-7890"};
        FileReaderAndWriter rw = new FileReaderAndWriter();
        StringBuffer sb = new StringBuffer();

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

        sb = rw.readAllTextFromFileFormatted(filePath);
        System.out.println("--===||| Filtered phone numbers list according to format requirements: |||===--");
        System.out.println(sb);
        sb.delete(0, sb.length());
    }
}