import React from 'react';
import "./LycaMobileAppComponent.css";

const LycaMobileAppComponent=(props)=>{
    console.log("the props: ",props)
    return(
        
<div className="app-component">
    <div class="left-section">
        <h2 className="lyca-mobile-app-text">
            {props.mainHeading}
            </h2>
        <ul className="lyca-mobile-app-features">
            {props.mobileAppFeatures?.map((featureItem,index)=>(
              <li key={index}>{featureItem}</li>
            )) }  
        </ul>
        <div className="lyca-mobile-app-appStore">
            {(props.appStore|| []).map((appStoreItem,index)=>
               <div key={index} >
                <a href={appStoreItem.appStoreLink || '#'}>
                    <img src={appStoreItem.appStoreIcon} alt="App Store Icon" className="lyca-mobile-app-store-icons"/>
                </a>
                </div>
            )}
        </div>
    </div>
            <div className="right-section">
                <div className="qr-container">
                     <p className="qr-text">{props.qrText}</p>
                     <div className="qr-box">
                         <img src={props.qrImage} alt={props.qrText}/>
                    </div>
                </div>
                <img src={props.mobileImage} alt={props.qrText} className="lyca-mobile-Image"/>
            </div>
</div>
        
    );
};
export default LycaMobileAppComponent;