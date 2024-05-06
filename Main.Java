// Šarlote Stariņa 12.grupa 231RDB086
// Henrijs Filips Verlis 12.grupa 231RDB066 
// Terēze Estere Grants 17.grupa 231RDC025

import java.util.Scanner;
import java.io.*;
import java.util.zip.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter command:");
            command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            }

            try {
                switch (command) {
                    case "comp":
                        System.out.println("source file name:");
                        String sourceFileName = scanner.nextLine();
                        System.out.println("archive name:");
                        String archiveName = scanner.nextLine();
                        compressGZIP(sourceFileName, archiveName);
                        break;
                    case "decomp":
                        System.out.println("archive name:");
                        String archiveFileName = scanner.nextLine();
                        System.out.println("file name:");
                        String outputFileName = scanner.nextLine();
                        decompressGZIP(archiveFileName, outputFileName);
                        break;
                    case "size":
                        System.out.println("file name:");
                        String fileName = scanner.nextLine();
                        File file = new File(fileName);
                        System.out.println("size: " + file.length());
                        break;
                    case "equal":
                        System.out.println("first file name:");
                        String firstFileName = scanner.nextLine();
                        System.out.println("second file name:");
                        String secondFileName = scanner.nextLine();
                        System.out.println("equal: " + compareFiles(firstFileName, secondFileName));
                        break;
                    case "about":
                        System.out.println("Šarlote Stariņa 12. grupa 231RDB086");
                        System.out.println("Henrijs Filips Verlis 12.grupa 231RDB066");
                        System.out.println("Terēze Estere Grants 17.grupa 231RDC025");
                        break;
                    default:
                        System.out.println("Invalid command");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public static void compressGZIP(String sourceFileName, String archiveName) throws IOException {
        FileInputStream in = new FileInputStream(sourceFileName);
        GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(archiveName));
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.finish();
        out.close();
    }

    public static void decompressGZIP(String archiveFileName, String outputFileName) throws IOException {
        GZIPInputStream in = new GZIPInputStream(new FileInputStream(archiveFileName));
        FileOutputStream out = new FileOutputStream(outputFileName);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    public static boolean compareFiles(String firstFileName, String secondFileName) throws IOException {
        FileInputStream fis1 = new FileInputStream(firstFileName);
        FileInputStream fis2 = new FileInputStream(secondFileName);
        int b1, b2;
        while (true) {
            b1 = fis1.read();
            b2 = fis2.read();
            if (b1 != b2) {
                return false;
            }
            if ((b1 == -1) || (b2 == -1)) {
                break;
            }
        }
        return true;
    }
}