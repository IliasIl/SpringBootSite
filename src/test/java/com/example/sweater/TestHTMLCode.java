package com.example.sweater;


import com.example.sweater.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/user-before.sql", "/message-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/message-after.sql", "/user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TestHTMLCode {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MainController mainController;

    @Test
    public void mainTestPage() throws Exception {
        this.mockMvc
                .perform(get("/main"))
                .andDo(print()).andExpect(authenticated())
                .andExpect(xpath("//div[@id='navbarSupport']/div").string("admin"));
    }

    @Test
    public void messageListTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='idCardList']/div").nodeCount(4));
    }

    @Test
    public void filterTest() throws Exception {
        this.mockMvc
                .perform(get("/main").param("filter", "my-tag"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='idCardList']/div").nodeCount(2))
                .andExpect(xpath("//div[@data-id=1]").exists())
                .andExpect(xpath("//div[@data-id=3]").exists())
                .andExpect(xpath("//div[@data-id=1]/div/i").string("#my-tag"));
    }

    @Test
    public void addNewMessage() throws Exception {
        MockHttpServletRequestBuilder multi = multipart("/main")
                .file("file", "123".getBytes())
                .param("text", "hello")
                .param("tag", "#my-tag").with(csrf());
        this.mockMvc.perform(multi)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='idCardList']/div").nodeCount(5))
                .andExpect(xpath("//div[@data-id=10]").exists())
                .andExpect(xpath("//div[@data-id=10]/div/span").string("hello"));
    }

}
