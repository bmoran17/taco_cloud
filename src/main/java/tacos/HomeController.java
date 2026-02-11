package tacos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation. GetMapping;

@Controller 
public class HomeController {

  // handles requests for root path /
  @GetMapping("/")
  public String home() {
    // returns the view name
    return "home";
  }

}
