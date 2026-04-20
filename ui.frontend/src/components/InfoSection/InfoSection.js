import React from 'react';
import './InfoSection.css';

const InfoSection = ({ heading, description }) => {
    console.log("heading: ",heading);
    console.log("description: ",description)
  // Authoring Placeholder
  if (!heading && !description) {
    return (
      <div className="info-section-wrapper" style={{ padding: '20px', border: '1px dashed #cccccc', textAlign: 'center' }}>
        <strong>Please configure Info Section Component</strong>
      </div>
    );
  }

  return (
    <div className="info-section-wrapper">
      <div className="info-section-container">
        {heading && <h2 className="info-section-heading">{heading}</h2>}
        {description && (
          <div 
            className="info-section-description" 
            dangerouslySetInnerHTML={{ __html: description }} 
          />
        )}
      </div>
    </div>
  );
};

export default InfoSection;