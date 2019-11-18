package com.river.videriTest;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.river.videriTest.repository.SeckillRepository;

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
	CommandLineRunner initializeDatabase(SeckillRepository seckillRepository) throws ParseException {
//	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	    String dateStart = "2019-11-18 00:00:00";
//	    String dateEnd = "2019-11-19 00:00:00";
	    Date start_date = new Date();
	    LocalDate localDate=start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	   // Date end_date = simpleDateFormat.parse(dateEnd);
	    Date end_date = java.sql.Date.valueOf(localDate.plusDays(1));		
	    
	    return (evt) -> {
	      /**
		    seckillRepository.saveAll(Arrays.asList(
		            new Seckill("1000mioashaiphone7",100,start_date,end_date ), 
		            new Seckill("500mioashaipad3",200,start_date,end_date),
		            new Seckill("300mioashaixiaomi5",300,start_date,end_date),
		            new Seckill("200mioashahongminote2",400,start_date,end_date),
		            new Seckill("3000mioashaiphone8",10,start_date,end_date)));
//			writerRepository.saveAll(Arrays.asList(new Writer("Victor", "Hugo"), new Writer("Marie-Henri", "Beyle")));
//		
//			Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("./sample.txt").toURI()),
//					StandardCharsets.UTF_8)
//			      .map(line -> line.split(",")).forEachOrdered(line -> {
//						long authorId = Long.valueOf(line[2].trim()).longValue();
//						if (writerRepository.findById(authorId).isPresent()) {
//							novelRepository.save(new Novel(authorId, line[1]));
//						}
//					});
*/
		};
		
	}


}
