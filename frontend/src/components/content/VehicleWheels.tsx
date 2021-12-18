import React from 'react';
import VehicleWheel from './VehicleWheel';

export function VehicleWheels(props: { vehicleWheelData: any[]; }) {
    const vehicleWheels = props.vehicleWheelData.map((element, index) => (
        <VehicleWheel { ...{ element, index} } />
    ));

    return (
        <div className="tab-pane fade show active" id="wheels" role="tabpanel" aria-labelledby="home-tab">
            <div className="vehicleWheels">
                { vehicleWheels }
            </div>
        </div>        
    )

};

export default VehicleWheels;