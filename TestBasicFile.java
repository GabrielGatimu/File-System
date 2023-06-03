import javax.swing.*;

public class TestBasicFile {

    public static void main(String[] args) {

        // create a new instance of BasicFile
        BasicFile file = new BasicFile();

        // create a menu for the user to select options
        while (true) {
            String input = JOptionPane.showInputDialog(
                    "Select an option:\n" +
                            "1. Select a file\n" +
                            "2. Make a copy of the file\n" +
                            "3. Write to a file\n" +
                            "4. Display file attributes\n" +
                            "5. Display file contents\n" +
                            "6. Search file for string\n" +
                            "7. Tokenize file\n" +
                            "8. Exit");

            int choice = Integer.parseInt(input);

            // perform the selected action
            switch (choice) {
                case 1:
                    // prompt the user to select a file
                    String selectedFile = file.selectFile();
                    if (selectedFile != null) {
                        System.out.println("Selected file: " + selectedFile);
                    } else {
                        System.out.println("File selection cancelled by user.");
                    }
                    break;
                case 2:
                    // prompt the user to make a copy of the selected file
                    file.copyFile();
                    break;
                case 3:
                    // prompt the user to write to a file
                    String fileToWrite = file.selectFile();
                    file.writeToFile(fileToWrite);
                    break;
                case 4:
                    // display the file attributes
                    file.selectFile();
                    file.displayAttributes();
                    break;
                case 5:
                    // display the contents of the file
                    file.displayContents(file.selectFile());
                    break;
                case 6:
                    // search the file for a given string
                    file.searchFile(file.selectFile());
                    break;
                case 7:
                    // tokenize the file
                    file.tokenizeFile(file.selectFile());
                    break;
                case 8:
                    // exit the program
                    file.exitProgram();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice");
                    break;
            }
        }
    }
}