package com.reply.Automotive_Platform.offers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

@SpringBootTest
@AutoConfigureMockMvc
public class OffersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String basicAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @Test
    public void testPostWithMaxPriceFilter() throws Exception  {
        // given

        String jsonBody = """
                {
                 "priceLessThanEqual": 100.00
                }
                """;

        // when
        mockMvc.perform(
                post("/public/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10")
                        .content(jsonBody))
                .andExpect(status().isOk()).andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    public void testAddOffer() throws Exception {
        String jsonBody = """
            {
              "title": "Testowa oferta",
              "price": 99.99,
              "available": true,
              "car": {
                "id": 1
              }
            }
            """;

        mockMvc.perform(post("/auth/offers/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", basicAuthHeader("admin", "admin123"))
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Testowa oferta"))
                .andExpect(jsonPath("$.price").value(99.99))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    public void testDeleteOfferSuccess() throws Exception {
        mockMvc.perform(delete("/auth/offers/1").header("Authorization", basicAuthHeader("admin", "admin123")))
                .andExpect(status().isOk())
                .andExpect(content().string("Oferta została pomyślnie usunięta."));
    }

    @Test
    public void testDeleteOfferNotFound() throws Exception {
        mockMvc.perform(delete("/auth/offers/9999").header("Authorization", basicAuthHeader("admin", "admin123")))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Nie znaleziono oferty o ID 9999."));
    }
}
