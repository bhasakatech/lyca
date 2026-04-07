import React from 'react';
import './LycaFeature.css';

const LycaFeatureComponent = (props) => {

  return (
    

     <div className="feature-wrapper">
      <h1 className="main-heading">{props.heading}</h1>

      <div className="cards-container">
        {(props.items || []).map((item, index) => (
          <div key={index} className="card">
            
            {item.icon && (
              <div className="icon-wrapper">
                <a href={item.iconLink || '#'}>
                  <img src={item.icon} alt={item.featureHeading || "icon"} className="icon-img" />
                </a>
              </div>
            )}

            
            <h2 className="feature-name">{item.featureHeading || "Feature Name Missing"}</h2>

           
            <div className="desc"
              dangerouslySetInnerHTML={
                {
                     __html:item.featureDescription || "Feature Description Missing"
                }
              }
              />
          </div>
        ))}
      </div>

    </div>
  );
};

export default LycaFeatureComponent;