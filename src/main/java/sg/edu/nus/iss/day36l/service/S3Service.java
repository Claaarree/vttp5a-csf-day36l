package sg.edu.nus.iss.day36l.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${do.storage.bucket}")
    private String bucket;

    @Value("${do.storage.endpoint}")
    private String endPoint;

    public String upload (MultipartFile file,
            String comments,
            String postId) throws IOException{
                Map<String, String> metaData = Map.of(
                    "comments", comments,
                    "postId", postId,
                    "uploadDateTime", String.valueOf(System.currentTimeMillis())
                );

                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType(file.getContentType());
                objectMetadata.setContentLength(file.getSize());
                objectMetadata.setUserMetadata(metaData);
                String originalFileName = file.getOriginalFilename();
                String finalName = "";
                if(originalFileName.equals("blob")) {
                    finalName = postId + ".png";
                }

                PutObjectRequest putObjectRequest = 
                        new PutObjectRequest(bucket, 
                                finalName, 
                                file.getInputStream(), 
                                objectMetadata);

                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
                amazonS3.putObject(putObjectRequest);

                String endpointUrl = "https://%s.%s/%s"
                        .formatted(bucket, endPoint, finalName);

                System.out.println(endpointUrl);

                return endpointUrl;
            }
}
