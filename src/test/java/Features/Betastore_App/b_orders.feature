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


# @order
  Scenario: Make single order for existing customer
    Given User clicks on "Orders" tab
    When clicks "Add" orders
    And switch to content frame
    Then "Add an Order" page is displayed
    And searches for customer "Benja Test"
    Then customer billing address should be listed
    When user selects the address to be used
    And clicks "Next" button
    Then navigated to "Add Products"
    When user searches for orders "Auntie B Spaghetinni 500g x 20"
    And enters quantity "1"
    And clicks "Next" button
    Then navigated to "Shipping"
    And clicks "Next" button
    Then navigated to "Finalize"
    When user selects payment as "Cash on Delivery"
    And clicks "Save & Process Payment »" button
    Then order is made successfully

  @order-test @order
  Scenario: Make multiple orders order for existing customer
    Given User clicks on "Orders" tab
    When clicks "Add" orders
    And switch to content frame
    Then "Add an Order" page is displayed
    And searches for customer "Benja Test"
    Then customer billing address should be listed
    When user selects the address to be used
    And clicks "Next" button
    Then navigated to "Add Products"
    When user searches for orders in exel "src/test/resources/Objects/Order_SKU.xlsx" and set random quantity
    And clicks "Next" button
    Then navigated to "Shipping"
    And clicks "Next" button
    Then navigated to "Finalize"
    When user selects payment as "Cash on Delivery"
    And clicks "Save & Process Payment »" button
    Then order is made successfully

#  @order @order-status
  Scenario: Change status of successful order
    Given User clicks on "Orders" tab
    When clicks "View" orders
    And switch to content frame
    When user views order under "For Review"
    Then "For Review" tab is displayed
    When user searches the order
    And Order has status of "Pending"
    And changes order to status of "Awaiting Fulfillment"
    Then order status should be "Awaiting Fulfillment"







