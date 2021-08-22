Feature: orders feature

  Background:
    Given User navigates to the page "https://login.bigcommerce.com/login"
    When Enters the username "barnaby.kamau@betastore.co" and password "Twendekazi@254" to login
    Then user should be prompted for OTP "Enter the verification code sent to"
    And user opens a new tab
    Given User navigates to the page "https://mail.google.com/mail/u/0/#inbox"
    When user navigates to email and logs in in with credentials "barnaby.kamau@betastore.co" and password "twendekazi254"
    Then User should be able to access their email
    When user clicks email and grabs the otp generated
    And user switches to first tab
    And User enters the otp code and clicks verify
    Then User should be loggedin

  @VERIFY_FIELD
  Scenario: verify fields sidebar
    And user validates the sidebar fields


  @orders
  Scenario: User enters order for Existing customer
    Given User clicks on orders
    When clicks Add orders
    And switch to content frame
    Then "Add an Order" page is displayed
    And searches for customer "Benja Test"
    Then customer billing address should be listed
    When user selects the address to be used
    And clicks next button
    Then navigated to "Add Products"
    When user searches for orders "Auntie B Spaghetinni 500g x 20"
    And enters quantity "4"
    And clicks next button
    Then navigated to "Shipping"
    And clicks next button
    Then navigated to "Customer Billing DetailsCustomer Billing Details"
    When user selects payment as "pay as delivery"



