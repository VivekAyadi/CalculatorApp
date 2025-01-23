import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

abstract class LibraryItem {
    String title;
    String author;
    String category;
    boolean isCheckedOut;

    public LibraryItem(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isCheckedOut = false;
    }

    public abstract String getItemType();
    
    @Override
    public String toString() {
        return getItemType() + " [Title: " + title + ", Author: " + author + ", Category: " + category + "]";
    }
}

class Book extends LibraryItem {
    public Book(String title, String author, String category) {
        super(title, author, category);
    }

    @Override
    public String getItemType() {
        return "Book";
    }
}

class Magazine extends LibraryItem {
    public Magazine(String title, String author, String category) {
        super(title, author, category);
    }

    @Override
    public String getItemType() {
        return "Magazine";
    }
}

class DVD extends LibraryItem {
    public DVD(String title, String author, String category) {
        super(title, author, category);
    }

    @Override
    public String getItemType() {
        return "DVD";
    }
}

class User {
    String username;
    String password;
    String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

class LibrarySystem {
    List<LibraryItem> libraryItems;
    List<User> users;
    Map<String, LibraryItem> checkedOutItems;

    public LibrarySystem() {
        this.libraryItems = new ArrayList<>();
        this.users = new ArrayList<>();
        this.checkedOutItems = new HashMap<>();
        loadData();
    }

    public void loadData() {
        try (ObjectInputStream itemStream = new ObjectInputStream(new FileInputStream("library_items.dat"));
             ObjectInputStream userStream = new ObjectInputStream(new FileInputStream("users.dat"))) {
            libraryItems = (List<LibraryItem>) itemStream.readObject();
            users = (List<User>) userStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        }
    }

    public void saveData() {
        try (ObjectOutputStream itemStream = new ObjectOutputStream(new FileOutputStream("library_items.dat"));
             ObjectOutputStream userStream = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            itemStream.writeObject(libraryItems);
            userStream.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public void addLibraryItem(LibraryItem item) {
        libraryItems.add(item);
        saveData();
    }

    public void registerUser(User user) {
        users.add(user);
        saveData();
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void checkOutItem(String title, String username) {
        for (LibraryItem item : libraryItems) {
            if (item.title.equalsIgnoreCase(title) && !item.isCheckedOut) {
                item.isCheckedOut = true;
                checkedOutItems.put(username, item);
                saveData();
                System.out.println("Checked out " + item);
                return;
            }
        }
        System.out.println("Item not available or already checked out.");
    }

    public void returnItem(String title, String username) {
        if (checkedOutItems.containsKey(username)) {
            LibraryItem item = checkedOutItems.get(username);
            item.isCheckedOut = false;
            checkedOutItems.remove(username);
            saveData();
            System.out.println("Returned " + item);
            return;
        }
        System.out.println("No item checked out by " + username);
    }

    public void manageOverdueFines() {
        // In a real-world scenario, you would track due dates and compute fines here.
        // For simplicity, assume fines are $1 per overdue day.
        for (Map.Entry<String, LibraryItem> entry : checkedOutItems.entrySet()) {
            LibraryItem item = entry.getValue();
            System.out.println("Fine for " + item.title + ": $1/day");
        }
    }

    public void searchItem(String keyword) {
        for (LibraryItem item : libraryItems) {
            if (item.title.toLowerCase().contains(keyword.toLowerCase()) ||
                item.author.toLowerCase().contains(keyword.toLowerCase()) ||
                item.category.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(item);
            }
        }
    }

    public void displayItems() {
        for (LibraryItem item : libraryItems) {
            System.out.println(item);
        }
    }
}
public class libraryManagement {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();

        // Adding some users
        librarySystem.registerUser(new User("admin", "admin123", "Librarian"));
        librarySystem.registerUser(new User("john", "password", "Patron"));

        // Adding some items
        librarySystem.addLibraryItem(new Book("Java Programming", "John Doe", "Programming"));
        librarySystem.addLibraryItem(new Magazine("Tech Monthly", "Jane Smith", "Technology"));
        librarySystem.addLibraryItem(new DVD("Java for Beginners", "Jake Black", "Education"));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library System");

        // Login
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = librarySystem.authenticateUser(username, password);
        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        System.out.println("Welcome " + username + " (" + user.role + ")");

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            if (user.role.equals("Librarian")) {
                System.out.println("1. Add New Item");
                System.out.println("2. View All Items");
                System.out.println("3. Search Item");
                System.out.println("4. Manage Overdue Fines");
                System.out.println("5. Logout");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter item type (Book/Magazine/DVD): ");
                        String itemType = scanner.nextLine();
                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter category: ");
                        String category = scanner.nextLine();
                        if (itemType.equalsIgnoreCase("Book")) {
                            librarySystem.addLibraryItem(new Book(title, author, category));
                        } else if (itemType.equalsIgnoreCase("Magazine")) {
                            librarySystem.addLibraryItem(new Magazine(title, author, category));
                        } else if (itemType.equalsIgnoreCase("DVD")) {
                            librarySystem.addLibraryItem(new DVD(title, author, category));
                        }
                        break;
                    case 2:
                        librarySystem.displayItems();
                        break;
                    case 3:
                        System.out.print("Enter search keyword: ");
                        String keyword = scanner.nextLine();
                        librarySystem.searchItem(keyword);
                        break;
                    case 4:
                        librarySystem.manageOverdueFines();
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("1. View All Items");
                System.out.println("2. Search Item");
                System.out.println("3. Check Out Item");
                System.out.println("4. Return Item");
                System.out.println("5. Logout");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        librarySystem.displayItems();
                        break;
                    case 2:
                        System.out.print("Enter search keyword: ");
                        String keyword = scanner.nextLine();
                        librarySystem.searchItem(keyword);
                        break;
                    case 3:
                        System.out.print("Enter item title to check out: ");
                        String itemTitle = scanner.nextLine();
                        librarySystem.checkOutItem(itemTitle, username);
                        break;
                    case 4:
                        System.out.print("Enter item title to return: ");
                        String returnTitle = scanner.nextLine();
                        librarySystem.returnItem(returnTitle, username);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
        scanner.close();
    }
}
