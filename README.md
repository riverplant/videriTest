# videriTest
test of the videri

Java
All the application code is available under the "com.river.videriTest" package and important classes are:

App.java ==> Main application
NovelController.java ==> REST Endpoint
WriterController.java ==> REST Endpoint
DomainImpl.java, Writer.java  Domain class, representing an Writer.
DomainImpl.java, Novel.java  Domain class, representing an Novel.

Once started, you should be able to access the following endpoint: http://localhost:8088/novel

1.make any valid assumptions, such writer info and their novel info.

writer info: http://localhost:8088/writer
novel info: http://localhost:8088/novel

2.design the database schema
  writer                                  novel
 * +---------------+---------+           +---------------+---------+
 * | id            | int     |<----+  +->| id            | int     |
 * | first_name    | varchar |     |  |  | name         | varchar |
 * | last_name     | varchar |     |  |  | author_id       | int    |
 * +---------------+---------+  |  |  |  +---------------+---------+
 
 3.design the file structure for import, and create an example.
 /resources/sample.txt
 
 4.design and implement a java program to import the sample file.
 
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
    
    5.the program can create/update/list/delete the novel records.
    Restful API: com.river.videriTest.web.controller.NovelController.java
    Java Programme : com.river.videriTest.utils.FileUtil.java
    
    6.design and implement test cases for the import system.
    test cases for the repository:    com.river.videriTest.repository.NovelTest      
    test cases for the import file: com.river.videriTest.Util.FileWriterTest
    test cases for the restAPI:   com.river.videriTest.web.controller.NovelControllerTest
    
