# Spectrum_Point_Calculation

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

### `npm start`

Runs the app in the development mode.<br />
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

## Running the tests
1. Add transactions by using addTransaction method (POST)
ex)
* http://localhost:8080/customerTransaction?custid=10001&moneyspent=200&transdate=20200101
* http://localhost:8080/customerTransaction?custid=10001&moneyspent=60&transdate=20200102
* http://localhost:8080/customerTransaction?custid=10001&moneyspent=40&transdate=20200119
* http://localhost:8080/customerTransaction?custid=10001&moneyspent=70&transdate=20200203
* http://localhost:8080/customerTransaction?custid=10001&moneyspent=200&transdate=20200303

2. Get the points of a customer on each months (GET)
ex)
* http://localhost:8080/monthpoint/10001/202001
* http://localhost:8080/monthpoint/10001/202002
* http://localhost:8080/monthpoint/10001/202003

3. Get the total points of the customer (GET)
* http://localhost:8080/totalpoint/10001

4. Use the points of the customer (POST)
* http://localhost:8080/usePoints??custid=10001&pointsspent=100&transdate=20200220

5. Check the total points of the customer again (GET)
* http://localhost:8080/totalpoint/10001


## Authors
* **Ki Hong "Daniel" Park** - [PersonalBlog](https://www.notion.so/kihong017/Leaving-Traces-5ece193b296e4ea494327207224c6ce2)
