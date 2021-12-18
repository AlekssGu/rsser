import React from 'react';
import Vehicle from './Vehicle';

export function VehicleWheels(props: { vehicleData: any[]; }) {
    const vehicleWheels = props.vehicleData.map((element, index) => (
        <Vehicle { ...{ element, index} } />
    ));

    return (
        <div className="tab-pane fade" id="cars" role="tabpanel" aria-labelledby="profile-tab">
            <div className="vehicles">
                { vehicleWheels }
            </div>
        </div>
    )

};

export default VehicleWheels;

