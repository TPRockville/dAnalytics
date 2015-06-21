package org.jderive.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jderive.OpenFdaApplication;
import org.jderive.service.WeightGroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import context.TestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OpenFdaApplication.class)
@ContextConfiguration(classes = { MockServletContext.class, TestContext.class })
@WebAppConfiguration
public class WeightGroupControllerTest {

    @Autowired
    private WeightGroupService weightGroupService;

    private MockMvc mvc;

    @Before
    public void setUp() {
        WeightGroupController weightGroupController = new WeightGroupController();
        ReflectionTestUtils.setField(weightGroupController, "weightGroupService", weightGroupService);
        mvc = MockMvcBuilders.standaloneSetup(weightGroupController).build();
    }

    @Test
    public void testGetWeightGroup() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weightgroup/list").accept(MediaType.APPLICATION_JSON)).andExpect(
                status().isOk());
    }
}
