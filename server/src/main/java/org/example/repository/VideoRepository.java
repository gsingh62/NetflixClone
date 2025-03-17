package org.example.repository;


import org.example.model.Video;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.annotations.Query;
import java.util.List;

@Repository
public interface VideoRepository extends ElasticsearchRepository<Video, String> {
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"name\", \"year\", \"genre\", \"director\",\"cast\",\"summaryText\",\"ratingValue\"]}}")
    List<Video> searchVideos(String query);
}
