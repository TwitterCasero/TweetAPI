package com.twittercasero.tweets.application.useCases;

import java.util.function.BiConsumer;

public interface AddLikeOrDislikeUseCase extends BiConsumer<String, Boolean> {
}
