
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;

public class BasicFile {
    private File selectedFile;

    public String selectFile() {
        FileDialog fileDialog = new FileDialog((Frame) null, "Select File");
        fileDialog.setVisible(true);

        String selectedFilePath = null;
        if (fileDialog.getFile() != null) {
            selectedFilePath = fileDialog.getDirectory() + fileDialog.getFile();
        }

        return selectedFilePath;
    }

    public void copyFile() {
        String filePath = selectFile();
        if (filePath != null) {
            File source = new File(filePath);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File destination = fileChooser.getSelectedFile();
                try {
                    FileInputStream fis = new FileInputStream(source);
                    FileOutputStream fos = new FileOutputStream(destination);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fis.close();
                    fos.close();
                    System.out.println("File copied successfully.");
                } catch (IOException e) {
                    System.out.println("An error occurred while copying the file.");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    public void writeToFile(String fileToWrite) {
        if (fileToWrite != null) {
            try {
                FileWriter writer = new FileWriter(fileToWrite, true);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter text to write to file:");
                String text = scanner.nextLine();
                writer.write(text);
                writer.close();
                System.out.println("Text written to file successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    public void displayAttributes() {
        String filePath = selectFile();
        if (filePath != null) {
            File file = new File(filePath);
            Path path = file.toPath();
            try {
                BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                System.out.println("File name: " + file.getName());
                System.out.println("Size: " + attr.size() + " bytes");
                System.out.println("Creation time: " + attr.creationTime());
                System.out.println("Last modified time: " + attr.lastModifiedTime());
                System.out.println("Is directory: " + attr.isDirectory());
                System.out.println("Is regular file: " + attr.isRegularFile());
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file attributes.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    public void displayContents(String selectedFile) {
        if (selectedFile != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                br.close();
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    public void searchFile(String selectedFile) {
        if (selectedFile != null) {
            String searchTerm = JOptionPane.showInputDialog("Enter the search term:");
            if (searchTerm != null) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                    String line;
                    int lineNumber = 0;
                    while ((line = br.readLine()) != null) {
                        lineNumber++;
                        if (line.contains(searchTerm)) {
                            System.out.println("Found '" + searchTerm + "' at line " + lineNumber + ": " + line);
                        }
                    }
                    br.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while reading the file.");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    public void tokenizeFile(String selectedFile) {
        if (selectedFile != null) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split("\\s+");
                    for (String token : tokens) {
                        System.out.println(token);
                    }
                }
                br.close();
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }

    public void exitProgram() {
        System.out.println("Exiting program.");
        System.exit(0);
    }

}