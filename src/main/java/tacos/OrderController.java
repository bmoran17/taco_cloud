package tacos;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import tacos.TacoOrder;

// controller to handle request for /orders/current

@Slf4j // Lombok annotation to generate logger at compile time
@Controller
@RequestMapping("/orders") // class-level @RequestMapping to handle requests for /orders
@SessionAttributes("tacoOrder")
public class OrderController {

  // handles GET /orders/current to show order form view
  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }

  // handles POST /orders to process submitted order form
  @PostMapping
  public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {

    if (errors.hasErrors()) {
      return "orderForm";
    }

    // logs submitted order and marks session complete to clear session attributes
    log.info("Order submitted: {}", order);
    sessionStatus.setComplete();

    // redirects to home page after processing order
    return "redirect:/";
  }

}
