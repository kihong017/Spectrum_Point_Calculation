<<<<<<< HEAD
# Spectrum Point Calculation
This is a Rest web service for calculating points customers receive depending on how much they spend.
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction. (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

It also provides report on reward points earned for each customer per month and total.

## Data Sets Explain
* custid : Customer's ID (Assuming there are already customer ids exist). ex) 10001, 10002
* moneyspent : Money spent by a customer. ex) 100
* transdate : Date of Transaction. ex) "20200129"
* reportMonth : Month of reward points report. ex) "202001", "202008"
* point : Reward points

## REST Methods
|  | Description   | Request  | Response |
| --- | --- | --- | --- |
| GET | Get the reward points for a month | /monthpoint/{custid}/{transmonth} | {"reportMonth": "202001", "moneySpent": 120, "point": 90} |
| GET | Get the total points of a customer | /totalpoint/{custid} | {"reportMonth": "Total", "moneySpent": 180, "point": 100} |
| POST | Add a transaction | /customerTransaction {"custid": "10001", "moneyspent": "120", "transdate": "20200117"} | {"reportMonth": "202001", "moneySpent": 180,    "point": 100} |
| POST | Use the reward points  | /usePoints {"custid": "10001", "pointsspent": "100", "transdate": "20200117"} | {"reportMonth": "Total", "moneySpent": 200, "point": 50}  |

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

## Test Data Sets
| custid | moneyspent | transdate |
| --- | --- | --- | 
| 10001 | 120 | 20200101 |
| 10001 | 60 | 20200102 |
| 10001 | 40 | 20200119 |
| 10001 | 70 | 20200203 |
| 10001 | 90 | 20200206 |
| 10002 | 90 | 20200110 |
| 10002 | 60 | 20200112 |
| 10002 | 120 | 20200302 |
| 10003 | 90 | 20200110 |
| 10003 | 100 | 20200112 |
| 10003 | 120 | 20200402 |

## Built With
* Java 1.12 
* SpringBoot 1.4.1
* Maven
* Restful API

## Authors
* **Ki Hong "Daniel" Park** - [PersonalBlog](https://www.notion.so/kihong017/Leaving-Traces-5ece193b296e4ea494327207224c6ce2)
# Spectrum_Point_Calculation
=======
This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.<br />
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br />
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.<br />
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.<br />
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br />
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (Webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).
>>>>>>> Initial commit from Create React App
# Spectrum_Point_Calculation
