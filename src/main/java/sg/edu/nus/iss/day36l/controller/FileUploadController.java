package sg.edu.nus.iss.day36l.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import sg.edu.nus.iss.day36l.model.Post;
import sg.edu.nus.iss.day36l.service.FileUploadService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;


@Controller
public class FileUploadController {
    public static final String BASE64_PREFIX = "data:image/png;base64,";
    
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping(path="/api/upload",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postMethodName(@RequestPart("file") MultipartFile file, 
    @RequestPart("comments") String comments) {
        String postId = "";

        try {
            postId = fileUploadService.uploadFile(file, comments);
            System.out.println("Post ID: " + postId);
        } catch (SQLException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        JsonObject payload = Json.createObjectBuilder()
                .add("postId", postId)
                .build();
        return ResponseEntity.ok().body(payload.toString());
    }

    @GetMapping(path="/api/get-image/{post-id}")
    public ResponseEntity<String> getImage(@PathVariable String postId) 
    throws SQLException{
        Optional<Post> r = this.fileUploadService.getPostByID(postId);
        Post p = r.get();
        String encodedString = Base64.getEncoder().encodeToString(p.getPicture());
        JsonObject payload = Json.createObjectBuilder()
                .add("image", BASE64_PREFIX + encodedString)
                .build();
        return ResponseEntity.ok().body(payload.toString());
    }
}
