# Anderson Eshop

##  Developed by :
1. Brendan MARAN    ID no.25530136
2. Deborah RAIMBAS  ID no.25530143
3. Valentino TOMON  ID no.25530376

##  Project description

Anderson Eshop is a professional, modern Android application designed for a grocery shopping experience. It features a fresh UI, robust product discovery, and a complete shopping flow from browsing to checkout.

##  Application Objectives
**1. Primary Goal**
- To develop a robust and user-friendly mobile e-commerce platform that allows customers to browse, manage, and purchase grocery and retail products (Bakery, Butchery, Drinks, etc.) from Anderson Eshop.

**2. Functional Objectives**
- Product Catalog Management: To provide an organized display of products categorized by type (e.g., Fruits, Vegetables, Bakery) using a centralized ProductCatalogue.
- Efficient Categorization: To enable users to filter products through a dedicated CategoryActivity, making it easier to find specific item groups.
- Shopping Cart System: To implement a persistent shopping cart (Cart and CartItem) that allows users to add, view, and modify items before final purchase.
- Seamless Checkout Process: To facilitate a structured transaction flow from the cart to a CheckoutActivity, culminating in a ConfirmationActivity.
- Data Integrity: To manage store data and customer information effectively using Object-Oriented principles (utilizing ShopData and Customer classes).

**3. User Experience (UX) Objectives**
- Branding & Engagement: To provide a professional first impression through a SplashActivity featuring the company logo and a welcome message.
- Personalization: To offer user-centric features such as a Dark Mode toggle in the MainActivity, allowing users to customize the interface based on their preference or environment.
-  Intuitive Navigation: To ensure a flat and simple navigation structure, allowing users to jump between the home screen, product lists, and their cart with minimal clicks.

 **4. Technical Objectives**
- Cross-Version Compatibility: To utilize AppCompatActivity and the AndroidX libraries to ensure the application remains functional and visually consistent across a wide range of Android OS versions.
- Maintainable Architecture: To apply Object-Oriented Programming (OOP) standards (Inheritance, Encapsulation) through classes like Product and its specialized subclasses (Drink, Bakery, etc.) for easier code scaling and updates.

##  Tech Stack

- **Platform:** Android
- **Language:** Java
- **UI Framework:** Material Design 3
- **Build System:** Gradle (AndroidX enabled)
- **Architecture:** Object-Oriented Programming (OOP) with clean separation of Data, Logic, and UI.

##  Features

- **Professional Home Screen:** A welcoming landing page with quick access to products, categories, and your shopping cart.
- **Dynamic Product Catalog:** 
  - Browse a wide variety of fresh essentials including Produce, Bakery, Dairy, Butchery, and Drinks.
  - **Real-time Search:** Instantly find products by name or description.
  - **Price Filtering:** Filter products by custom minimum and maximum price ranges.
  - **Category Filtering:** Quick-select chips to jump to specific departments with dynamic highlighting.
- **Smart Shopping Cart:**
  - Add/remove items directly from the product list.
  - Manage quantities with a professional +/- selector.
  - Persistent cart state across different screens.
- **Seamless Checkout:**
  - Modern, secure-looking checkout flow.
  - Delivery details input (Name, Phone, Location).
  - Order review with items and total calculation.
  - Cash on Delivery payment integration.
- **Dark Mode Support:** A system-wide theme toggle allows users to switch between a fresh light theme and a sleek dark theme.
- **Modern UI/UX:** Built using Material Design 3 components for a premium feel, including adaptive icons that match your phone's shape.

##  Project Structure

- `ProductActivity`: Handles the main product display, search, and filtering logic.
- `CartActivity`: Manages the user's selected items and total price.
- `CheckoutActivity`: Captures delivery information and finalizes the order.
- `ProductCatalogue`: The master data source for all store items.
- `ShopData`: Manages global application state (like the active cart).

##  How to Run

1.  Clone this repository.
2.  Open the project in **Android Studio**.
3.  Ensure you have an Android emulator or a physical device connected (API 24 or higher).
4.  Click the **Run** button (green play icon) in the toolbar.

## Screenshots
<img src="img0.jpg" alt="App Screenshot" width="200">  <img src="img2.jpg" alt="App Screenshot" width="200">  <img src="img3.jpg" alt="App Screenshot" width="200">  <img src="img4.jpg" alt="App Screenshot" width="200">  <img src="img5.jpg" alt="App Screenshot" width="200">  <img src="img6.jpg" alt="App Screenshot" width="200">  <img src="img7.jpg" alt="App Screenshot" width="200"> 

##  AI Assistance Declaration

AI tools were used to assist with the development of this project, including providing guidance, explanations, troubleshooting assistance, and suggestions for improving the application's structure and user interface. The project implementation, testing, and final decisions were reviewed and completed by the project members.

##  Prototype Note
This application is a prototype developed for an OOP Programming assignment. No real payments are processed, and it is intended for educational purposes.

---

