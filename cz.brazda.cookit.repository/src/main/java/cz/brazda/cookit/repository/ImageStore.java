package cz.brazda.cookit.repository;

import cz.brazda.cookit.repository.entity.Image;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageStore extends ContentStore<Image, UUID> {
}