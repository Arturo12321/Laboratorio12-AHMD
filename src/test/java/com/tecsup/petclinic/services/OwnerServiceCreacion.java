package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;

@SpringBootTest
public class OwnerServiceCreacion {
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceCreacion.class);

	@Autowired
	private OwnerService ownerService;
	
	@Test
	public void testCreatePet() {

		String FIRST_NAME = "Rosa";
		String LAST_NAME = "Gutierrez";
		String ADDRESS = "Jr.Valentin 234.";
		String CITY = "California";
		String TELEPHONE="77678673";

		Owner owner = new Owner(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
		
		Owner ownerCreated = ownerService.create(owner);
		
		logger.info("OWNER CREATED :" + ownerCreated);

		//          ACTUAL                 , EXPECTED 
		assertThat(ownerCreated.getId()      , notNullValue());
		assertThat(ownerCreated.getFirst_name()    , is(FIRST_NAME));
		assertThat(ownerCreated.getLast_name() , is(LAST_NAME));
		assertThat(ownerCreated.getAddress()  , is(ADDRESS));
		assertThat(ownerCreated.getCity()  , is( CITY));
	    assertThat(ownerCreated.getTelephone()  , is(TELEPHONE));

	}
}
