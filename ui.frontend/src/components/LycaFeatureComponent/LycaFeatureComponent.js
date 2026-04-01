import React from 'react';
import './cards.css';

// export const CustomEditConfig = {
//   emptyLabel: "Lyca Feature Component",
//   isEmpty: function (props) {
//     return !props || !props.heading;
//   }
// };

const LycaFeatureComponent = (props) => {

  // if (CustomEditConfig.isEmpty(props)) {
  //   return null;
  // }

  return (
    

     <div className="feature-wrapper">
      
      {/* Heading */}
      <h1 className="main-heading">{props.heading}</h1>

      {/* Cards */}
      <div className="cards-container">
        {props.items.map((item, index) => (
          <div key={index} className="card">
            
            {/* Icon */}
            {item.icon && (
              <div className="icon-wrapper">
                <a href={item.iconLink || '#'}>
                  <img src={item.icon} alt={item.featureHeading || "icon"} className="icon-img" />
                </a>
              </div>
            )}

            {/* Title */}
            <h2 className="feature-name">{item.featureHeading || "Feature Name Missing"}</h2>

            {/* Description */}
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