# MultiplexManagementSystem

Design a multiplex management system that allows admins to manage multiplex, screens, seats, and movies played across those screens and customers to book the movie ticket.



Requirements



This multiplex management system is operational in only 1 city (Mumbai) which will have multiple multiplex buildings across various locations. (Exa: Central Mall, Inorbit Mall, etc…)



Each multiplex will have a few screens and each screen will have various categories of seats (silver, gold, platinum, etc...) based on the screen setup


Each screen will have some movie being played on it and this schedule can be configured along with the price of a seat for each category. Example:

Multiplex: Central Mall


Screen: 1


Movie: Movie Title


Time: 2 PM to 5 PM


Prices:


Silver: 150


Gold: 200


Platinum: 250


Provide filtering capability to check the availability of the seats for a given movie title or specific multiplex branch. Keep the implementation extensible so that new filters can be easily added with minimal effort on filtering logic. Only movie and multiplex filters need to be implemented. 


Example:

Movie = Movie Title


Multiplex = Central Mall


Provide the sorting capability to sort the results based on the cheapest show first strategy so that the cheapest show can be shown on the top. Keep the implementation extensible so that new sorters can be easily added with minimal effort on sorting logic. Only the cheapest show first sorting strategy needs to be implemented.



After checking the availability of seats the customer can perform the booking

Guidelines:

Use any IDE
Write a driver class for demo purposes. Which will execute all the commands in one place in the code and have test cases.
You should store the data in memory using a language-specific data structure.
You can print the output in the console. Do not create any UI for the application.
Design your application in a way that a new rental Strategy can be implemented and used instead of the default one (the lowest price).
Implement clear separation between your data layers and service layers.
Please prioritize code compilation, execution, and completion
Work on the expected output first and then add bonus features of your own.
You can take the startTime and endTime as DateTime or in any other way as you prefer.
The start time and end time will be in hourly granularity only.



Expectations:



Your code should cover all the mandatory functionalities explained above.
Your code should be executable and clean.
Your code should be properly refactored, and exceptions should be gracefully handled.
Please add any assumptions taken in a README file. Eg 1: Assume authentication and authorization are already taken care of. Eg 2: Assume you are building for a small business but extensible enough for future use cases


Evaluation Criteria:



Separation of concerns


Readable Code


Choices on data structures (lists vs sets, maps vs for)


Choices on patterns (repositories, abstractions)


Low-Level design


Functional correctness


Concurrency handling (Bonus Points)


Extension problems (Bonus Points)


Working code


Test Coverage (Bonus Points)


Language proficiency


Scalability



[execution time limit] 3 seconds (java)

[memory limit] 1 GB
