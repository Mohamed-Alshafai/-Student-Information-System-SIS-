package sis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SIS {
    private File usersFile = new File("textFiles/users.txt");
    private ArrayList<ArrayList<String>> names;
    private ArrayList<ArrayList<String>> passwords;

    public SIS() {
        names = new ArrayList<ArrayList<String>>();
        passwords = new ArrayList<ArrayList<String>>();
        readUsers();
    }

    public SIS(ArrayList<ArrayList<String>> names, ArrayList<ArrayList<String>> passwords) {
        if (names != null && passwords != null) {
            this.names = names;
            this.passwords = passwords;
        }
        else {
            names = new ArrayList<ArrayList<String>>();
            passwords = new ArrayList<ArrayList<String>>();
        }
    }

    private void readUsers() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(usersFile);
            for (int i = 0; i < 3; ++i) {
                ArrayList<String> name = new ArrayList<String>();
                ArrayList<String> password = new ArrayList<String>();
                int numUsers = Integer.parseInt(scanner.nextLine());
                while (numUsers > 0) {
                    name.add(scanner.nextLine());
                    password.add(scanner.nextLine());
                    --numUsers;
                }
                names.add(name);
                passwords.add(password);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (scanner!=null) {
                scanner.close();
            }
        }
    }
    public ArrayList<String> getNames(int i) {
        return names.get(i);
    }

    public ArrayList<String> getPasswords(int i) {
        return passwords.get(i);
    }
}
