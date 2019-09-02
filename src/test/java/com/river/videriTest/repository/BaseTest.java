package com.river.videriTest.repository;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.river.videriTest.App;
/**
 * 
 * @author riverplant
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class)
@Transactional//会执行回滚
public class BaseTest 
{ 
	
}
