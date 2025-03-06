package sg.edu.nus.iss.day36l.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.nus.iss.day36l.model.Post;
import sg.edu.nus.iss.day36l.repository.FileUploadRepository;

@Service
public class FileUploadService {
    @Autowired
    private FileUploadRepository fileUploadRepository;

    public String uploadFile(MultipartFile file, String comments) 
    throws SQLException, IOException{
        return fileUploadRepository.upload(file, comments);
    }

    public Optional<Post> getPostByID(String postId) {
        return fileUploadRepository.getPostById(postId);
    }
}
