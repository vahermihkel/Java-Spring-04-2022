package com.example.cardgame.controller;

import com.example.cardgame.model.Card;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
class CardControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private CardController cardController;

//    private JacksonTester<Card> jsonCard;

    MockHttpServletResponse response;

    @BeforeEach
    void setUp() throws Exception {
//        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(cardController).build();
        response = mvc.perform(MockMvcRequestBuilders.get("/start-round"))
                .andReturn().getResponse();
    }

    @Test
    void startRoundUrlNotFoundWhenUrlInvalid() throws Exception {

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get("/start-roun"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void startRoundUrlIsSuccessfulWhenUrlCorrect() throws Exception {
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void startRoundRequestReturnsNewCard() throws Exception {

        assertTrue(response.getContentAsString().contains("suit") &&
                response.getContentAsString().contains("rank") &&
                response.getContentAsString().contains("value"));
    }

    @Test
    void startRoundRequestReturnsOneOfTheSuits() throws Exception {

        JSONObject card = new JSONObject(response.getContentAsString());
        assertThat(card.get("suit")).isIn(Arrays.asList("HEART","DIAMOND", "SPADE", "CLUB"));
    }

    @Test
    void startRoundRequestReturnsCardValueBetweenOneAndTen() throws Exception {

        JSONObject card = new JSONObject(response.getContentAsString());
        int cardValue = (int) card.get("value");
        boolean isBetweenOneAndTen = cardValue >= 1 && cardValue <= 10;
        assertTrue(isBetweenOneAndTen);
    }

    @Test
    void roundResponseUrlNotFoundWhenUrlInvalid() throws Exception {

        MockHttpServletResponse roundResponse = mvc.perform(MockMvcRequestBuilders.get("/round-respnse/higher"))
                .andReturn().getResponse();
        assertThat(roundResponse.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void roundResponseUrlIsSuccessfulWhenUrlCorrect() throws Exception {

        MockHttpServletResponse roundResponse = mvc.perform(MockMvcRequestBuilders.get("/round-response/higher"))
                .andReturn().getResponse();
        assertThat(roundResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void roundResponse() {
    }
}
