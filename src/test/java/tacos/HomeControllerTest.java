package tacos;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

// Web test for HomeController
@WebMvcTest(HomeController.class)
public class HomeControllerTest {
  
  @Autowired
  // Injects MockMvc
  private MockMvc mockMvc;

  @Test
  public void testHomePage() throws Exception {
    // performs GET /
    mockMvc.perform(get("/"))
    // Expects HTTP 200
    .andExpect(status().isOk())
    // Expects home view
    .andExpect(view().name("home"))
    // Expects Welcome to...
    .andExpect(content().string(
      containsString("Welcome to...")));
  }
}