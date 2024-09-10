package com.twittercasero.tweets.infrastructure.config;

import com.twittercasero.tweets.application.port.input.TweetInputPort;
import com.twittercasero.tweets.application.port.output.TweetOutputPort;
import com.twittercasero.tweets.application.useCases.AddLikeOrDislikeUseCase;
import com.twittercasero.tweets.application.useCases.AddReplyUseCase;
import com.twittercasero.tweets.application.useCases.AddRetweetUseCase;
import com.twittercasero.tweets.application.useCases.CreateTweetUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByHashtagUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByIdUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByMentionsUseCase;
import com.twittercasero.tweets.application.useCases.GetTweetByOwnersUseCase;
import com.twittercasero.tweets.application.useCases.impl.AddLikeOrDislikeUseCaseImpl;
import com.twittercasero.tweets.application.useCases.impl.AddReplyUseCaseImpl;
import com.twittercasero.tweets.application.useCases.impl.AddRetweetUseCaseImpl;
import com.twittercasero.tweets.application.useCases.impl.CreateTweetUseCaseImpl;
import com.twittercasero.tweets.application.useCases.impl.GetTweetByHashtagUseCaseImpl;
import com.twittercasero.tweets.application.useCases.impl.GetTweetByIdUseCaseImpl;
import com.twittercasero.tweets.application.useCases.impl.GetTweetByMentionsUseCaseImpl;
import com.twittercasero.tweets.application.useCases.impl.GetTweetByOwnersUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class TweetConfig {

    @Bean
    CreateTweetUseCase createTweetUseCase(TweetInputPort tweetInputPort) {
        return new CreateTweetUseCaseImpl(tweetInputPort);
    }

    @Bean
    GetTweetByHashtagUseCase getTweetByHashtagUseCase(TweetOutputPort tweetOutputPort) {
        return new GetTweetByHashtagUseCaseImpl(tweetOutputPort);
    }

    @Bean
    GetTweetByMentionsUseCase getTweetByMentionsUseCase(TweetOutputPort tweetOutputPort) {
        return new GetTweetByMentionsUseCaseImpl(tweetOutputPort);
    }

    @Bean
    GetTweetByOwnersUseCase getTweetByOwnersUseCase(TweetOutputPort tweetOutputPort) {
        return new GetTweetByOwnersUseCaseImpl(tweetOutputPort);
    }

    @Bean
    AddReplyUseCase addReplyUseCase(TweetInputPort tweetInputPort, TweetOutputPort tweetOutputPort) {
        return new AddReplyUseCaseImpl(tweetInputPort, tweetOutputPort);
    }

    @Bean
    AddLikeOrDislikeUseCase addLikeOrDislikeUseCase(TweetInputPort tweetInputPort, TweetOutputPort tweetOutputPort) {
        return new AddLikeOrDislikeUseCaseImpl(tweetInputPort, tweetOutputPort);
    }

    @Bean
    AddRetweetUseCase addRetweetUseCase(TweetInputPort tweetInputPort, TweetOutputPort tweetOutputPort) {
        return new AddRetweetUseCaseImpl(tweetInputPort, tweetOutputPort);
    }

    @Bean
    GetTweetByIdUseCase getTweetByIdUseCase(TweetOutputPort tweetOutputPort) {
        return new GetTweetByIdUseCaseImpl(tweetOutputPort);
    }


}
