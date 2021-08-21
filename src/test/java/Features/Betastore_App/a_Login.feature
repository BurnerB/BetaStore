Feature: login feature


  @login @login-positive
  Scenario: User can login with correct credentials successfully
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

  @login-negative @login
  Scenario: User cannot login with correct email and incorrect password successfully
    Given User navigates to the page "https://login.bigcommerce.com/login"
    When Enters the username "barnaby.kamau@betastore.co" and password "incorrect password" to login
    Then  message should be displayed "The username or password you have entered is incorrect. Please try again."

  @login-negative @login
  Scenario: User cannot login with incorrect email and correct password successfully
    Given User navigates to the page "https://login.bigcommerce.com/login"
    When Enters the username "barnaby.kamau@incorrectEmail.co" and password "correct password" to login
    Then  message should be displayed "The username or password you have entered is incorrect. Please try again."

  @login-negative @login
  Scenario: User cannot login with blank email and blank password successfully
    Given User navigates to the page "https://login.bigcommerce.com/login"
    When Enters the username " " and password " " to login
    Then  message should be displayed "You need to sign in or sign up before continuing."

  @login-negative-blank @login
  Scenario: password reset email sent successfully
    Given User navigates to the page "https://login.bigcommerce.com/login"
    When user clicks forgot password link "Forgot?"
    And enters email "barnaby.kamau@betastore.co" and clicks reset
    Then  message should be displayed "If the email address you submitted is a valid username, you will receive an email with instructions on how to reset your password within a few minutes."
