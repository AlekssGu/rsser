import React from 'react';

export default function VehicleWheel(element: any, index: number) {
    return (
        <div className="car-wheel" key={index}>
            <a href={element.link}><img src={element.imageUrl} alt="vehicle-wheel" /></a>
            <div className="vehicle-info">
                <p><b>Pievienots:</b> {element.datePublished}</p>
                <p><b>Cena:</b> {element.price} EUR</p>
                <p><b><a href={element.link}>Apskatīt</a></b></p>
            </div>
            <hr />
        </div>
    );
};
