package sg.edu.nus.iss.day36l.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    private String postId;
    private String comments;
    private byte[] picture;

    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public byte[] getPicture() {
        return picture;
    }
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public static Post populate(ResultSet rs) throws SQLException{
        Post post = new Post();
        post.setPostId(rs.getString("post_id"));
        post.setComments(rs.getString("comments"));
        post.setPicture(rs.getBytes("picture"));

        return post;
    }
    
}
