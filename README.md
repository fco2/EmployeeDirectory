## Build tools & versions used
- Android SDK build tools used: version 30.0.3

## Steps to run the app
- Open the project using Android studio and launch the EmployeeDirectory application using an emulator. 
- Currently, within the EmployeeDirectoryApi, there are three constants EMPLOYEES, MALFORMED_EMPLOYEES and EMPLOYEES_EMPTY which represent the 3 endpoints for getting employee data from
  the api. Replace each of these endpoints on line 7 for the retrofit Get request relative url with the needed constant for your test case. It is currently set to EMPLOYEES which returns
  all the employees for valid data. So, if you want to test for malformed employees end point, replace EMPLOYEES in line 7 with MALFORMED_EMPLOYEES. Then build and run the app again.

## What areas of the app did you focus on?
I focused on the following areas:
- Dependency Injection using Dagger-Hilt
- Clean Architecture
- UI using Jetpack Compose
- Data validation
- Unit testing

## What was the reason for your focus? What problems were you trying to solve?
- Dependency Injection was used to inject dependencies into the constructor of objects that needed them, in following with the Dependency Inversion SOLID design principle which says we
  should depend on abstractions and not concretions.
- Clean Architecture was utilized for the advantage it brings through ease of maintenance of app, ease of navigation through the app, decoupling of components of app and having the app be
  represented in an MVVM style.
- Jetpack compose was used to design the UI as it follows the declarative approach to creation of UI components and it presents components as functions which has the ability for re-use
  and less code duplication.
- Data validation was done at the repository level to ensure valid data was received from the api, and that other errors such as network errors were also handled and propagated 
  correctly to the UI layer.
- Unit testing was utilized to ensure functions and data validations worked as expected.

## How long did you spend on this project?
- About 7 hours over a span of 3 days.

## Did you make any trade-offs for this project? What would you have done differently with more time?
- I would have spent more time improving the UI and making the cards more user friendly.
- I should have written more unit tests for the viewModel.

### trade-offs
- The one trade-off made was not creating use-cases yet. The reason for this was that I didn't see the benefit /added value this would bring for just getting 
a list back from the api. Now, if this was a for getting a single employee, I can see the benefit of having a use case in that scenario to validate search query (employee information).

## What do you think is the weakest part of your project?
- I would say the fact that I do not have close to 100% test coverage. I did not test the functionality of the viewModel to ensure it behaves correctly (Although, this functionality was 
tested indirectly via the repository).This is due to the fact that my viewModel just calls instantiateAllEmployeesState function that populates the mutable state variable 
responsible for managing the employee list. Testing a function that does not return anything and is a coroutine is not very practical.
- Also, from the start of the project, I really should have been using git version control to preserve snapshots of my app at various working points as the correct development approach.

## Did you copy any code or dependencies? Please make sure to attribute them here!
- Used the Google accompanist library version 0.19.0 for swipe refresh functionality
- Used the Mockito-Kotlin dependency library version 2.0.0 to mock the api dependency needed for testing the EmployeeRepository.
- Used the Coil library dependency version 2.0.0-rc02 to load images from a URL.
- Used Dagger-Hilt library dependency version 2.40 for dependency injection.
- Used Retrofit 2 dependency library version 2.9.0 to get data from the api.

## Is there any other information you’d like us to know?
- I have implemented the swipe down to refresh employee list using the Google's accompanist swipeRefresh library.
  When you swipe down, a network call to the api is made to refresh the employee list.
- I utilized dto (data transfer objects) to get data from the api, and then mapped that result to a more concise object (Employee) in order to only get data points which the
  view/presentation layer needs.
- This app was built with focus on mobile devices.

  
  