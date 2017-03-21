package com.charter;

import com.charter.model.Plane;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
public class ApiDocumentation {
	@Rule
	public final RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper objectMapper;


	private MockMvc mockMvc;


	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.build();
	}

	@Test
	public void index() throws Exception {
		this.mockMvc.perform(get("/ws/v1/").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(document("home"));
	}

	@Test
	public void planes() throws Exception {
		this.mockMvc.perform(get("/ws/v1/planes").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("planes",
						responseFields(
								fieldWithPath("[].id").description("id of the plane"),
								fieldWithPath("[].name").description("name of the plane"),
								fieldWithPath("[].speed").description("speed (mph) for the plane"),
								fieldWithPath("[].range").description("range (miles) for the plane")
						)));
	}

	@Test
	public void maverick() throws Exception {
		this.mockMvc.perform(get("/ws/v1/planes/maverick").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("planes/maverick",
						responseFields(
								fieldWithPath("id").description("id of the plane"),
								fieldWithPath("name").description("name of the plane"),
								fieldWithPath("speed").description("speed (mph) for the plane"),
								fieldWithPath("range").description("range (miles) for the plane")
						)));
	}

	@Test
	public void nitro() throws Exception {
		Plane plane = new Plane();
		plane.setName("nitro");
		plane.setSpeed(900);
		plane.setRange(5000);

		this.mockMvc.perform(put("/ws/v1/planes/nitro")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(plane)))
				.andExpect(status().isOk())
				.andDo(document("planes/nitro",
						responseFields(
								fieldWithPath("id").description("id of the plane"),
								fieldWithPath("name").description("name of the plane"),
								fieldWithPath("speed").description("speed (mph) for the plane"),
								fieldWithPath("range").description("range (miles) for the plane")
						)));
	}

	@Test
	public void flight() throws Exception {
		Point from = new Point(5, 10);
		Point to = new Point(200, 1500);
		Path path = new Path(from, to);

		this.mockMvc.perform(post("/ws/v1/planes/maverick")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(path)))
				.andExpect(status().isOk())
				.andDo(document("planes/flight"));
	}
}
