package com.river.videriTest;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.river.videriTest.domain.Novel;
import com.river.videriTest.domain.Writer;
import com.river.videriTest.repository.NovelRepository;
import com.river.videriTest.repository.WriterRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author riverplant
 *
 */
@SpringBootApplication
@EnableSwagger2
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	CommandLineRunner initializeDatabase(WriterRepository writerRepository, NovelRepository novelRepository) {
		return (evt) -> {
			
			writerRepository.saveAll(Arrays.asList(new Writer("Victor", "Hugo"), new Writer("Marie-Henri", "Beyle")));
			/**
			 * import the sample file
			 */
			Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("./sample.txt").toURI()),
					StandardCharsets.UTF_8)
			      .map(line -> line.split(",")).forEachOrdered(line -> {
						long authorId = Long.valueOf(line[2].trim()).longValue();
						if (writerRepository.findById(authorId).isPresent()) {
							novelRepository.save(new Novel(authorId, line[1]));
						}
					});

		};
	}

}
