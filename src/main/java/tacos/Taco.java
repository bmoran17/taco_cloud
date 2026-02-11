package tacos;
import java.util.List;
import lombok.Data;

// Taco Domain Class
@Data
public class Taco {
  private String name;
  private List<Ingredient> ingredients;
}