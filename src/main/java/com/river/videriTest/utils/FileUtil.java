package com.river.videriTest.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.river.videriTest.dto.NovelDto;

/**
 * 
 * @author riverplant
 *
 */
public class FileUtil {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		show();
	}

	/**
	 * list the novel records
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void show() throws IOException, URISyntaxException  {
		Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("./sample.txt").toURI()),
				StandardCharsets.UTF_8)
		      .forEach(System.out::println);
	}
	/**
	 * 
	 * @param novel_name
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void delete(String novel_name) throws IOException, URISyntaxException {
		        Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("./sample.txt").toURI()),
				StandardCharsets.UTF_8)
		         .filter(i->{
		    	  String[] str = i.split(",");
		    	  return !str[1].trim().equalsIgnoreCase(novel_name);
		    	  }).forEach(System.out::println);
		
		
	}
	
	
	public static void update(NovelDto novelDto) throws IOException, URISyntaxException {
		StringBuffer sb = new StringBuffer();
		List<List<String>> file_content =  Files.lines(Paths.get(Thread.currentThread().getContextClassLoader().getResource("./sample.txt").toURI()),
				StandardCharsets.UTF_8)
		         .map(i -> {
		 			String[] str = i.split(",");
					List<String> list = Arrays.stream(str).collect(Collectors.toList());
					if(Long.parseLong(str[0])==novelDto.getId()) {
						list.add(1,novelDto.getName());
						list.add(2,String.valueOf(novelDto.getAuthor()));
						list.remove(4);
						list.remove(3);
					}
					return list;
				}).collect(Collectors.toList());
		
		file_content.stream().forEach(row -> {
			row.stream().forEach(col -> {
				sb.append(col+",");
			});
			sb.deleteCharAt(sb.length()-1);
			sb.append("\n");
		});
		
		System.out.println(sb.toString());

	}

}
