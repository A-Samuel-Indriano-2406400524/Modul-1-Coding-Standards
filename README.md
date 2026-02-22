Reflection 2.1 (Module 2)
1. In this exercise, I selected 3 code quality issues from SonarCloud findings. The first issue was a critical severity
issue, which there were some duplicated literal "redirect:/product/list" in my code. I resolved it by extracting 
the value into a constant and reusing it in many of the related methods. The second issue was a critical severity
also, which there was an empty setup method. I resolved it by removing the empty setup method. The third one was a minor
severity, which I resolved by removing an unused MockBean import. My strategy was to prioritize high-severity issues
first, then apply some refactoring, and verify the result by rerunning the test.

2. I think my current implementation has met the definition of Continuous Integration. Every push and pull request
triggers an automated test execution, and SonarCloud analysis is also integrated, so code quality issues can be detected 
before changes are finalized. This gives fast feedback and helps prevent bad code from being merged. For deployment,
I also implemented CD using a pull-based approach with Koyeb. Koyeb is connected to my repository main
branch, then redeploys automatically when changes are merged to main branch.


Reflection 1.1 (Module 1)
1. Clean code  
I have learned to apply and understand clean code principles while writing my code. One of the main things
I focused on was using clear and descriptive variable names, so the variable names are self-explanatory
without needing any comments to explain. I also applied the principals of using functions, such as clear and 
descriptive names, and focus on one specific task.

2. Security  
I applied REST principles to ensure the security of my application. For example, I used GET only for 
retrieving data. Then I used POST for modifying data, such as create, edit, or deleting a product.

3. Mistakes  
A mistake I made several times was rushing to commit to Git without realizing that some of the logic
in my code were still incomplete. To prevent this from happening again, I'll try to be
more careful by checking the completeness of the logic and bug testing before finally committing to Git.

Reflection 1.2 (Module 1)
1. Implementing unit test felt quite difficult at first, but eventually I started to understand it.
I also feel more confident because unit test help to verify the correctness of my code logics. I feel
like the number of unit tests depends on how much the code coverage. For example, 90% code coverage is already
very good. However, 100% code coverage doesn't guarantee that there are no bugs or errors in my code.

2. If I create a new functional test suite with the same setup procedures and instance variables from
CreateProductFunctionalTest, the code become less clean because it can cause duplicated code.
Duplicated code can reduce code quality because if I need to change the logic, I have to update it in 
many places and could easily miss one place. To improve it, I should reuse the setup in a cleaner way,
such as putting the setup into a helper method, so each test suite that uses same setup is easier
to maintain.
