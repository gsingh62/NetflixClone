package org.example.service;


import org.example.model.Video;
import org.example.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> search(String query) {
        return videoRepository.searchVideos(query);
    }

    public Video saveVideo(Video Video) {
        return videoRepository.save(Video);
    }
}
