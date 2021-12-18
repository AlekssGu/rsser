import React from 'react';

export function Vehicle(props: { vehicleData: any[]; }) {
    const vehicles = props.vehicleData.map((element, index) => (
        <div className="vehicle" key={index}>
            <a href={element.link}><img src={element.imageUrl} alt="vehicle" /></a>
            <div className="vehicle-info">
                <p><b>Pievienots:</b> {element.datePublished}</p>
                <p><b>Automašīna:</b> {element.make} {element.model}</p>
                <p><b>Izlaiduma gads:</b> {element.makeYear}</p>
                <p><b>Cena:</b> {element.price} EUR</p>
                <p><b>Motora tilp.:</b> {element.motorCapacity}</p>
                <p><b>Nobraukums:</b> {element.mileage}</p>
            </div>
            <hr />
        </div>
    ));
    return vehicles;
};

export default Vehicle;