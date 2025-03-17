package org.example.controller;

import org.example.model.Video;
import org.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public Video saveVideo(@RequestBody Video video) {
        return videoService.saveVideo(video);
    }

    @GetMapping("/search")
    public List<Video> search(@RequestParam String query) {
        return videoService.search(query);
    }
}
