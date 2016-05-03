@responsiveDesign
Feature: Responsive Design
  As a user I want to make sure that
  the application is displayed correctly on
  laptop, mobile and web

  Background:
    Given I navigate to Home page

  @positive
  Scenario Outline: Verify that Menu link is displayed instead of navigation menu links on Iphone 6 and Nexus 6P mobiles
    When I resize the page according to the following device:
      | Device      | <Device>      |
      | Orientation | <Orientation> |
    Then I should see the Menu link
    And I should not see the Top navigation menu links

    Examples:
      | Device   | Orientation |
      | Iphone 6 | Portrait    |
      | Nexus 6P | Portrait    |

  @positive
  Scenario Outline: Verify that navigation menu links are displayed instead of Menu link on given devices
    When I resize the page according to the following device:
      | Device      | <Device>      |
      | Orientation | <Orientation> |
    Then I should not see the Menu link
    And I should see the Top navigation menu links
    And I should see 5 the Top navigation menu links

    Examples:
      | Device   | Orientation |
      | Laptop   |             |
      | Iphone 6 | Panorama    |
      | Nexus 6P | Panorama    |
      | Ipad     | Portrait    |
      | Ipad     | Panorama    |

  @positive
  Scenario Outline: Verify amount of images are displayed for specified device
    Given I open the Work tab
    When I resize the page according to the following device:
      | Device      | <Device>      |
      | Orientation | <Orientation> |
    Then Amount of Images on the page is <Image Count>
    Then I should see <Displayed Image Count> Images

    Examples:
      | Device   | Orientation | Image Count | Displayed Image Count |
      | Laptop   |             | 8           | 4                     |
      | Iphone 6 | Panorama    | 8           | 4                     |
      | Iphone 6 | Portrait    | 8           | 4                     |
      | Nexus 6P | Panorama    | 8           | 4                     |
      | Nexus 6P | Portrait    | 8           | 4                     |
      | Ipad     | Portrait    | 8           | 4                     |
      | Ipad     | Panorama    | 8           | 4                     |

  @positive
  Scenario Outline: Verify image position is shifter according to selected device
    Given I open the Work tab
    When I resize the page according to the following device:
      | Device      | <Device>      |
      | Orientation | <Orientation> |
    Then I should see the Images in <Image View> representation

    Examples:
      | Device   | Orientation | Image View |
      | Laptop   |             | Gallery    |
      | Iphone 6 | Panorama    | List       |
      | Iphone 6 | Portrait    | List       |
      | Nexus 6P | Panorama    | List       |
      | Nexus 6P | Portrait    | List       |
      | Ipad     | Portrait    | List       |
      | Ipad     | Panorama    | Gallery    |