Feature: Navigate somewhere


  @Examples
  Scenario Outline: Navigate somewhere

  
    Given I execute step "Step 1: I navigate to <website>"
     When I navigate to "<website>"
  
    Examples: 
      | website                                       | 
      | https://www.google.com/                       | 
      | https://silentium98.wixsite.com/look-gorgeous | 
