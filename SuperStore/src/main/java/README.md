Instructions on setup,running and using the program:

Hey, in this brief document I will go over the basics of the SuperStore comand line app. :)
For the app itself to function, besides the imported libraries, a dependency was used (from org.apache.commons), since it was necesary for reading and using the data given in the CSV file.
The app itself is a basic menu-style aplication following the given specifications for the course project.

At the very beginning, after running the app, it will check the CSV file for noticeable errors, and will display the lines that have been incorrectly filled to the user so they can be fixed/edited, while skipping them completely in the code that follows.
The user will also be told how to use the app in a few short words, after which the title of the app will be displayed and the program itself will start.
The app functions are quite straightforward, navigating the menus is done by simply choosing a number when the options are enumerated or entering what the text is asking for.
All of the options have been tested to only accept correct input, when the input is incorrect, the user will either be asked to put in a new input, or in case of a misspelling it will simply respond by saying that there is no item that matches the requirements given by the user.

The first option (1) simply asks the user to put in a name of the person they are looking for and it returns the customers with that name, given that they are different people, by comparing their customer ID beforehand to insure no duplicates are displayed.
The second option (2) asks the user for a full name or the customer whos orders they want to see, after which it displays the orders if the name was valid.
After the orders are displayed, if the used wants to check out the specific details about the order, they are asked if they want to read into the order details or return to the Main Menu.
If they choose so, they can put in the Order ID, and if it matches one in the system, the program will show the amount of individual parts of the order that have been made with that ID (The same order ID means that the order is the same, but the customer has decided to buy multiple products at once, thus they are listed under the same order ID), after which it will display the information regarding the products ordered themselved which will be listed on the screen.
At the end of option two, the total sales for the order will be shown as well.
The user can then continue to input order IDs or decide to go back to the Main Menu.
The third option (3) represents some statistics regarding the data set, after choosing it, it will simply ask the user what statistics they are looking for and show a list of options.
The options are quite straightforward and will just return an answer with the desired information to the user.
The fourth option (4) simply creates a sales report in the form of a text file with the current date, showing the sales by area and number of sales/total sales made on it.
The fifth option (5) will simply exit the program.

Those are the outlines of the functionalities and design, again, all the inputs have been checked, and the current version has no noticeable errors.
If you do find some, please email me at nikola.simovic@tuni.fi and I will do my best to address these issues.

Lastly, if you wish to check out how the program works visually, the following link will lead you to my YouTube channel, where I explain the functions of the program in a more down-to-earth format:
https://youtu.be/ycWDPEKpCEk

And if you've made it this far, thank you for reading this and I hope you enjoy the app!




P.S. Hopefully all of the course final project requirements have been met, so assuming that's true, I self-evaluate this project as a 75 point work!