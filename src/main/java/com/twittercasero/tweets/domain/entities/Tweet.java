package com.twittercasero.tweets.domain.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "tweets")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

    @Id
    private String id;

    @NotBlank(message = "Message name cannot be empty")
    private String message;

    @NotBlank(message = "Owner name cannot be empty")
    private String owner;

    @CreatedDate
    private Date posted;

    @Builder.Default
    private List<String> mentions = new ArrayList<>();

    @Builder.Default
    private List<String> hashtags = new ArrayList<>();

    @Setter
    private int likes;

    @Setter
    private int retweets;

    @Builder.Default
    private List<Reply> replies = new ArrayList<>();

    @Builder.Default
    private boolean edited = false;

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Reply {
        private String owner;
        private String message;
        @CreatedDate
        private Date posted;

    }

}
