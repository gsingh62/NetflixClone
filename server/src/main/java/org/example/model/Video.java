package org.example.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.util.List;

@Data
@Document(indexName = "videos", createIndex = true)
public class Video {

    @Id
    private String id;
    private String name;
    private String year;
    private List<String> genre;
    private String director;
    private List<String> cast;
    private String summaryText;
    private String ratingCount;
    private String ratingValue;
    private String thumbnailUri;
}
