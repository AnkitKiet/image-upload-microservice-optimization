package edu.optimization.imageupload.repository;

import edu.optimization.imageupload.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image,String> {


}
