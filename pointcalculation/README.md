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
