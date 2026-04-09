import React from 'react';
import './LycaFeature.css';

const LycaFeatureComponent = (props) => {

  return (
    

     <div className="lyca-feature-wrapper">
      <h1 className="lyca-feature-main-heading">{props.heading}</h1>

      <div className="lyca-feature-cards-container">
        {(props.items || []).map((item, index) => (
          <div key={index} className="lyca-feature-card">
            
            {item.icon && (
              <div className="icon-wrapper">
                <a href={item.iconLink || '#'}>
                  <img src={item.icon} alt={item.featureHeading || "icon"} className="icon-img" />
                </a>
              </div>
            )}

            
            <h4 className="feature-name">{item.featureHeading || "Feature Name Missing"}</h4>

           
            <div className="feature-desc"
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