#    Aero Food

Allows the user to orders food in the airport

# Possible API

 * API https://developers.zomato.com/api
 * API https://www.flysfo.com/shop-dine-relax/dining
 * API http://api.geonames.org/findNearby?lat=40.776902&lng=-73.968887&fcode=AIRP&radius=25&maxRows=100&username=xxxxx`.
 * API for the payment https://stripe.com/docs/api
    
Glossary Of Terms
* Visitor: Web user that is not registered or logged in. Can only orders/Cancel food orders.
* User: Web user that is registered and logged in. Can orders food, cancel orders, accumulate points for future savings, access to member promotions, rate the restaurant.
* Admin: Administrative web user. Can view and manage all pages, profiles, users, and site content
* Restaurants : list of restaurants
* Menu: Restaurant menu
* Menu Items: Restaurant menu item
* Order: Food orders

User Stories
* As a visitor when I complete the registration form I expect/want to be told that I have successfully registered and be directed to a welcome screen.
* As a user when I click on the forgot my password link I expect/want the ability to fill out my email address and have a password reset email that allows me to reset my password.
* As a user when I click the choose a menu item, I expect the menu item to be added to my orders
* As a user when I pay for the orders, I expect to get an email instead of a printed receipt
* As a user when I cancel the orders, I expect to get an email instead of a printed receipt
* As a user, when I decide to rate the restaurant, I expect my rating to be public and my comments may be either public or private

Features:
General
* Home Page
    *  Registration Button
    *  Login Button
    * Site description

* From the Register button...
    *  Fields for Username, Password, First/Last name, email, phone
    *  Checks for existing usernames/emails to avoid duplication
    *  Goes to next form depending on success

Registered users
* Login
    *  Username and Password
    *  Verifies username exists
    *  Verifies password matches
    *  Forwards to Restaurant Dashboard
* Restaurant Dashboard
    *  Ability to select a restaurant to orders from
* Menu Items dashboard
    * Select menu items
    * Checkout
* Checkout screen
    * Provide payment information
* Profile page
    * List past orders
    * Request a refund (nice to have)
    * Cancel an orders (nice to have)