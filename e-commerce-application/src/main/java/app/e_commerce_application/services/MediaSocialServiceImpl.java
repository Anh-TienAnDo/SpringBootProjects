package app.e_commerce_application.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.e_commerce_application.entities.MediaSocial;
import app.e_commerce_application.repositories.MediaSocialRepository;
import app.e_commerce_application.entities.Detail;

@Service
public class MediaSocialServiceImpl implements MediaSocialService {
    
    @Autowired
    private MediaSocialRepository mediaSocialRepository;

    @Override
    public List<MediaSocial> getAll() {
        return mediaSocialRepository.findAll();
    }

    @Override
    public Optional<MediaSocial> getById(String id) {
        return mediaSocialRepository.findById(id);
    }

    @Override
    public MediaSocial save(MediaSocial mediaSocial) {
        System.out.println("----------------------");
        System.out.println(" MediaSocialService.save() mediaSocial: " + mediaSocial);
        if (mediaSocial.getId() != null) {
            Optional<MediaSocial> mediaSocialOptional = this.getById(mediaSocial.getId());
            if (mediaSocialOptional.isPresent()) {
                MediaSocial existingMediaSocial = mediaSocialOptional.get();
                existingMediaSocial.setTitle(mediaSocial.getTitle());
                existingMediaSocial.setType(mediaSocial.getType());
                existingMediaSocial.setView(mediaSocial.getView());
                existingMediaSocial.setActive(mediaSocial.isActive());
                existingMediaSocial.setDetail(mediaSocial.getDetail());
                return mediaSocialRepository.save(existingMediaSocial);
            }
        }
        return mediaSocialRepository.save(mediaSocial);
    }

    @Override
    public void deleteById(String id) {
        mediaSocialRepository.deleteById(id);
    }

    @Override
    public List<MediaSocial> getByTitle(String title) {
        return mediaSocialRepository.findItemByNameLike(title);
    }

    @Override
    public long count() {
        return mediaSocialRepository.count();
    }
}
