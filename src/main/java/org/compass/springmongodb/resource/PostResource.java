package org.compass.springmongodb.resource;

import org.compass.springmongodb.domain.Post;
import org.compass.springmongodb.domain.User;
import org.compass.springmongodb.dto.UserDTO;
import org.compass.springmongodb.resource.util.URL;
import org.compass.springmongodb.services.PostService;
import org.compass.springmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = service.fullsearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}
