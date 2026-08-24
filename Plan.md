Main:
    Library...
        Objects:
            1. User List
            2. Book List
        
    User Class:
        - Need to register if the user not in the database.
        - If user already exists:
            - Permission to access the lib and books
        - If user:
            can search for the book
                by title
                by author
                by ISBN
            can borrow
            can return
            Can write a feedback to the book (if added to one book, should be displayed over all books)
        - If librarian:
            Can access transactions
            Can add comments to the borrowal
            Can add comments to the return
            Can grant/revoke access to the user
            Can update user details in the database
            Can change permissions of the user
        - If author:
    Book Class
        - some parts of book will have user info... (Not needed)
        - Just count and info abt the book
    Transaction Class
        - To list all the transactions
