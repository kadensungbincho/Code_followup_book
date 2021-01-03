package book.prospringboot.ch2;

import book.prospringboot.ch2.domain.Journal;
import book.prospringboot.ch2.repository.JournalRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ch2Application {

    @Bean
    InitializingBean saveData(JournalRepository repo) {
        return () -> {
            repo.save(new Journal("Get to know Spring Boot", "Today I will learn Spring Boot", "01/01/2016"));
            repo.save(new Journal("Spring Boot Reading", "Read more about Spring", "02/01/2016"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Ch2Application.class, args);
    }

}
