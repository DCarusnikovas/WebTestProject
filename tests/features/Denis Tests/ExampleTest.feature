@tag0
Feature: Test Examples
I want to use this template for my feature file

@tag1
  Scenario: Single item selection exmaples
  
  # And I select "<ANY String>" item and put it in basket - user not restricted
      And I select "Nothing" item and put it in basket
  
  #And I select from (Apple|Pear|Orange) item and put it in basket  - user restricted to select only 3 items
      And I select from Apple item and put it in basket
      And I select from Pear item and put it in basket
      And I select from Orange item and put it in basket
      
    #bad exmaple  
      ##And I select from Bannaa item and put it in basket
       And I select from (Apple|Pear|Orange) item and put it in basket
  
  @tag2
  Scenario Outline: Title of your scenario outline
      And <step>
  
  @tag3
    Examples: 
      | step                                           | 
      | I select from Orange item and put it in basket | 
  
  @tag4
    Examples: 
      | step                                         | 
      | I select from Pear item and put it in basket | 
  
  @tag5
  Scenario: MultiSelect item selection exmaples
      And I select from following items and put it in basket
      | Apple      | 
      | Strawberry | 
      | Lemon      | 
  
  @tag6
  Scenario: MultiSelect item selection exmaples and baskets
      And I select from following items and put it in correct basket
      | Apple      | Basket1 | 
      | Strawberry | Basket2 | 
      | Lemon      | Basket1 | 