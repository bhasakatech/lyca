import React from 'react';
import './LycaExclusiveOffers.css';

const LycaExclusiveOffers = (props) => {
  return (
    <div className="exclusive-offers">
      
      <div className="exclusive-offers__container">
        
        <p className="exclusive-offers__heading">
          {props.heading}
        </p>

        <div className="exclusive-offers__form">
          
          <input
            type="text"
            className="exclusive-offers__input"
            placeholder={props.inputPlaceholderText}
          />

          <a href={props.ctaLink} className="exclusive-offers__link">
            <button className="exclusive-offers__button">
              {props.ctaLabel}
            </button>
          </a>

        </div>

        <p className="exclusive-offers__description" dangerouslySetInnerHTML={{ __html: props.description }}>
        </p>

      </div>

    </div>
  );
};

export default LycaExclusiveOffers;
