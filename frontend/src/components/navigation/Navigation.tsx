import React from 'react';

export function Tabs() {
    return (
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
    );
};

export default Tabs;