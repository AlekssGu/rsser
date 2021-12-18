import React from 'react';
import Vehicle from './Vehicle';

export default function Vehicles(props: { vehicleData: any[]; }) {
    return (
        <div className="tab-pane fade" id="cars" role="tabpanel" aria-labelledby="profile-tab">
            <div className="vehicles">
                { 
                    props.vehicleData.map((element, index) => (
                        <Vehicle element={element} key={index} />
                    ))                
                }
            </div>
        </div>
    )

};