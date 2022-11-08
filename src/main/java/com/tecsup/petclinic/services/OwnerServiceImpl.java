package com.tecsup.petclinic.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

@Service
public class OwnerServiceImpl implements OwnerService{
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

	@Autowired
	OwnerRepository ownerRepository;

	/**
	 * 
	 * @param id
	 * @throws OwnerNotFoundException
	 */
	
	@Override
	public void delete(Long id) throws OwnerNotFoundException {
		
		Owner owner = findById(id);
		ownerRepository.delete(owner);
		
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Owner findById(long id) throws OwnerNotFoundException {
	
		return null;
	}
	/**
	 * 
	 * @param owner
	 * @return
	 */
	
	@Override
	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
	}
	
	
	

}
