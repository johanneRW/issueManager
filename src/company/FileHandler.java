package company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public void saveNewIssue(String FILE_PATH, Issue issue) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        PrintStream ps = new PrintStream(new FileOutputStream(file, true));
        ps.println(issue.toString() + ";" + issue.status);
        ps.close();
    }

    public ArrayList<Issue> readIssues(String FILE_PATH) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        ArrayList<Issue> issues = new ArrayList<>();
        Issue issue = null;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[]parts = line.split(";");
            String description = parts[0];
            Status status = Status.valueOf(parts[1]);
            issues.add(new Issue(description, status));
        }

        return issues;
    }

    public void deleteContents(String fileName) throws FileNotFoundException {
        new PrintStream(fileName).close();
    }
}
