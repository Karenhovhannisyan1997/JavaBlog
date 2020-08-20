package storage.impl;

import exception.PostNotFoundException;
import model.Post;
import storage.PostStorage;

public class PostStorageImpl implements PostStorage {

    private Post[] posts = new Post[16];
    private int size = 0;


    @Override
    public void add(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Post[] array = new Post[size + 32];
        System.arraycopy(posts,0,array,0,posts.length);
        posts = array;
    }

    @Override
    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }
    }

    @Override
    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if(posts[i].getCategory().equals(category)){
                System.out.println(posts[i]);
            }
        }
    }

    @Override
    public Post getPostByTitle(String title) throws PostNotFoundException {
        for (int i = 0; i < size; i++) {
            if(posts[i].getTitle().equals(title)){
                return posts[i];
            }
        }
        throw new PostNotFoundException("Post not found");
    }

    @Override
    public void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if(posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)){
                System.out.println(posts[i]);
            }
        }
    }
}
