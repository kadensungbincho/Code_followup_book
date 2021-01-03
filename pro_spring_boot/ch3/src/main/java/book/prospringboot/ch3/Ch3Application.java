package book.prospringboot.ch3;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.logging.Logger;

@SpringBootApplication
public class Ch3Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Ch3Application.class);
        app.setBanner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                out.print("\n\n\tthis is my own banner!\n\n".toUpperCase());
            }
        });
        app.run(args);
    }
}


