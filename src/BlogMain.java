import model.Post;
import storage.PostStorage;
import storage.impl.PostStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class BlogMain implements Commands {

    private static final PostStorage POST_STORAGE = new PostStorageImpl();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            String comm = SCANNER.nextLine();
            int command;
            try {
                command = Integer.parseInt(comm);
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case POSTS_BY_CATEGORY:
                    postsByCategory();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid Number !!!!");
            }
        }
    }

    private static void postsByCategory() {
        System.out.println("Please enter category");
        String category = SCANNER.nextLine();
        POST_STORAGE.searchPostsByKeyword(category);
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
            POST_STORAGE.add(post);
            System.out.println("Post added");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data");
            addPost();
        }
    }
    private static void printCommands() {
        System.out.println("Please enter " + EXIT + " for EXIT");
        System.out.println("Please enter " + ADD_POST + " for ADD_POST");
        System.out.println("Please enter " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Please enter " + POSTS_BY_CATEGORY + " for POSTS_BY_CATEGORY");
    }
}
