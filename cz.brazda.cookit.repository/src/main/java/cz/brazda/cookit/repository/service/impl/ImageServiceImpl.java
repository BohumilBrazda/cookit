package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.repository.ImageRepository;
import cz.brazda.cookit.repository.entity.Image;
import cz.brazda.cookit.repository.entity.Meal;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import cz.brazda.cookit.repository.service.ImageService;
import cz.brazda.cookit.repository.ImageStore;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by virtual on 23.4.2017.
 */
@Service
@Transactional
public class ImageServiceImpl extends RepositoryServiceImpl<Image, ImageRepository, AuthorNotFound> implements ImageService {


    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        repository = imageRepository;
        exception = new AuthorNotFound();
    }


    @Override
    protected void updateEntity(Image updatedElement, Image originEntity) {

    }

    @Override
    public <V> V findById(Long id, Class<V> type) {
        return repository.findById(id, type);
    }


    @Override
    public void storeImage(Meal meal, byte[] imageStream) {
        Image image = new Image();
        image.setImage(imageStream);
        image.setSourceId(meal);
        repository.save(image);

    }

    @Override
    public byte[] loadImage(Long id) throws SQLException {
        Image image = repository.getOne(id);
        return image.getImage();
    }


}
