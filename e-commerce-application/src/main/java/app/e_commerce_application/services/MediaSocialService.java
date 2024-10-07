package app.e_commerce_application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.e_commerce_application.entities.MediaSocial;
import app.e_commerce_application.entities.Detail;
import app.e_commerce_application.repositories.MediaSocialRepository;

import java.util.List;
import java.util.Optional;

public interface MediaSocialService {

    List<MediaSocial> getAll();

    Optional<MediaSocial> getById(String id);

    MediaSocial save(MediaSocial mediaSocial);

    void deleteById(String id);

    List<MediaSocial> getByTitle(String title);

    long count();
} 