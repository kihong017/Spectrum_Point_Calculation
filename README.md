# Spectrum Point Calculation
This is a solution for **calculating rewards points** customers receive depending on how much they spend.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction. (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

It also provides report on reward points earned for each customer per month and total.

## Contents
This project is consists of two project.
* **client** - Front End
* **pointcalculation** - Back End

## Built With
* Java 1.8
* SpringBoot 1.4.1
* ReactJS
* Typescript
* Maven
* Restful API

# Front-End

### `npm install`

Once you download the project to local, run this command at the **client folder**.

### `npm start`
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

## Running the tests
* Test can be done by running the app using **npm start**
* The **back-end should be up and running** before starting the test

### Get Reward Points Report
1. Put the **user id** on the user id textbox, and **click search**.
![screen1](https://user-images.githubusercontent.com/34907404/73322543-98ddcb80-420a-11ea-9a6c-c60ca6a0f425.JPG)

2. You can see the reward points of the 3 months and the total points.
![screen2](https://user-images.githubusercontent.com/34907404/73322548-9aa78f00-420a-11ea-9bee-b5902ec4070b.JPG)

### Adding transactions to a customer
1. Put in the **user id, transaction date, and amount of money spent** on each textbox then click submit.
![addingpoint1](https://user-images.githubusercontent.com/34907404/73322553-9c715280-420a-11ea-8429-c64e1da3986c.JPG)

2. You can see the points are added if the money spent is **over $50**.
![addingpoint2](https://user-images.githubusercontent.com/34907404/73322556-9e3b1600-420a-11ea-9269-de0c3a265ce1.JPG)

# Back-End

## Data Sets Explain
* **custid** : Customer's ID (Assuming there are already customer ids exist). ex) 10001, 10002
* **moneyspent** : Money spent by a customer. ex) 100
* **transdate** : Date of Transaction. ex) "20200129"
* **reportMonth** : Month of reward points report. ex) "202001", "202008"
* **point** : Reward points

## Entities
* **Transaction** : Entity to be used when a transaction is added to a customer
* **RewardPointsReport** : Entity to be used to show a customer's current reward points

## Repository
* **TransactionRepository** : Repository that saves customer's transactions and reward points

## REST Methods
* Test the REST services by firing the below request to localhost:8080/request  [localhost:8080/request](http://localhost:8080)

|  | Description   | Request  | Response |
| --- | --- | --- | --- |
| GET | Get the point reports of a customer | /totalpoint/{custid} |  {"id":1,"custId":"example1","reportMonth":"202001","moneySpent":360.00,"points":270} |
| POST | Add a transaction | /customerTransaction {"custid": "10001", "moneyspent": "120", "transdate": "2020-01-17"} | {"id":1,"custId":"example1","reportMonth":"202001","moneySpent":360.00,"points":270} |

## Test Data Sets
| custid | moneyspent | reportMonth |
| --- | --- | --- | 
| example1 | 120 | 20200101 |
| example1 | 90 | 20200102 |
| example1 | 300 | 20200119 |

## Authors
* **Ki Hong "Daniel" Park** - [PersonalBlog](https://www.notion.so/kihong017/Leaving-Traces-5ece193b296e4ea494327207224c6ce2)
