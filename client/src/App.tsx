import React from 'react';
import './App.css';
import MonthPointsList from "./MonthPointsList";

class App extends React.Component<{}, any> {

    constructor(props: any) {
        super(props);

    }

    render() {

        return (
            <div className="App">
                <div>
                    <h1>Spectrum Customer Rewards Point</h1>
                </div>
                <MonthPointsList/>
            </div>
        );
    }

}

export default App;
