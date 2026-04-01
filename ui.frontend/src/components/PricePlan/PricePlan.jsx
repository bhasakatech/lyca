import React, { useState } from "react";
import "./PricePlan.css";

const plansData = [
  {
    title: "Starter",
    price: 5,
    data: "5GB Monthly Plan",
    features: [
      "1000 UK minutes & texts",
      "100 international minutes",
      "Unlimited streaming on Vodafone",
      "EU roaming included",
      "5G ready"
    ],
    highlight: false
  },
  {
    title: "Essential",
    price: 10,
    data: "30GB Monthly Plan",
    features: [
      "1000 UK minutes & texts",
      "100 international minutes",
      "Unlimited streaming on Vodafone",
      "EU roaming included",
      "5G ready",
      "Rollover data"
    ],
    highlight: true
  },
  {
    title: "Exclusive",
    price: 15,
    data: "50GB Monthly Plan",
    features: [
      "1000 UK minutes & texts",
      "100 international minutes",
      "Unlimited streaming on Vodafone",
      "EU roaming included",
      "5G ready",
      "Rollover",
      "Priority support"
    ]
  },
  {
    title: "Exclusive",
    price: 15,
    data: "85GB Monthly Plan",
    features: [
      "1000 UK minutes & texts",
      "100 international minutes",
      "Unlimited streaming on Vodafone",
      "EU roaming included",
      "5G ready",
      "Rollover",
      "Priority support"
    ]
  }
];

const PricePlan = (props) => {
  const [isYearly, setIsYearly] = useState(false);
  console.log("PROPS DATA:", props);
  return (
    <div className="pricing-container">
      <h2>{props.pricePlanHeading}</h2>
      
      {/* Toggle */}
      <div className="toggle">
        <span>{props.pricePlanMonthlyText}</span>
        <label className="switch">
          <input
            type="checkbox"
            onChange={() => setIsYearly(!isYearly)}
          />
          <span className="slider"></span>
        </label>
        <span>{props.pricePlanYearlyText}</span>
      </div>

      {/* Cards */}
      <div className="plans">
        {plansData.map((plan, index) => (
          <div
            key={index}
            className={`card ${plan.highlight ? "highlight" : ""}`}
          >
            {plan.highlight && <div className="badge">Popular Plan</div>}

            <h4>{plan.title}</h4>
            <h2>
              ${isYearly ? plan.price * 10 : plan.price}
              <span>{isYearly? "/year":"/Month"}</span>
            </h2>
            <p>{plan.data}</p>

            <ul>
              {plan.features.map((f, i) => (
                <li key={i}>✔ {f}</li>
              ))}
            </ul>

            <button className="btn">
              Choose Plan
            </button>
          </div>
        ))}
      </div>

      {/* Bottom Buttons */}
      <div className="bottom-actions">
        <button className="outline-btn" >{props.findPlanLabel}</button>
        <button className="primary-btn">{props.allPlanLabel}</button>
      </div>
    </div>
  );
};

export default PricePlan;