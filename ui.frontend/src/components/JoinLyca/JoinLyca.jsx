import React, { useState } from "react";
import "./JoinLyca.css";

const JoinLyca = (props) => {
  const [mobile, setMobile] = useState("");
  const [code, setCode] = useState("+91");

  return (

    

    <div className="cta-card">

      {/* LEFT */}
      <div className="left">
        <h1 className="title">{props.title}</h1>
        <p>{props.description}</p>

        <div className="icons">
          {props.ctaItems?.map((item, i) => (
            <a key={i} href={item.link}>
              <img src={item.icon} alt={item.label} />
              <span>{item.label}</span>
            </a>
          ))}
        </div>
      </div>

          {/* RIGHT */}
<div className="right">
  <h2>Already with Lyca?</h2>

  {/* BUTTONS */}
  <div className="btn-group">
  <a href="/recharge" className="primary-btn">Recharge</a>
  <a href="/renew" className="secondary-btn">Renew plan</a>
</div>


  {/* INPUT WITH +1 */}

<div className="input-wrapper">
  <div className="input-group">
    
    {/* Country Code Dropdown */}
    <select
      className="code-dropdown"
      value={code}
      onChange={(e) => setCode(e.target.value)}
    >
      <option value="+1">+1</option>
      <option value="+91">+91</option>
      <option value="+44">+44</option>
      <option value="+90">+90</option>
    </select>

    {/* Divider */}
    <div className="divider"></div>

    <input
  className="mobile-input"
  value={mobile}
  onChange={(e) => setMobile(e.target.value)}
  placeholder={
    window.innerWidth <= 400
      ? "Enter mobile number"
      : "Enter lyca mobile number & get started"
  }
/>

    {/* Mobile Input */}
    {/* <input className="mobile-input"
      value={mobile}
      onChange={(e) => setMobile(e.target.value)}
      placeholder="Enter lyca mobile number & get started"
    /> */}
  </div>
</div>


  {/* PROCEED */}
  <a href="/proceed" className="proceed-btn">
  Proceed
</a>


  {/* FOOTER */}
  <p className="footer">
    Track your usage on the go!
    <a href="/download"> Download our app</a>
  </p>
  </div>

    </div>
  );
};
export default JoinLyca;


