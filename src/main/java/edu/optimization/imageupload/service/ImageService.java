package edu.optimization.imageupload.service;

import edu.optimization.imageupload.entity.Image;
import edu.optimization.imageupload.repository.ImageRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public String addImage(String title, MultipartFile file) throws IOException {
        Image image = new Image(title);
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image.setFileContentType(file.getContentType());
        image = imageRepository.insert(image);
        return image.getId();
    }

    public Image getPhoto(String id) {
        return imageRepository.findById(id).get();
    }

}
