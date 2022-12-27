import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contacts> contacts;
    private static Scanner scanner;
    public static void main(String[] args) {
        contacts = new ArrayList<>();

        System.out.println("Hello! Welcome to our Message and Contacts Application");


        while (true) {


        }

    }

    private void showInitialOption(){
        System.out.println("Choose one of the option");
        System.out.println("\t1. Manage Contacts\n\t2. Messages\n\t3. Quit");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();;

        switch (choice){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;

        }
    }

    private void manageMessages() {
    }

    private void manageContacts(){
        System.out.println("Please select one of the option:");
        System.out.println("\t1. Show all contacts\n\t2. Add a new contacts\n\t3. Search for a contacts\n\t4. Delete a contact\n\t5. Go back to previous menu");
        int choice = scanner.nextInt();;
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContacts();
                break;
            case 3:
                searchForContacts();
                break;
            case 4:
                deleteContacts();
                break;
            default:
                showInitialOption();
                break;
        }
    }

    private void deleteContacts() {
        System.out.println("Please enter the contact's name");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter the name");
            deleteContacts();
        }
        else {
            boolean doesExist = false;
            for (Contacts c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);
                }
            }

            if(!doesExist){
                System.out.println("There is no such contact in your phone");
            }
        }

        showInitialOption();
    }

    private void searchForContacts() {
        System.out.println("Please enter the contact name:");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter the name");
            searchForContacts();
        }
        else {
            boolean doesExist = false;
            for (Contacts c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }

            if(!doesExist){
                System.out.println("There is no such contact in your phone");
            }
        }

        showInitialOption();
    }

    private void addNewContacts() {
        System.out.println("Adding a new contact..." +
                "\nPlease Enter the contact's name:");
        String name = scanner.next();
        System.out.println("Please enter contact's number:");
        String number = scanner.next();
        System.out.println("Please enter contact's email:");
        String email = scanner.next();

        if(name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Please enter all the information.");
            addNewContacts();
        }
        else {
            Contacts contact = new Contacts(name, number, email);
            contacts.add(contact);
        }

        showInitialOption();
    }

    private void showAllContacts() {
        for (Contacts c: contacts){
            c.getDetails();
        }
        showInitialOption();
    }

}