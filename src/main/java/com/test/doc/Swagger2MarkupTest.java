package com.test.doc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.github.robwin.markup.builder.MarkupLanguage;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/applicationContext.xml","classpath:/applicationContext-mvc.xml","classpath:/swagger.xml"})
public class Swagger2MarkupTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void convertSwaggerToAsciiDoc() throws Exception {
        ResultActions ra = this.mockMvc.perform(get("/v2/api-docs?group=customer")
        		.accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"));
        
        		ra.andDo(Swagger2MarkupResultHandlerUtf8.outputDirectory("src/docs/asciidoc/generated").build())
                .andExpect(status().isOk());
    }
   
    @Test
    public void convertSwaggerToMarkdown() throws Exception {
        this.mockMvc.perform(get("/v2/api-docs?group=customer")
                .accept(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
                .andDo(Swagger2MarkupResultHandlerUtf8.outputDirectory("src/docs/markdown/generated")
                    .withMarkupLanguage(MarkupLanguage.MARKDOWN).build())
                .andExpect(status().isOk());
    }
}