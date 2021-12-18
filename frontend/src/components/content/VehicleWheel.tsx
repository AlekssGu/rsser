import React from 'react';

export default function VehicleWheel(props) {
    return (
        <div className="car-wheel">
            <a href={props.element.link}><img src={props.element.imageUrl} alt="vehicle-wheel" /></a>
            <div className="vehicle-info">
                <p><b>Pievienots:</b> {props.element.datePublished}</p>
                <p><b>Cena:</b> {props.element.price} EUR</p>
                <p><b><a href={props.element.link}>Apskatīt</a></b></p>
            </div>
            <hr />
        </div>
    );
};
