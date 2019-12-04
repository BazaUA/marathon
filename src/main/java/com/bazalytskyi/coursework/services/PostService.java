package com.bazalytskyi.coursework.services;

import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.entities.PostEntity;
import com.bazalytskyi.coursework.repository.PostRepository;
import com.bazalytskyi.coursework.transformer.PostTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostTransformer postTransformer;

    public List<PostDTO> getAll() {
        return postTransformer.toDtoList(postRepository.findAll());
    }

    public PostDTO getById(Long id) {
        return postTransformer.toDto(postRepository.getOne(id));
    }

    public PostDTO create(PostDTO dto) {
        PostEntity entity = postTransformer.toEntity(dto);
        return postTransformer.toDto(postRepository.save(entity));
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}
