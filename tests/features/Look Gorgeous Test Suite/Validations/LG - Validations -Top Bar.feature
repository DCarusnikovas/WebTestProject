Feature: LG - Validations - User register Form

@LGRegression
  Scenario Outline: LG - Validations - User register Form
  
    Given I execute step "Step 1: I navigate to LG website and click nav panel button"
      And I navigate to LG
      And I wait "<wait>" milliseconds
      And I click "<element>"
    
    Given I execute step "Step 2: I verify nav panel visibility buttons"
  #Validate that element exist
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.HOME.Link" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.PlansPricing.Link" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.Programs.Link" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.Groups.Link" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.Members.Link" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.Shop.Link" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.Blog.Link" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.BookOnline.Link" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.More.Tab" <visibility> visible
     Then I check that element "Look.Gorgeous.Top.Nav.Panel.LogIn.Button" <visibility> visible
  
  @TC00001
    Examples: 
      | element                                       | visibility | wait |
      | Look.Gorgeous.Top.Nav.Panel.HOME.Link         | is         | 1000 |
      | Look.Gorgeous.Top.Nav.Panel.PlansPricing.Link | is         | 1000 | 
      | Look.Gorgeous.Top.Nav.Panel.Programs.Link     | is         | 1000 | 
      | Look.Gorgeous.Top.Nav.Panel.Groups.Link       | is         | 1000 | 
      | Look.Gorgeous.Top.Nav.Panel.Blog.Link         | is         | 1000 |
      | Look.Gorgeous.Top.Nav.Panel.Members.Link      | is         | 1000 | 
      | Look.Gorgeous.Top.Nav.Panel.Shop.Link         | is         | 1000 | 
      | Look.Gorgeous.Top.Nav.Panel.BookOnline.Link   | is         | 1000 |
      | Look.Gorgeous.Top.Nav.Panel.More.Tab          | is         | 1000 |
      
   @TC00002
    Examples: 
      | element                                       | visibility | wait | 
      | Look.Gorgeous.Top.Nav.Panel.LogIn.Button      | is not     | 5000 |
  
  @LGRegression @TC00003
  Scenario: LG - Validations - User register Form
  
    Given I execute step "Step 1: I navigate to LG website"
      And I navigate to LG
    Given I execute step "Step 2: I test sleep"
      And I put pause here for "1" minutes
    Given I execute step "Step 3: Sleep worked good" 
    
   
