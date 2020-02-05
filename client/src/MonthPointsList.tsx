import React from 'react';

class MonthPointsList extends React.Component<{}, any> {

    constructor(props: any) {
        super(props);

        this.state = {
            rewardpointsreports: [],
            isLoading: false,
            custid: '',
            moneyspent: 0,
            transdate: '',
        }

        this.handleChange = this.handleChange.bind(this);
        this.addPoints = this.addPoints.bind(this);
    }

    fetchData = () => {

        return fetch('http://localhost:8080//totalpoint/' + this.state.custid)
            .then(response => response.json())
            .then(data => this.setState({rewardpointsreports: data, isLoading: false}));
    }

    handleChange(event: React.FormEvent<HTMLFormElement>) {

        const target = event.currentTarget;

        var d = new Date(event.currentTarget.transdate.value);
        d.setMinutes(d.getMinutes() + d.getTimezoneOffset() );

        this.setState({custid: event.currentTarget.custid.value,
                             moneyspent: event.currentTarget.moneyspent.value,
                             transdate: d
        });

    }

    async addPoints(event: React.FormEvent<HTMLFormElement>) {

        event.preventDefault();

        const item = {
            custid: this.state.custid,
            moneyspent: this.state.moneyspent,
            transdate: this.state.transdate};

        await fetch("http://localhost:8080//customerTransaction", {
            method : 'POST',
            headers : {'Content-Type':'application/json'},
            body : JSON.stringify(item),
            })
            .then(res=>res.json())
            .then(data=>console.log(data));

        this.fetchData();

    }

    render() {
        const {rewardpointsreports, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div>
                <table>
                    <tr>
                        <th>Year and Month</th>
                        <th>Money Spent</th>
                        <th>Points</th>
                    </tr>
                    {rewardpointsreports.map((rewardpointsreport: any) =>
                        <tr key = {rewardpointsreport.reportMonth}>
                            <td>{rewardpointsreport.reportMonth}</td>
                            <td>{rewardpointsreport.moneySpent}</td>
                            <td>{rewardpointsreport.points}</td>
                        </tr>
                    )}
                </table>
                <br/>
                <br/>
                <form onSubmit={this.addPoints} onChange =  { this.handleChange }>
                    <b>User ID : </b>
                    <input type="text" name="custid" required></input>
                    <button type="button" onClick={this.fetchData}> Search </button>
                    <text>{"\n"}</text>
                    <br/>
                    <br/>
                    <b>Transaction Date : </b>
                    <input type="date" name="transdate" required/>
                    <text>  </text>
                    <b>Money Spent : </b>
                    <input type="number" name="moneyspent" min="0" step="0.01" inputMode="numeric" required/>
                    <input type="submit" value="Submit" />
                </form>
            </div>
        );
    }


}



export default MonthPointsList;
