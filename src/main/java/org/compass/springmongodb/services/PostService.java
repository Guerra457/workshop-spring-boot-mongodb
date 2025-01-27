package org.compass.springmongodb.services;

import org.compass.springmongodb.domain.Post;
import org.compass.springmongodb.domain.User;
import org.compass.springmongodb.dto.UserDTO;
import org.compass.springmongodb.repository.PostRepository;
import org.compass.springmongodb.repository.UserRepository;
import org.compass.springmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repo.searchTitle(text);
    }
}
