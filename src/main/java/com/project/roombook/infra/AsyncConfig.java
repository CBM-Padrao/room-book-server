package com.project.roombook.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "emailTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Número de threads iniciais no pool
        executor.setMaxPoolSize(10); // Número máximo de threads
        executor.setQueueCapacity(50); // Capacidade da fila de tarefas
        executor.setThreadNamePrefix("EmailSender-");
        executor.initialize();
        return executor;
    }
}
