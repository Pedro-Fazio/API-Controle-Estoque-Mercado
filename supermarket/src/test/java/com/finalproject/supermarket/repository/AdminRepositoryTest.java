package com.finalproject.supermarket.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.finalproject.supermarket.model.Admin;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdminRepositoryTest {

	@Autowired
	private AdminRepository adminRepository;

	@Test
	public void loadAdminByHisEmail() {
		String email = "pedro@email.com";
		Optional<Admin> admin = adminRepository.findByEmail(email);
		assertNotNull(admin);
		assertEquals(email, admin.get().getEmail());
	}
	
	@Test
	public void loadAdminByNonexistentEmail() {
		String email = "notcorrectemail@email.com";
		Optional<Admin> admin = adminRepository.findByEmail(email);
		
		if(admin.isEmpty()) {
			assert(true);
		} else
			assert(false);
	}

}
