package company;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IssueManager {
        private FileHandler fileHandler = new FileHandler();
        private UserInterface ui = new UserInterface();
        private static final String FILE_PATH = "data/issues.txt";

        public void run() {
            boolean stop = false;

            while (!stop) {
                ui.printMainMenu();
                int answer = ui.getChoice();
                switch (answer) {
                    case 1:
                        createNewIssue();
                        break;
                    case 2:
                        validatePendingIssues();
                        break;
                    case 3:
                        sowAllIssues();
                        break;
                    case 4:
                        stop = true;
                        break;
                    default:
                        ui.printErrorMessage("Invalid choice.");
                        stop = true;
                        break;
                }
            }
        }


        public void createNewIssue() {
            String text = ui.getIssueDescription();
            //text = "Type conversion problem: How to convert a String to integer";
            Issue issue = new Issue(text);
            try {
                fileHandler.saveNewIssue(FILE_PATH, issue);
            } catch (FileNotFoundException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }

        public void validatePendingIssues() {
            ArrayList<Issue> saved = null;
            try {
                saved = fileHandler.readIssues(FILE_PATH);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            boolean hasPending=false;
            for (int i=0;i<saved.size();i++){
                Issue line = saved.get(i);
                if (line.status==Status.PENDING){
                    System.out.printf("%d. %s\n",i+1,line);
                    hasPending=true;
                }
            }if(hasPending){
            System.out.println("Select the issue-number");
            int issueNumber=ui.getChoice();
            Issue issue = saved.get(issueNumber-1);
            System.out.printf("You chose %d. %s\n",issueNumber,issue);
            Status newStatus = ui.getStatusChoice();
            if (newStatus != null) {
                issue.status = newStatus;
                try {
                    fileHandler.deleteContents(FILE_PATH);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                for (int i=0;i<saved.size();i++){
                    Issue linje = saved.get(i);
                    try {
                        fileHandler.saveNewIssue(FILE_PATH, linje);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else System.out.println("No issues awaits validation.");}

        public void sowAllIssues() {


                ArrayList<Issue> saved = null;
                try {
                    saved = fileHandler.readIssues(FILE_PATH);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
               //String linje = scanner.nextLine();
                for (int i = 0; i < saved.size(); i++) {
                    Issue line = saved.get(i);
                    System.out.printf("%d. %s (%s)\n",i+1,line, line.status);
            }
        }}

