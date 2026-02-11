package tacos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder; 

// Controller to handle HTTP GET requests for path /design
@Slf4j //Lombok annotation that generates logger at compile time
@Controller
@RequestMapping("/design") 
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  @ModelAttribute
  public void addIngredientsToModel(Model model) {

    // creates hardcoded list of ingredient objects
    List<Ingredient> ingredients = Arrays.asList(
      new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
      new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
      new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
      new Ingredient("CARN", "Carnitas", Type.PROTEIN),
      new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
      new Ingredient("LETC", "Lettuce", Type.VEGGIES),
      new Ingredient("CHED", "Cheddar", Type.CHEESE),
      new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
      new Ingredient("SLSA", "Salsa", Type.SAUCE),
      new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );
    
    // gets all ingredient types & filters ingredients by type, then adds to model
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
        filterByType(ingredients, type));
    } 
  }

  // creates new TacoOrder object & adds to model w/ key "tacoOrder"
  // stored in HTTP session b/c of @SessionAttributes annotation on class
  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  // creates empty Taco object & adds to model w/ key "taco"
  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  //  handles GET /design & returns design view
  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  // helper method to filter list of ingredients by type
  private Iterable<Ingredient> filterByType(
    List<Ingredient> ingredients, Type type) {
      return ingredients
        .stream()
        .filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());
    }
}
