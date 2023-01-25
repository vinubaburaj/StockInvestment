Please note that the jar file Stocks.jar needs to be in the same directory as the below folders
1. allUserPortfolios
2. dates
3. stocksData_csv
4. Commission.txt


Instruction on how to run the program from the jar file:

1. Open terminal on the folder which contains the jar file.
2. Type →      java -jar Stocks.jar
3. The console will show a menu. 
4. You can choose the respective option to work with a: inflexible portfolio, flexible portfolio with command line interface(CLI), flexible portfolio with graphical user interface(GUI)
5. After this the program will give you an option to create a new portfolio or to work with an existing portfolio.
6. Click on create portfolio to a create a new portfolio. Provide the portfolio name and the creation date.
7. In case of working with a CLI, you can add multiple stocks to a portfolio during creation until you hit 'quit'.
7. After creating a new portfolio, the program will again give you an option to create a new portfolio or to work with an existing portfolio.
8. Click on work with an existing portfolio and enter the portfolio name that you wish to work with.
9. You will now be presented with a list of options that you can perform on this portfolio.
10. Choose your respective option to perform actions on a portfolio or to fetch values from it.
11. For purchase stock and investment strategy option, you will be allowed to enter stocks in succession until you enter 'quit' (or click 'finish' on GUI).
12. You can choose to exit from the program once your required operations are performed.


Below is the list of stocks that this program supports:

1. Microsoft( Ticker: MSFT) 
2. Google( Ticker: GOOGL) 
3. Apple( Ticker: AAPL) 
4. IBM( Ticker: IBM) 
5. Morgan Stanley( Ticker: MS) 
6. Amazon.com Inc( Ticker: AMZN) 
7. Bank Of America Corp( Ticker: BAC) 
8. DoorDash Inc - Class A( Ticker: DASH) 
9. Etsy Inc( Ticker: ETSY) 
10. JPMorgan Chase & Company( Ticker: JPM) 
11. MongoDB Inc - Class A( Ticker: MDB) 
12. Netflix Inc( Ticker: NFLX) 
13. U.S. Gold Corp( Ticker: USAU) 
14. Vodafone Group plc( Ticker: VOD) 
15. Warner Bros. Discovery Inc - Class A( Ticker: WBD) 


Restrictions: 

Since there are IO operations and API data fetching going on in the backend, after creating an investment strategy, there is a slight load time where the user has to wait for the next view to load.

Dates supported for flexible portfolio: All days from the past 20 years are accepted. However, transactions can be made in chronological order only.

Dates supported for inflexible portfolio: All business days  between 2022-06-13 and 2022-11-02 are supported for an inflexible portfolio.
Please note that the program assumes that today’s date is 2022-11-02 for an inflexible portfolio (Due to limited data stored in the project data files.)

Please note that performance chart can be created only for spans more than or equal to 5 days.

There is no caching implemented as of now, so functionalities involving fetching data from API incur a higher fetch time.


The HTML Documentation is present inside the docs zip file.
