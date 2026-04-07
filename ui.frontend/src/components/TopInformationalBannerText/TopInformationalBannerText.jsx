import React from 'react';
import './TopInformationalBannerText.css';

const TopInformationalBannerText=(props)=> {
    return(
       <div className="top-info"
       dangerouslySetInnerHTML={
        {
          __html: props.text || "Top Informational Banner Heading"
        }
       }
       />
    );
}

export default TopInformationalBannerText;