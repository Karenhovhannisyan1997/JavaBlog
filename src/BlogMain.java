import model.Post;
import model.User;
import storage.PostStorage;
import storage.UserStorage;
import storage.impl.PostStorageImpl;
import storage.impl.UserStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class BlogMain implements UserCommands, Command {

    private static final PostStorage POST_STORAGE = new PostStorageImpl();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static User user = null;
    private static boolean isRun = true;
    private static final UserStorage USER_STORAGE = new UserStorageImpl();

    public static void main(String[] args) {

        while (isRun) {
            printCommands();
            mainPage();
        }
    }

    private static void mainPage() {
        POST_STORAGE.printAllPosts();
        String comm = SCANNER.nextLine();
        int command;
        try {
            command = Integer.parseInt(comm);
        } catch (NumberFormatException e) {
            command = -1;
        }
        switch (command) {
            case Command.EXIT:
                isRun = false;
                break;
            case LOGIN:
                login();
                break;
            case REGISTER:
                register();
                break;
            case Command.SEARCH_POST:
                searchPost();
                break;
            default:
                System.out.println("Invalid Number !!!!");
        }
    }

    private static void register() {

        System.out.println("Please input your data (name,surname,email,password)");
        String userStr = SCANNER.nextLine();
        String[] split = userStr.split(",");
        User registredUser = new User();

        try {
            if (USER_STORAGE.isEmailUser(split[2])) {
                System.out.println("email incorrect please input urish email");
                register();
            } else {
                registredUser.setName(split[0]);
                registredUser.setSurname(split[1]);
                registredUser.setEmail(split[2]);
                registredUser.setPassword(split[3]);
                USER_STORAGE.add(registredUser);
                System.out.println("Congrutulation");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data please try again");
            register();
        }
    }

    private static void login() {
        System.out.println("Please input your email");
        String email = SCANNER.nextLine();
        System.out.println("Please input your password");
        String password = SCANNER.nextLine();
        user = USER_STORAGE.getUserByEmailAndPassword(email, password);
        if (user == null) {
            System.out.println("Invalid Login or Password Please try again");
            login();
        } else {
            userPage();
        }
    }

    private static void userPage() {
        POST_STORAGE.printAllPosts();
        while (user != null) {
            userCommand();
            String comm = SCANNER.nextLine();
            int command;
            try {
                command = Integer.parseInt(comm);
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case UserCommands.EXIT:
                    isRun = false;
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case UserCommands.SEARCH_POST:
                    searchPost();
                    break;
                case POSTS_BY_CATEGORY:
                    postsByCategory();
                    break;
                case LOGOUT:
                    user = null;
                    break;
                default:
                    System.out.println("Invalid Number !!!!");
            }
        }
    }

    private static void userCommand() {
        System.out.println("Please enter " + UserCommands.EXIT + " for EXIT");
        System.out.println("Please enter " + ADD_POST + " for ADD_POST");
        System.out.println("Please enter " + UserCommands.SEARCH_POST + " for SEARCH_POST");
        System.out.println("Please enter " + POSTS_BY_CATEGORY + " for POSTS_BY_CATEGORY");
        System.out.println("Please enter " + LOGOUT + " for LOGOUT");
    }

    private static void postsByCategory() {
        System.out.println("Please enter category");
        String category = SCANNER.nextLine();
        POST_STORAGE.printPostsByCategory(category);
    }

    private static void searchPost() {

        System.out.println("Please enter keyword");
        String keyword = SCANNER.nextLine();
        POST_STORAGE.searchPostsByKeyword(keyword);
    }

    private static void addPost() {

        System.out.println("Please enter (title,text,category");
        String postData = SCANNER.nextLine();
        String[] split = postData.split(",");
        try {
            Post post = new Post();
            post.setTitle(split[0]);
            post.setText(split[1]);
            post.setCategory(split[2]);
            post.setCreatedDate(new Date());
            post.setUser(user);
            POST_STORAGE.add(post);
            System.out.println("Post added");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data");
            addPost();
        }
    }

    private static void printCommands() {
        System.out.println("Please enter " + Command.EXIT + " for EXIT");
        System.out.println("Please enter " + LOGIN + " for LOGIN");
        System.out.println("Please enter " + REGISTER + " for REGISTER");
        System.out.println("Please enter " + Command.SEARCH_POST + " for SEARCH_POST");
    }
}
