import React, {Component} from 'react';
import axios from 'axios';
import './App.css';
import Vehicles from '../content/Vehicles';
import VehicleWheels from '../content/VehicleWheels';
import Header from '../header/Header';
import Tabs from '../navigation/Navigation';

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
                <Header />
                <div className="container">
                    <Tabs />
                    <div className="row">
                        <div className="tab-content" id="myTabContent">
                            <VehicleWheels vehicleWheelData={this.state.vehicleWheelData} />
                            <Vehicles vehicleData={this.state.vehicleData} />
                            <div className="tab-pane fade" id="real-estate" role="tabpanel" aria-labelledby="contact-tab">...</div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;
