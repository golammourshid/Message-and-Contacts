import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contacts> contacts;
    private static Scanner scanner;
    private static int id = 0;
    public static void main(String[] args) {
        contacts = new ArrayList<>();

        System.out.println("Hello! Welcome to our Message and Contacts Application");

        showInitialOption();
    }

    private static void showInitialOption(){
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

    private static void manageMessages() {
        System.out.println("Please select one:" +
                "\n\t1. Show all messages" +
                "\n\t2. Send a new message" +
                "\n\t3. Go back");

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
            default:
                showInitialOption();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("Who are you going to send a message?");

        String name = scanner.next();
        if(name.equals("")) {
            System.out.println("Please enter the name of the contact.");
            sendNewMessage();
        }else {
            boolean doesExist = false;
            for (Contacts c:contacts) {
                if (c.getName().equals(name)) {
                    doesExist = true;
                    break;
                }
            }

            if(doesExist){
                System.out.println("What are you going to say?");
                String text = scanner.next();

                if(text.equals("")) {
                    System.out.println("Please enter some message");
                    sendNewMessage();
                }else {
                    id++;
                    Message message = new Message(text, name, id);
                    for (Contacts c: contacts) {
                        if (c.getName().equals(name)) {
                            ArrayList<Message> messages = c.getMessages();
                            messages.add(message);
                            c.setMessages(messages);
                            break;
                        }
                    }
                    System.out.println("Successfully delivered the message");
                }
            }
            else {
                System.out.println("There is no such Contacts");
            }
        }

        manageMessages();
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contacts c: contacts) {
            allMessages.addAll(c.getMessages());
        }
        if(allMessages.size()>0) {
            for (Message m: allMessages) {
                m.getDetails();
                System.out.println("*****************");
            }
        }else {
            System.out.println("You don't have any message");
        }

        manageMessages();
    }

    private static void manageContacts(){
        System.out.println("Please select one of the option:");
        System.out.println("\t1. Show all contacts\n\t2. Add a new contacts\n\t3. Search for a contacts\n\t4. Delete a contact\n\t5. Go back to previous menu");

        int choice = scanner.nextInt();

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

    private static void deleteContacts() {
        System.out.println("Please enter the contact's name");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter the name");
            deleteContacts();
        }else {
            boolean doesExist = false;
            for (Contacts c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);
                    System.out.println(name + " removed successfully.");
                    break;
                }
            }

            if(!doesExist){
                System.out.println("There is no such contact in your phone");
            }
        }
        manageContacts();
    }

    private static void searchForContacts() {
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

        manageContacts();
    }

    private static void addNewContacts() {
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
            boolean doesExist = false;
            for (Contacts c: contacts){
                if (c.getName().equals(name)) {
                    doesExist = true;
                    break;
                }
            }

            if(doesExist){
                System.out.println("This contact name" + name + " already exist.");
                addNewContacts();
            }
            else {
                Contacts contact = new Contacts(name, number, email);
                contacts.add(contact);

                System.out.println(name + " added successfully.");
            }

        }

        manageContacts();
    }

    private static void showAllContacts() {
        if (contacts.size()>0) {
            for (Contacts c: contacts){
                c.getDetails();
                System.out.println("*****************");
            }
        }else {
            System.out.println("You don't have any contact");
        }

        manageContacts();
    }

}