Feature: Test login at Ellucian


  @EllucianTest
  Scenario: Test login at Ellucian
    
    
    Given I navigate to Ellucian
    
    
     When I enter a text "DenisasTestUser@elluciancloud.com" into "Ellucian.LoginPage.Username.Input"
      And I enter a text "Elluc1an!" into "Ellucian.LoginPage.Password.Input"
      And I click "Ellucian.LoginPage.SignIn.Button"
      And I click sign in botton
     Then I check that element "Ellucian.Elloyce.TopPanel.Logo.Img" is visible
     

  @EllucianTestAdmin
  Scenario: Test login at Ellucian
    
    
    Given I navigate to Ellucian
    And I put pause here for "130" minutes
     When I enter a text "AdminCourseCatalog@elluciancloud.com" into "Ellucian.LoginPage.Username.Input"
      And I enter a text "Password1!" into "Ellucian.LoginPage.Password.Input"
      And I click "Ellucian.LoginPage.SignIn.Button"
     Then I check that element "Ellucian.Elloyce.TopPanel.Logo.Img" is visible
      
     When I click "Ellucian.Elloyce.TopPanel.SideBar.Button" 
     
     
     
  @EllucianTestDenis
  Scenario: Test at Ellucian
      
    Given I navigate to Ellucian
    And I login with user "DenisasTestUser@elluciancloud.com" and password "Elluc1an!"
    
    
    
    
    