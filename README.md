# Setup
1) Required docker to be installed
2) Run ./scripts/dockerScript.txt to setup env
   - DB: username=admin, password=password, port=5432, schema=postgres
3) Run ./scripts/sqlScript.txt to setup env after connected to db

# Explanation
1) Anything data related that need patching db will be using method POST. GET if only gets db data
2) Using postgres & docker for convenience setup test run
3) ISBN can be in 10 or 13 digits. For this app, i use 13 digits validation
4) In db, added 2 columns for book to keep track when book borrowed & returned


# APIs
### Book
1) <b>\<host>:8081/book/register </b>
    - Http status: 200, 400, 500
    - Method: POST
    - Request Body
        ```json
        {
            "isbn_number": "978-0-306-40615-7",
            "title": "Title book",
            "author": "Author book"
        }
        ```
    - Response Body
        ```json
        {
            "book_id": 1
        }
        ```
2) <b>\<host>:8081/book/list </b>
    - Http status: 200, 500
    - Method: GET
    - Response Body
        ```json
        [
          {
            "id": 1,
            "isbn_number": "978-0-306-40615-7",
            "title": "Book Title",
            "author": "Book Author"
          }
        ]
        ```

### Borrower
1) <b>\<host>:8081/borrower/register </b>
   - Http status: 200, 400, 500
   - Method: POST
   - Request Body
      ```json
      {
          "name": "Jason",
          "emailAddress": "jason@mail.com"
      }
      ```
- Response Body
    ```json
    {
        "borrower_id": 1
    }
    ```
2) <b>\<host>:8081/borrower/{book_id} </b>
    - Http status: 200, 400, 500
    - Method: POST
    - Response Body
        ```json
         {
            "status": "Successfully borrowed"
         }
        ```
3) <b>\<host>:8081/borrower/return </b>
    - Http status: 200, 400, 500
    - Method: POST
    - Request Body
      ```json
      {
        "name": "Jason",
        "author": "book author",
        "title": "Book title"
      }
      ```
   - Response Body
       ```json
       {
           "status": "Successfully returned"
       }
       ```

# Database (Postgres)
1) <b>tb_book</b>
    - id | name | email_address
2) <b>tb_borrower</b>
    - id | isbn_number | author | title