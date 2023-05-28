package hunnid.com.blog.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import hunnid.com.blog.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3StorageServiceImpl implements StorageService {

    @Value("${access.key}")
    private String accessKey;
    @Value("${secret.key}")
    private String secretKey;
    @Value("${bucket.name}")
    private String bucketName;
    @Value("${folder.name}")
    private String folderName;
    @Value("${region}")
    private String region;

    private AmazonS3 getS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    private String uploadFile(MultipartFile file) throws IOException {
        AmazonS3 s3client = getS3Client();
        String fileName = new StringBuilder()
                .append(folderName)
                .append("/")
                .append(file.getOriginalFilename())
                .toString();
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(inputStream.available());
        s3client.putObject(
                bucketName,
                fileName,
                inputStream,
                metadata
        );
        return new StringBuilder().append("s3Domain").append(fileName).toString();
    }

    @Override
    public boolean deleteFile(List<String> pathFile) {
        String objkeyArr[] = pathFile.toArray(new String[0]);
        DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName)
                .withKeys(objkeyArr);
        AmazonS3 s3client = getS3Client();
        s3client.deleteObjects(delObjReq);
        return true;
    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        List<String> results = new ArrayList<>() ;
        for(MultipartFile file : files){
            results.add(uploadFile(file));
        }
        return results;
    }
}
