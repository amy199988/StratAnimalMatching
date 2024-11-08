package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.entity.Cat;
import com.example.demo.repository.CatRepository;

@SpringBootTest
public class CatJAPTest {
	
	@Autowired
	private CatRepository catRepository;
	
	@Test
	public void testCatadd() {
		Cat cat = new Cat(null,"小八","八字瀏海",5,"無","性格開朗、樂觀，並且非常健談。","...",false);
		cat = catRepository.save(cat);
		System.out.println("測試新增：" + cat );
	}
	
	/*@Test
	public void testFindAllCats() {
		System.out.println("測試查詢全部：" + catRepository.findAll());
	}
	
	@Test
	public void testFindCat() {
		System.out.println("測試查詢單筆：" + catRepository.findById(1));
		System.out.println("測試查詢單筆：" + catRepository.findById(2));
	}
	
	@Test
	public void testUpdateCat() {
		Cat updatecat = new Cat(1,"小八","八字瀏海",5,"無","性格開朗、樂觀，並且非常健談。",true);
		updatecat = catRepository.save(updatecat);
		System.out.println("測試更新：" + updatecat);
	}*/
	
}
