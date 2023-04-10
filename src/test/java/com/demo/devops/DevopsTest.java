package com.demo.devops;

import com.demo.devops.controller.Devops;
import com.demo.devops.domain.MessageRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Devops.class)
class DevopsTest {

    private static final String API_KEY = "2f5ae96c-b558-4c7b-a590-a501ae1c3f6c";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MessageRequest messageRequest;

    @Test
    void givenValidRequest_whenSendMessage_thenReturnOk() throws Exception {
        mockMvc.perform(post("/devops")
                        .header("X-Parse-REST-API-Key", API_KEY)
                        .content("{\n" +
                                "    \"message\": \"This is a test\",\n" +
                                "    \"to\": \"Juan Perez\",\n" +
                                "    \"from\": \"Rita Asturia\",\n" +
                                "    \"timeToLifeSec\": 45\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Juan Perez your message will be sent"));
    }

}
