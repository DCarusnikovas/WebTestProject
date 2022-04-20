Feature: TC0001 - Google Search

@TC0001 @Google
Scenario: TC0001 - Google Search option First
#dsd

Given I execute step "Step 1: I navigate to Google"
When I navigate to "https://www.google.com/"
And I click "Google.Search.IAgree.Button"

Given I execute step "Step 2: I search for Fish"
And I enter a text "fish" into "Google.Search.TextBox.Input"
And I click "Google.Search.GOOGLE.img"
And I click "Google.Search.GSearch.Button"
And I read element "Google.Search.Header.FirstResult.Link" text to state "State.FistLinkFromGoogle"

