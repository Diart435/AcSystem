package com.example.AcSystemConsumer.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeduplicationService {
    private final RedisTemplate<String, String> redisTemplate;

    private static final Duration TIME_WINDOW = Duration.ofSeconds(15);
    private static final String KEY_PREFIX = "dedup:key:";

    public boolean isDuplicate(String key){
        Boolean exists = redisTemplate.hasKey(KEY_PREFIX + key);
        return Boolean.TRUE.equals(exists);
    }

    public void markAsProcessed(String key) {
        redisTemplate.opsForValue().set(
                KEY_PREFIX + key,
                String.valueOf(System.currentTimeMillis()),
                TIME_WINDOW
        );
        log.debug("Marked as processed: {}", key);
    }

}
