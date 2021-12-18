import React from 'react';

export default function Vehicle(props) {
    return (
            <div className="vehicle">
            <a href={props.element.link}><img src={props.element.imageUrl} alt="vehicle" /></a>
            <div className="vehicle-info">
                <p><b>Pievienots:</b> {props.element.datePublished}</p>
                <p><b>Automašīna:</b> {props.element.make} {props.element.model}</p>
                <p><b>Izlaiduma gads:</b> {props.element.makeYear}</p>
                <p><b>Cena:</b> {props.element.price} EUR</p>
                <p><b>Motora tilp.:</b> {props.element.motorCapacity}</p>
                <p><b>Nobraukums:</b> {props.element.mileage}</p>
            </div>
            <hr />
        </div>
    );
};