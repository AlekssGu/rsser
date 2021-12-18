import React, {Component} from 'react';
import axios from 'axios';
import logo from './logo.png';
import './App.css';
import Vehicle from './components/Vehicle';
import VehicleWheel from './components/VehicleWheel';

class App extends Component {

    state = {
        vehicleData: [],
        vehicleWheelData: []
    }

    componentDidMount() {
        axios.get('/ss/cars/')
            .then(response => {
                this.setState({ vehicleData: response.data });
            });

        axios.get('/ss/car-wheels/')
            .then(response => {
                this.setState({ vehicleWheelData: response.data });
            });
    }

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
                                <div className="vehicleWheels">
                                    <VehicleWheel vehicleWheelData={this.state.vehicleWheelData} />
                                </div>
                            </div>
                            <div className="tab-pane fade" id="cars" role="tabpanel" aria-labelledby="profile-tab">
                                <div className="vehicles">
                                    <Vehicle vehicleData={this.state.vehicleData} />
                                </div>
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
