import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;

    private static Scanner scanner;
    private static int id = 0;

    public static void main(String[] args) {
        contacts = new ArrayList<>();
        System.out.println("Welcome");

        showInitialOptions();

    }
    private static void showInitialOptions(){
        System.out.println("Select one: " +
                "\n\t1. Manage contacts" +
                "\n\t2. Messages"  +
                "\n\t3. Quit");

        scanner= new Scanner(System.in);
        int choice = scanner.nextInt();
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
        System.out.println("Select one: " +
                "\n\t1. Show all messages" +
                "\n\t2. Send new message"  +
                "\n\t3. Go back");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("Send message to?");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter name");
            sendNewMessage();
        }
        else{
            boolean doesExist = false;
            for (Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if(!doesExist){
                System.out.println("Message?");
                String text = scanner.next();
                if(text.equals("")){
                    System.out.println("Enter some message");
                    sendNewMessage();
                }else {
                    id++;
                    Message newMessage = new Message(text,name,id);
                    for (Contact c: contacts){
                        if(c.getName().equals(name)){
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            Contact currentContact = c;
                            currentContact.setMessages(newMessages);
                            contacts.remove(c);
                            contacts.add(currentContact);
                        }
                    }
                }

            }
            else{
                System.out.println("No such contact");
            }
        }
        showInitialOptions();
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for(Contact c:contacts){
            allMessages.addAll(c.getMessages());

        }
        if (allMessages.size()>0){
            for(Message m:allMessages){
                m.getDetails();
                System.out.println("*************");
            }
        }
        else{
            System.out.println("No messages found");
        }
        showInitialOptions();
    }

    public static void manageContacts(){
        System.out.println("Select one: " +
                "\n\t1. Show all contacts" +
                "\n\t2. Add new contact"  +
                "\n\t3. Search for a contact"+
                "\n\t4. Delete a contact"+
                "\n\t5. Go back");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }

    }

    private static void deleteContact() {
        System.out.println("Please enter contact name:");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter name");
            deleteContact();
        }
        else{
            boolean doesExist = false;
            for(Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);

                }
            }
            if(!doesExist){
                System.out.println("No contact");
            }
        }
        showInitialOptions();
    }


    private static void searchForContact() {
        System.out.println("Please enter contact name:");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter name");
            searchForContact();
        }
        else{
            boolean doesExist = false;
            for(Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist=true;
                    c.getDetails();
                }
            }
            if(!doesExist){
                System.out.println("No contact");
            }
        }
        showInitialOptions();
    }

    private static void addNewContact() {
        System.out.println("Adding new contact.."+
                "\n please enter contact name:");
        String name =scanner.next();
        System.out.println("Enter contact number:");
        String number = scanner.next();
        System.out.println("Enter contact's email:");
        String email = scanner.next();

        if(name.equals("")||number.equals("")||email.equals("")){
            System.out.println("Please enter all info");
            addNewContact();
        }else{
            boolean doesExist = false;
            for(Contact c: contacts){
                if(c.getName().equals(name)){
                doesExist=true;
                }
            }
            if (doesExist){
                System.out.println("We have contact name" + name + "saved on the device");
                addNewContact();
            }
            else{
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println(name + "added successfully");

            }
        }
        showInitialOptions();
    }

    private static void showAllContacts() {
        for(Contact c:contacts){
            c.getDetails();
            System.out.println("*****************");
        }
        showInitialOptions();
    }

}