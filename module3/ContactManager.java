import java.util.*;

public class ContactManager {
    public static void main(String[] args) {
        HashMap<String, Contact> contacts = new HashMap<>();
        contacts.put("Ada Lovelace", new Contact("Ada Lovelace", "+1 617 555 0101"));
        contacts.put("Alan Turing", new Contact("Alan Turing", "+1 617 555 0102"));
        contacts.put("Grace Hopper", new Contact("Grace Hopper", "+1 617 555 0103"));
        contacts.put("Andrea Fischetti", new Contact("Andrea Fischetti", "+1 617 555 0104"));
        contacts.put("Corinne Skala", new Contact("Corinne Skala", "+1 617 555 0105"));

        Contact found = contacts.get("John");
        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Contact not found.");
        }
    
    ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
    sorted.sort((a, b) -> a.getName().compareTo(b.getName()));
    System.out.println("=== All Contacts ===");
    for (Contact contact : sorted) {
        System.out.println(contact);
    }
    }
}
