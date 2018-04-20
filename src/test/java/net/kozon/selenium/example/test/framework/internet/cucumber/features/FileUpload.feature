Feature: File upload

    Scenario: Upload example file to page
        Given I have to open File Upload page
        When I load file to the page
        Then I have confirmation that file is uploaded correctly

