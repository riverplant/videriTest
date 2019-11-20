package com.river.videriTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.ParseException;

import com.river.videriTest.domain.Seckill;
import com.river.videriTest.repository.SeckillRepository;

/**
 * 
 * @author riverplant
 *
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer{
    
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }

    public static void main(String[] args) {
        Date start_date = new Date();
        LocalDate localDate=start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date end_date = java.sql.Date.valueOf(localDate.plusDays(1));   
        System.out.println("startTime="+start_date.getTime());
        System.out.println("end_date="+end_date.getTime());
		//SpringApplication.run(App.class, args);
	}

	@Bean
	CommandLineRunner initializeDatabase(SeckillRepository seckillRepository) throws ParseException {
	    Date start_date = new Date();
	    LocalDate localDate=start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    Date end_date = java.sql.Date.valueOf(localDate.plusDays(2));		
	    
	    return (evt) -> {
	      
//		    seckillRepository.saveAll(Arrays.asList(
//		            new Seckill("1000mioashaiphone80",100,start_date,end_date ), 
//		            new Seckill("500mioashaipad40",200,start_date,end_date),
//		            new Seckill("300mioashaixiaomi60",300,start_date,end_date),
//		            new Seckill("200mioashahongminote20",400,start_date,end_date),
//		            new Seckill("3000mioashaiphone80",10,start_date,end_date)));

		};
		
	}


}
