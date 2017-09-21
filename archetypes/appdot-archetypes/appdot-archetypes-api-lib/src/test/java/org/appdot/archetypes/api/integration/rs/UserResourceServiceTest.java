/**
 * 
 */
package org.appdot.archetypes.api.integration.rs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.appdot.archetypes.api.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * @author Lian
 *
 */
public class UserResourceServiceTest extends BaseRsTest {

	@Test
	public void testGetAllUsers() {
		WebResource resource = resource();
		List<UserDTO> emptyList = new ArrayList<UserDTO>();
		List<UserDTO> userDtos = resource.path("users").accept(MediaType.APPLICATION_JSON).get(emptyList.getClass());
		System.out.println("testGetAllUsers result: " + userDtos);
		Assert.assertTrue("user list should not be empty", !userDtos.isEmpty());
	}

	@Test
	public void testGetUser1() {
		WebResource resource = resource();
		UserDTO userDto = resource.path("users/-1").accept(MediaType.APPLICATION_JSON).get(UserDTO.class);
		System.out.println("testGetUser result: " + userDto);
	}

	@Test
	public void testGetUser2() {
		WebResource resource = resource();
		UserDTO userDto = resource.path("users/-2").accept(MediaType.APPLICATION_JSON).get(UserDTO.class);
		System.out.println("testGetUser result: " + userDto);
	}

	@Test
	public void testGetUser3() {
		WebResource resource = resource();
		UserDTO userDto = resource.path("users/-3").accept(MediaType.APPLICATION_JSON).get(UserDTO.class);
		System.out.println("testGetUser result: " + userDto);
	}

	@Test
	public void testGetUser4() {
		WebResource resource = resource();
		UserDTO userDto = resource.path("users/-4").accept(MediaType.APPLICATION_JSON).get(UserDTO.class);
		System.out.println("testGetUser result: " + userDto);
	}
}
