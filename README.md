# 🍳 RecipeFlow

A modern, visually polished Android application built with **Jetpack Compose** that allows users to search for delicious recipes based on ingredients currently in their kitchen. The project follows **Clean Architecture** guidelines and uses the **MVI (Model-View-Intent)** presentation pattern for unidirectional data flow.

---

## ✨ Features

* **Pantry-Based Search:** Input ingredients manually or select from popular chips to instantly discover matched recipes.
* **Dynamic Chip Filtering:** Popular ingredients dynamically disappear from the selection panel when added and return when removed.
* **Rich Recipe Metadata:** View detailed recipe screens complete with cooking times, servings profiles, ingredient lists, and instructions.
* **Persistent Favorites:** Bookmark recipes locally to save them for offline viewing.

---

## 🏗️ Architecture & Design Patterns

The project is decoupled into clear data, domain, and presentation layers to maintain strict separation of concerns, scalability, and testability:

* **Presentation Layer (MVI):** Handled completely via Jetpack Compose. ViewStates represent the exact UI status, while user actions are explicitly processed as `RecipeIntent` events handled by state-retaining ViewModels.
* **Domain Layer:** Contains pure Kotlin business rules, entity model definitions, and clean abstract UseCases (`SearchRecipesUseCase`).
* **Data Layer:** Leverages a **Repository Pattern** that manages network communication with the Spoonacular API and handles local caching through a Room Database abstraction.

---

## 🛠️ Tech Stack & Libraries

* **UI Framework:** Jetpack Compose (100% Declarative UI)
* **Asynchronous Flow:** Kotlin Coroutines & StateFlow
* **Dependency Injection:** Dagger Hilt
* **Networking:** Retrofit & OkHttp
* **Local Caching:** Room Database
* **Image Loading:** Coil Compose
* **Architecture Components:** Jetpack ViewModel & Navigation Component

---

## 🚀 API Optimization Strategy

During development, the search functionality was refactored from strict ingredient matching (`findByIngredients`) to semantic search filtering via Spoonacular's `complexSearch`. 

By configuring `addRecipeInformation = true` and limiting the request count footprint, the app fetches crucial structural metadata—such as preparation duration and serving counts—directly within a single network call, keeping API quota point consumption highly efficient.

---

<img width="411" height="880" alt="image" src="https://github.com/user-attachments/assets/5dda2350-3d4d-4c09-82a4-e1300f5590fd" /><img width="414" height="865" alt="image" src="https://github.com/user-attachments/assets/d4bb82de-dc4d-4a91-9410-8ba7ba866f4b" /><img width="407" height="879" alt="image" src="https://github.com/user-attachments/assets/ad488b93-8cb0-4868-8df6-560bdf793c25" />

<img width="438" height="879" alt="image" src="https://github.com/user-attachments/assets/6c50a6d3-dcfb-4070-ba33-7ca056b77e6a" />


