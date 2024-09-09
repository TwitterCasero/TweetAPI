package com.twittercasero.tweets.infrastructure.controllers;

import com.twittercasero.tweets.application.useCases.GetTweetByHashtagUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByMentionsUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByOwnersUseCase;
import com.twittercasero.tweets.domain.entities.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TweetController.class)
public class TweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetTweetByOwnersUseCase getTweetByOwnersUseCase;

    @MockBean
    private GetTweetByHashtagUseCase getTweetByHashtagUseCase;

    @MockBean
    private GetTweetByMentionsUseCase getTweetByMentionsUseCase;

    @Test
    public void testGetTweetByOwnersReturnsTweets() throws Exception {
        List<Tweet> tweets = List.of(Tweet.builder().build());
        when(getTweetByOwnersUseCase.apply(anyList(), any())).thenReturn(tweets);

        mockMvc.perform(get("/tweets")
                        .param("owners", "user1,user2")
                        .param("page", "1")
                        .param("size", "25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists());

        verify(getTweetByOwnersUseCase).apply(anyList(), any());
    }

    @Test
    public void testGetTweetByOwnersReturnsNotFound() throws Exception {
        when(getTweetByOwnersUseCase.apply(anyList(), any())).thenReturn(null);

        mockMvc.perform(get("/tweets")
                        .param("owners", "user1,user2")
                        .param("page", "1")
                        .param("size", "25"))
                .andExpect(status().isNotFound());

        verify(getTweetByOwnersUseCase).apply(anyList(), any());
    }

    @Test
    public void testGetTweetByMentionReturnsTweets() throws Exception {
        List<Tweet> tweets = List.of(Tweet.builder().build(), Tweet.builder().build());

        when(getTweetByMentionsUseCase.apply(anyString(), any())).thenReturn(tweets);

        mockMvc.perform(get("/tweets/{nickname}/mentions", "user1")
                        .param("page", "1")
                        .param("size", "25"))
                .andExpect(status().isOk());

        verify(getTweetByMentionsUseCase).apply(eq("user1"), any());
    }

    @Test
    public void testGetTweetByHashtagReturnsTweets() throws Exception {
        List<Tweet> tweets = List.of(Tweet.builder().build(), Tweet.builder().build());
        when(getTweetByHashtagUseCase.apply(anyString(), any())).thenReturn(tweets);

        mockMvc.perform(get("/tweets/{hashtag}/hashtag", "tech")
                        .param("page", "1")
                        .param("size", "25"))
                .andExpect(status().isOk());

        verify(getTweetByHashtagUseCase).apply(eq("tech"), any());
    }


}
