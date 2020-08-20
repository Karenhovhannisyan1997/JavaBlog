package storage;

import exception.PostNotFoundException;
import model.Post;

public interface PostStorage {

    void add(Post post);

    void printAllPosts();

    void printPostsByCategory(String category);

    Post getPostByTitle(String title) throws PostNotFoundException;

    void searchPostsByKeyword(String keyword);
}
