Feature: LG - Register customer validation

@TC00004
Scenario: LG - Register customer validation


    Given I execute step "Step 1: I navigate to LG website"
      And I navigate to LG
    
    Given I execute step "Step 2: Click login and select "
      
      And I click "Look.Gorgeous.Top.Nav.Panel.LogIn.Button"
      And I click "Look.Gorgeous.Register.Button"
    
    Given I execute step "Step 3: Check that on correct page and correct elements displayed" 
    
    Then I check that element "Look.Gorgeous.Register.SignUp.Header.Label" equal to text "Sign Up"
    Then I check that element "Look.Gorgeous.Register.Login.Link.Button" is visible
    Then I check that element "Look.Gorgeous.Register.Email.Input" is visible
    Then I check that element "Look.Gorgeous.Register.Password.Input" is visible
    Then I check that element "Look.Gorgeous.Register.SignUp.button" is visible
    Then No error messages on page displayed
   
   Given I execute step "Step 4: Check that email is mandatory and validate"  
      
      And I enter a text "12345Aa!!!!" into "Look.Gorgeous.Register.Password.Input"
      And I click "Look.Gorgeous.Register.SignUp.button"
     Then An error message appears with text "Email cannot be blank"
      
      And I enter a text "Denis" into "Look.Gorgeous.Register.Email.Input"
      And I click "Look.Gorgeous.Register.SignUp.button"
    Then An error message appears with text "Double check your email and try again."
      
      And I update a text "Denis@" in "Look.Gorgeous.Register.Email.Input"
      And I click "Look.Gorgeous.Register.SignUp.button"
    Then An error message appears with text "Double check your email and try again."
      
      And I update a text "Denis@gmailcom" in "Look.Gorgeous.Register.Email.Input"
      And I click "Look.Gorgeous.Register.SignUp.button"
    Then An error message appears with text "Double check your email and try again."
    
     And I update a text "Denis@gmail.com" in "Look.Gorgeous.Register.Email.Input"
     And I click "Look.Gorgeous.Register.SignUp.button"
    Then An error message appears with text "An account with this email already exists."
    
    Given I execute step "Step 5: Check that password is mandatory and validate"
    	 
      And I update a text "DenisTest@gmail.com" in "Look.Gorgeous.Register.Email.Input"
      And I update a text "" in "Look.Gorgeous.Register.Password.Input"
      And I click "Look.Gorgeous.Register.SignUp.button"
    Then An error message appears with text "Make sure you enter a password."
      
      And I update a text "DenisTest@gmail.com" in "Look.Gorgeous.Register.Email.Input"
      And I update a text " " in "Look.Gorgeous.Register.Password.Input"
      And I click "Look.Gorgeous.Register.SignUp.button"
    Then An error message appears with text "Password length must be between 4 and 100"
        

