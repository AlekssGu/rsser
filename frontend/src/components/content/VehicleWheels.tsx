import React from 'react';
import VehicleWheel from './VehicleWheel';

export default function VehicleWheels(props: { vehicleWheelData: any[] })  {
    return (
        <div className="tab-pane fade show active" id="wheels" role="tabpanel" aria-labelledby="home-tab">
            <div className="vehicleWheels">
                {
                    props.vehicleWheelData.map((element, index) => (
                        <VehicleWheel element={element} key={index} />
                    ))
                }
            </div>
        </div>        
    );
    
};