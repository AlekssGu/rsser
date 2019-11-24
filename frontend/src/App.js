import React, {Component} from 'react';
import logo from './logo.png';
import './App.css';

class App extends Component {

    state = {};

    componentDidMount() {
        setInterval(this.setTime, 250);
        this.refreshRate = 30000;
        this.vehicles();
        this.timer = setInterval(() => this.vehicles(), this.refreshRate);
    }

    getRandomRefreshRate() {
        var min = 30000;
        var max = 50000;
        this.setState({ refreshRate: min + (Math.random() * (max-min))});
    }

    setTime = () => {
        fetch('/rss')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message});
            });
    };

    async vehicles() {
        fetch('/ss/cars/')
            .then(response => response.json())
            .then(response => response.map(element => {
                return <div className="vehicle">
                    <a href={element.link}><img src={element.imageUrl} /></a>
                    <div className="vehicle-info">
                        <p><b>Pievienots:</b> {element.datePublished}</p>
                        <p><b>Automašīna:</b> {element.make} {element.model}</p>
                        <p><b>Izlaiduma gads:</b> {element.makeYear}</p>
                        <p><b>Cena:</b> {element.price} EUR</p>
                        <p><b>Motora tilp.:</b> {element.motorCapacity}</p>
                        <p><b>Nobraukums:</b> {element.mileage}</p>
                    </div>
                    <hr/>
                    </div>
            }))
            .then(vehicles => {
                this.setState({vehicles: vehicles});
            });
    };

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">{this.state.message}</h1>
                </header>
                <div className="vehicles">{this.state.vehicles}</div>
            </div>
        );
    }
}

export default App;
