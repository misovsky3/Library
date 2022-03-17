# Library
Student project - library services management system

About the README
This README is used for the initial design of the Data project of the Library project.
It will be a library system that will make it possible to monitor, manage and lend books.
For the average user, it will be possible to borrow books either in the library or in the warehouse, or to view them in the reading room. As mentioned, he will be able to apply for a distance book loan, in addition, after the system announces, he can pick up the requested book. He will be able to return the book, and a fine will be sent to him if the book is not returned on time. If this happens the user will have to pay this fine.
It will be possible for the administrator or librarian to perform various library operations through the system. He will be able to manage readers, search for and manage books, classify them and create categories, manage prints of individual books and the warehouses in which the prints are stored.
In addition to all of the above, some statistics can be listed.



![image](https://user-images.githubusercontent.com/79416440/158895988-deb5ee2c-b9ba-4d52-be7b-0a65e266480f.png)

DESCRIPTION OF THE DATA MODEL
Brief description of database tables
accounts: There are readers and administrators (librarians) in the table - using the boolean value is_admin it can be recognized. In addition, it keeps the name, surname and duration of the registration
penalties: They are tied to accounts. There are fines in the table, it is possible to find out whether the fine is for delaying the return or for damaging the book or a combination. When borrowing a book, it is necessary to find out whether the reader has an unpaid fine. If he has, another book must not be lent to him
requests: They are also linked to accounts. Table stores, user book lending requirements. It contains the date the request was created and the end date in the event of a forfeiture. It is possible to find out whether the requested book (printout) has been borrowed.
rentals: They are also linked to accounts. Similar to the requests table, except that it records data not from the request but from the loan and whether the book has already been returned.
copies: The table stores physical copies or copies of books (from the books table). You can find out about the individual prints from the table whether the printout is in the library or in the warehouse, the condition of the book (value in the interval 0 to 10, which corresponds to the condition of the book, where 10 is new and the number 0 is destroyed) and whether the book is countable. (if it is not possible to view it only in the reading room in person)
books: Stores the title of the book and the author
categories: Books are categorized, with a different loan period for each category. The categories and rental periods are stored in this table.
warehouses: If the printout is stored in a warehouse, it will refer to the warehouse name stored in this table.
