For more details refer

https://stackoverflow.com/questions/4783063/configuring-spring-security-3-x-to-have-multiple-entry-points

Primary objective of this application is to achieve a single application with multiple set of users say customers and employee. Employees will have different table(employee_details) and customers will have different table(customer_details). Both are accessing same application but with different access levels or authorization.

Say, employee and customer make login to application with different form and hence needs to be validated credentials with different tables. 
- While logging in there should be an identifier such as radio button to determine whether admin is trying to login or customer is trying to login.
- But post login page will be same with different authorities. This example is well suited for this requirement.

Here we have two users<br>
1. admin/employee<br>
2. user/customer<br>

We have four cases to test
1. admin tries to login as admin with valid admin credentials.<br>
   - `Success` and App with admin access<br>
2. admin tries to login as customer with valid admin credentials.<br>
   - `Invalid credentials` message should be displayed as admin credentials not present in customer table<br>
3. user tries to login as user with valid user credentials.<br>
   - `Success` and App with user access.<br>
4. user tries to login as admin with valid user credentials.<br>
   - `Invalid credentials` message should be displayed as user credentials not present in admin table<br>

