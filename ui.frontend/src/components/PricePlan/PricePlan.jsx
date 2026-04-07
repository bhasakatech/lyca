import React, { useState } from "react";
import "./PricePlan.css";

const PricePlan = (props) => {
  const [isYearly, setIsYearly] = useState(false);
  const [showAll, setShowAll] = useState(false);

  const plans = props.plans || [];

  // Show only 4 initially
  const visiblePlans = showAll ? plans : plans.slice(0, 4);

  return (
    <div className="pricing-container">
      <h2>{props.pricePlanHeading || "Plans"}</h2>

      {/* Toggle */}
      <div className="toggle">
        <span>{props.pricePlanMonthlyText || "Monthly"}</span>

        <label className="switch">
          <input
            type="checkbox"
            checked={isYearly}
            onChange={() => setIsYearly(prev => !prev)}
          />
          <span className="slider"></span>
        </label>

        <span>{props.pricePlanYearlyText || "Yearly"}</span>
      </div>

      {/* Cards */}
      <div className="plans">
        {visiblePlans.map((plan, index) => (
          <div
            key={plan.planTitle || index}
            className={`card ${plan.popular ? "highlight" : ""}`}
          >
            {plan.popular && (
              <div className="badge">Popular Plan</div>
            )}

            <h4>{plan.planTitle}</h4>

            <h2>
              ₹{isYearly ? plan.priceYearly : plan.priceMonthly}
              <span>{isYearly ? "/year" : "/month"}</span>
            </h2>

            <p>{plan.dataLimit}</p>

            <ul>
              {plan.features?.map((f, i) => (
                <li key={i}>✔ {f}</li>
              ))}
            </ul>

            <a
              href={plan.ctaLink}
              className="btn"
              target="_blank"
              rel="noopener noreferrer"
            >
              {plan.ctaLabel || "Select Plan"}
            </a>
          </div>
        ))}
      </div>

      {/* Bottom Buttons */}
      <div className="bottom-actions">
        {props.findPlanLink && (
          <a href={props.findPlanLink} className="outline-btn">
            {props.findPlanLabel || "Find a Plan"}
          </a>
        )}

        {plans.length > 4 && (
          <button
            className="primary-btn"
            onClick={() => {
              setShowAll(prev => !prev);

              // scroll up when collapsing
              if (showAll) {
                window.scrollTo({ top: 0, behavior: "smooth" });
              }
            }}
          >
            {showAll ? "Show Less" : props.allPlanLabel || "All Plans"}
          </button>
        )}
      </div>
    </div>
  );
};

export default PricePlan;