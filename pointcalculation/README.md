## Data Sets Explain
* custid : Customer's ID (Assuming there are already customer ids exist). ex) 10001, 10002
* moneyspent : Money spent by a customer. ex) 100
* transdate : Date of Transaction. ex) "20200129"
* reportMonth : Month of reward points report. ex) "202001", "202008"
* point : Reward points

## Entities
* Transaction : Entity to be used when a transaction is added to a customer
* RewardPointsReport : Entity to be used to show a customer's current reward points

## Repository
* TransactionRepository : Repository that saves customer's transactions and reward points

## REST Methods
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
