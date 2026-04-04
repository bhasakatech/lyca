import React from 'react';
import './LycaHelpComponent.css';

const LycaHelpComponent = (props) => {
  console.log("PROPS:", props);
  console.log("icons array:", props.helpingIcons);
  return (
    <div className="help-container">
      <h1>{props.heading || "Heading"}</h1>
      <div className="help-icons">
        {Object.values(props.helpingIcons || {}).map((item, index) => (
          <div className="help-item" key={index}>
            {item.icon && (
              <a href={item.iconLink || '#'}>
                <img src={item.icon} alt={item.iconHeading || "icon"} />
              </a>
            )}
            <p>{item.iconHeading}</p>
          </div>
        ))}
      </div>
    </div>
  );
};
export default LycaHelpComponent;