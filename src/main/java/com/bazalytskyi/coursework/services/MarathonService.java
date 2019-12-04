package com.bazalytskyi.coursework.services;

import com.bazalytskyi.coursework.dto.MarathonDTO;
import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.entities.MarathonEntity;
import com.bazalytskyi.coursework.entities.UserEntity;
import com.bazalytskyi.coursework.repository.MarathonRepository;
import com.bazalytskyi.coursework.repository.PostRepository;
import com.bazalytskyi.coursework.transformer.MarathonTransformer;
import com.bazalytskyi.coursework.transformer.PostTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarathonService {
    @Autowired
    private UserService userService;
    @Autowired
    private MarathonRepository marathonRepository;
    @Autowired
    private MarathonTransformer marathonTransformer;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostTransformer postTransformer;

    public List<MarathonDTO> getAll() {
        return marathonTransformer.toDtoList(marathonRepository.findAll());
    }

    public MarathonDTO getById(long id) {
        return marathonTransformer.toDto(marathonRepository.findOne(id));
    }

    public MarathonDTO save(MarathonDTO dto) {
        MarathonEntity entity = marathonRepository.save(marathonTransformer.toEntity(dto));
        return marathonTransformer.toDto(entity);
    }

    public MarathonDTO update(MarathonDTO dto) {
        MarathonEntity entity = marathonRepository.findOne(dto.getId());
        MarathonEntity savedEntity = marathonRepository.save(marathonTransformer.updateEntity(entity, dto));
        return marathonTransformer.toDto(savedEntity);
    }

    public void deleteById(long id) {
        marathonRepository.delete(id);
    }

    public void enrollUser(Long marathonId, Long userId) {
        MarathonEntity marathonEntity = marathonRepository.findOne(marathonId);
        UserEntity userEntity = userService.getUserById(userId);
        marathonEntity.getUsers().add(userEntity);
        userEntity.getMarathons().add(marathonEntity);
    }

    public List<PostDTO> getMarathonPosts(Long id) {
        return postTransformer.toDtoList(postRepository.findAllByMarathonId(id));
    }
}
