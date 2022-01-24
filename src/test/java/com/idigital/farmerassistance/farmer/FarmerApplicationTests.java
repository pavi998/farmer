package com.idigital.farmerassistance.farmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idigital.farmerassistance.farmer.entities.Farmer;
import com.idigital.farmerassistance.farmer.respentities.ErrorInfo;
import com.idigital.farmerassistance.farmer.respentities.SuccessInfo;
import com.idigital.farmerassistance.farmer.service.IFarmerService;

@SpringBootTest
class FarmerApplicationTests {

	private  MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper om = new ObjectMapper();
	
	@BeforeEach
	public  void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Autowired
	IFarmerService fs;
	
	 //st - Success Test
	//ft - Failure Test
	
	//Test for Get Mapping	
	@Test
	void stGetFarmerById() throws Exception
	{
		/*
		 * JUnit Test Case 1
		 * 
		 * @param farmerId @return farmer
		 * The test case will pass if the @param farmerId matches 
		 * with @return farmerId in Farmer Class
		 */
		mockMvc.perform(get("/farmers/{id}",5)
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.farmerId").value(5))
                .andReturn();

		Farmer farm = fs.getFarmersbyId(5);
		assertEquals(5,farm.getFarmerId());
	}
	
	@Test
	void ftGetFarmerById() throws Exception
	{
		MvcResult result = mockMvc.perform(get("/farmers/{id}",0)
				                  .accept(MediaType.APPLICATION_JSON))
	                              .andDo(print())
	                              .andExpect(status().isNotFound())
	                              .andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
		assertEquals(404,info.getStausCode());
	}
	
	@Test
	void stGetFarmerByName() throws Exception
	{
		/*
		 * JUnit Test Case 2
		 * 
		 * @param farmerName @return farmer 
		 * The test case will pass if the @param farmerName matches 
		 * with @return farmerName in Farmer Class
		 */
		mockMvc.perform(get("/farmers/{name}","Raj")
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
                
		Farmer farm = fs.getFarmerbyName("Raj");
		assertEquals(5,farm.getFarmerName());
	
}	
	@Test
	void ftGetFarmerbyName() throws Exception
	{
		MvcResult result = mockMvc.perform(get("/farmers/{name}","null")
				                  .accept(MediaType.APPLICATION_JSON))
                                  .andDo(print()).andExpect(status().isNotFound())
                                  .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        assertEquals(info.getStausCode(),404);	
	}
	
	
	@Test
	void stGetFarmerByemail() throws Exception
	{
		mockMvc.perform(get("/farmers/{email}","raj@gmail.com")
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
		List<Farmer> farm = fs.getFarmerbyEmail("raj@gmail.com");
		assertTrue(farm.stream().allMatch(obj->obj.getFarmerEmail().equals("raj@gmail.com")));
	}
	
	@Test
	void ftGetProductByType() throws Exception
	{
		MvcResult result = mockMvc.perform(get("/farmers/{email}","jamun@gmail.com").accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isNotFound()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        assertEquals(404,info.getStausCode());		
	}
	
	@Test
	void stInsertProduct() throws Exception
	{
		/*
		 * JUnit Test Case 3
		 * 
		 * @return Message
		 * The test case will pass if the new farmer details is 
		 * successfully inserted
		 */
		Farmer farm = new Farmer(99,"Raj",98765466,"rat@gmail.com",  null);
        String json = om.writeValueAsString(farm);
		MvcResult result = mockMvc.perform(post("/farms").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				                  .andDo(print())
				                  .andExpect(status().isCreated())
				                  .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        SuccessInfo info = om.readValue(resultContent, SuccessInfo.class);
        assertEquals(201,info.getStatusCode());	
	}
	
	@Test
	void ftInsertFarmer() throws Exception
	{
		Farmer farm = new Farmer(99,"Raj",98765466,"rat@gmail.com",  null);
        String json = om.writeValueAsString(farm);
		MvcResult result = mockMvc.perform(post("/farmers").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				                  .andDo(print())
				                  .andExpect(status().isBadRequest())
				                  .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        assertEquals(400,info.getStausCode());		
	}
	
	@Test
	void stUpdateProduct() throws Exception
	{
		/*
		 * JUnit Test Case 4
		 * 
		 * @return Message 
		 * The test case will pass if the existing farmer detail
		 * is successfully updated
		 */
		Farmer farm = new Farmer(99,"Raj",98765466,"rat@gmail.com",  null);
        String json = om.writeValueAsString(farm);
		MvcResult result = mockMvc.perform(put("/farmers/{id}",1).content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				                  .andDo(print())
				                  .andExpect(status().isOk())
				                  .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        SuccessInfo info = om.readValue(resultContent, SuccessInfo.class);
        assertEquals(200,info.getStatusCode());	
		
	}
	
	@Test
	void ftUpdateProduct() throws Exception
	{
		Farmer farm = new Farmer(99,"Raj",98765466,"rat@gmail.com",  null);
        String json = om.writeValueAsString(farm);
		MvcResult result = mockMvc.perform(put("/farmers/{id}",0).content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				                  .andDo(print())
				                  .andExpect(status().isBadRequest())
				                  .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        assertEquals(400,info.getStausCode());
	}
	
	@Test
	void stDeleteProduct() throws Exception
	{
		/*
		 * JUnit Test Case 5
		 * 
         * @return Message 
		 * The test case will pass if the existing farmer
		 * is successfully deleted
		 */
		mockMvc.perform(delete("/products/name/{name}","JUNIT")
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
	}
	
	@Test
	void ftDeleteProduct() throws Exception
	{
		MvcResult result = mockMvc.perform(delete("/products/id/{id}",99)
				.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
		String resultContent = result.getResponse().getContentAsString();
        ErrorInfo info = om.readValue(resultContent, ErrorInfo.class);
        assertEquals(404,info.getStausCode());
		
	}

}
