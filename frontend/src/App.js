import React, {Component} from 'react';
import logo from './logo.png';
import './App.css';

class App extends Component {

    state = {};

    componentDidMount() {
        this.refreshRate = 1000000;
        this.vehicles();
        this.vehicleWheels();
        this.timer = setInterval(() => this.vehicles(), this.refreshRate);
        this.timer = setInterval(() => this.vehicleWheels(), this.refreshRate);
    }

    async vehicleWheels() {
        fetch('/ss/car-wheels/')
            .then(response => response.json())
            .then(response => response.map(element => {
                return <div className="car-wheel">
                    <a href={element.link}><img src={element.imageUrl} /></a>
                    <div className="vehicle-info">
                        <p><b>Pievienots:</b> {element.datePublished}</p>
                        <p><b>Cena:</b> {element.price} EUR</p>
                        <p><b><a href={element.link}>Apskatīt</a></b></p>
                    </div>
                    <hr/>
                </div>
            }))
            .then(vehicleWheels => {
                this.setState({vehicleWheels: vehicleWheels});
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
                </header>
                <div className="container">
                    <ul className="nav nav-tabs" id="myTab" role="tablist">
                        <li className="nav-item" role="presentation">
                            <button className="nav-link active" id="wheels-tab" data-bs-toggle="tab"
                                    data-bs-target="#wheels" type="button" role="tab" aria-controls="wheels"
                                    aria-selected="true">Wheels
                            </button>
                        </li>
                        <li className="nav-item" role="presentation">
                            <button className="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#cars"
                                    type="button" role="tab" aria-controls="cars" aria-selected="false">Cars
                            </button>
                        </li>
                        <li className="nav-item" role="presentation">
                            <button className="nav-link disabled" id="contact-tab" data-bs-toggle="tab" data-bs-target="#real-estate"
                                    type="button" role="tab" aria-controls="real-estate" aria-selected="false" aria-disabled="true">Real estate
                            </button>
                        </li>
                    </ul>
                    <div className="row">
                        <div className="tab-content" id="myTabContent">
                            <div className="tab-pane fade show active" id="wheels" role="tabpanel" aria-labelledby="home-tab">
                                <div className="vehicleWheels">{this.state.vehicleWheels}</div>
                            </div>
                            <div className="tab-pane fade" id="cars" role="tabpanel" aria-labelledby="profile-tab">
                                <div className="vehicles">{this.state.vehicles}</div>
                            </div>
                            <div className="tab-pane fade" id="real-estate" role="tabpanel" aria-labelledby="contact-tab">...</div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;
