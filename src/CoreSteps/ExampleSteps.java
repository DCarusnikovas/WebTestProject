package CoreSteps;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.And;

public class ExampleSteps {
	
    @And("^I select \"([^\"]*)\" item and put it in basket$")
    public void i_select_something_item_and_put_it_in_basket(String item) {
    	System.out.println("[i_select_something_item_and_put_it_in_basket] -> I select \""+item+"\" and put in basket");
    }
    
    @And("^I select from (Apple|Pear|Orange) item and put it in basket$")
    public void i_select_from_something_item_and_put_it_in_basket(String item) {  	
    	System.out.println("[i_select_from_something_item_and_put_it_in_basket] -> I select from \""+item+"\" and put in basket");
    }
    
    @And("^I select from following items and put it in basket$")
    public void i_select_from_following_item_and_put_it_in_basket(List <String> items) {	
    	items.stream().forEach(item ->System.out.println("[i_select_from_following_items_and_put_it_in_basket] -> I select from \""+item+"\" and put in basket"));
    }
    
    @And("^I select from following items and put it in correct basket$")
    public void i_select_from_following_items_and_put_it_in_correct_basket(Map <String,String> items) {	
    	items.forEach((item,basket) ->System.out.println("[i_select_from_something_items_and_put_it_in_basket] -> I select from \""+item+"\" and put it in basket called "+basket));
    }
}
