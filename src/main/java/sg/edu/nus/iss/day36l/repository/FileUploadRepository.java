package sg.edu.nus.iss.day36l.repository;

import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.nus.iss.day36l.model.Post;

@Repository
public class FileUploadRepository {
    public static final String INSERT_POSTS = """
            insert into posts (post_id, comments, picture) 
            values (?, ?, ?)
            """;

    public static final String SELECT_POSTS = """
            select post_id, comments, picture from posts where post_id = ?
            """;

    @Autowired
    private JdbcTemplate template;

    public String upload(MultipartFile file, String comments) {
        String postId = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);

        try {
            byte[] filesByte = file.getBytes();
            template.update(INSERT_POSTS, ps -> {
                ps.setString(1, postId);
                ps.setString(2, comments);
                ps.setBytes(3, filesByte);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while uploading file");
        }
        return postId;
    }

    public Optional<Post> getPostById(String postId) {
        return template.query(SELECT_POSTS, (ResultSet rs) -> {
            if(rs.next()) {
                return Optional.of(Post.populate(rs));
            } else {
                return Optional.empty();
            }
        }, postId);
    }
}
