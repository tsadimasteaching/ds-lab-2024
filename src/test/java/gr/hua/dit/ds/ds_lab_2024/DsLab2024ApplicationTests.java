package gr.hua.dit.ds.ds_lab_2024;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DsLab2024ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateUser() throws Exception {
		// Arrange
		String userJson = "{\"username\":\"apiuser\",\"email\":\"api@hua.gr\",\"password\":\"pass123\"}";


		// Act
		ResultActions result = mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson));

		// Assert
		result.andExpect(status().isOk());
	}

	@Test
	public void testSignUser() throws Exception {
		// Arrange
		String userJson = "{\"username\":\"apiuser\",\"password\":\"pass123\"}";

		// Act
		ResultActions result = mockMvc.perform(post("/api/auth/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson));

		// Assert
		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("apiuser"));

	}

	@Test
	public void testAccessSecuredEndpointWithJWT() throws Exception {
		// First, sign in to get the JWT token
		String loginJson = "{\"username\":\"apiuser\",\"password\":\"pass123\"}";

		ResultActions loginResult = mockMvc.perform(post("/api/auth/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.content(loginJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accessToken").exists());

		// Extract the token from the response
		String responseBody = loginResult.andReturn().getResponse().getContentAsString();
		// Use a JSON parser to extract the token
		String token = com.jayway.jsonpath.JsonPath.read(responseBody, "$.accessToken");

		// Use the token to call a secured endpoint
		ResultActions result = mockMvc.perform(
				get("/api/student") // change to your actual secured endpoint
						.header("Authorization", "Bearer " + token)
						.contentType(MediaType.APPLICATION_JSON));

		// Assert access is successful
		result.andExpect(status().isOk());
	}
}
