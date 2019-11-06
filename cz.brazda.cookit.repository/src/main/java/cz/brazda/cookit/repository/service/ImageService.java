package cz.brazda.cookit.repository.service;



import cz.brazda.cookit.repository.entity.Image;
import cz.brazda.cookit.repository.entity.Meal;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by virtual on 22.4.2017.
 */
@Transactional
public interface ImageService extends RepositoryService<Image, AuthorNotFound>{

    void storeImage(Meal meal, byte[] imageStream);

    byte[] loadImage(Long id) throws SQLException;
}
