package com.river.videriTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author riverplant
 *
 */
@SpringBootApplication
@EnableSwagger2
public class App extends SpringBootServletInitializer{
    
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }

    public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

//	@Bean
//	CommandLineRunner initializeDatabase(SeckillRepository seckillRepository) throws ParseException {
//	    Date start_date = new Date();
//	    LocalDate localDate=start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	    Date end_date = java.sql.Date.valueOf(localDate.plusDays(1));		
//	    
//	    return (evt) -> {
//	      
//		    seckillRepository.saveAll(Arrays.asList(
//		            new Seckill("1000mioashaiphone7",100,start_date,end_date ), 
//		            new Seckill("500mioashaipad3",200,start_date,end_date),
//		            new Seckill("300mioashaixiaomi5",300,start_date,end_date),
//		            new Seckill("200mioashahongminote2",400,start_date,end_date),
//		            new Seckill("3000mioashaiphone8",10,start_date,end_date)));
//
//		};
//		
//	}


}
