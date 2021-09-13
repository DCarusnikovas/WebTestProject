Feature: TC0001 - Google Search

@TC0001 @Google
Scenario: TC0001 - Google Search option First
#dsd

Given I execute step "Step 1: I navigate to Google"
When I navigate to "https://www.google.com/"
And I click "//button[div[text()='I agree']]"

Given I execute step "Step 2: I search for Fish"
