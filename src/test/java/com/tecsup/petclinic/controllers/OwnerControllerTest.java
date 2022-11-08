package com.tecsup.petclinic.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.dto.OwnerDTO;
import com.tecsup.petclinic.dto.PetDTO;

@AutoConfigureMockMvc
@SpringBootTest
public class OwnerControllerTest {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(OwnerControllerTest.class);

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;
	
	
	/**
	 * @throws Exception
	 */
	
	@Test
    public void testCreatePet() throws Exception {
		
		String FIRST_NAME = "Ara";
		String LAST_NAME = "Huamani";
		String ADDRESS = "1450 Oak Blvd.";
		String CITY = "Lima";
		String TELEPHONE="6056663387";
		
		OwnerDTO newOwner = new OwnerDTO(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
		
		logger.info(newOwner.toString());
		logger.info(om.writeValueAsString(newOwner));
	    
	    mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.first_name", is(FIRST_NAME)))
	            .andExpect(jsonPath("$.last_name", is(LAST_NAME)))
	            .andExpect(jsonPath("$.address", is(ADDRESS)))
	    		.andExpect(jsonPath("$.city", is(CITY)))
	    		.andExpect(jsonPath("$.telephone", is(TELEPHONE)));
    
	}
    

	/**
	 * @throws Exception
	 */
	

	@Test
    public void testDeletePet() throws Exception {

    	String FIRST_NAME = "Jeff";
		String LAST_NAME = "Black";
		String ADDRESS = "1450 Oak Blvd.";
		String CITY = "Monona";
		String TELEPHONE="6085555387";
		
		OwnerDTO newOwner = new OwnerDTO(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
		
		ResultActions mvcActions = mockMvc.perform(post("/owners")
	            .content(om.writeValueAsString(newOwner))
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated());
	            
		String response = mvcActions.andReturn().getResponse().getContentAsString();

		Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/owners/" + id ))
                 /*.andDo(print())*/
                .andExpect(status().isOk());
    }


}
