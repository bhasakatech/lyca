import React, { useEffect, useState } from "react";
import './PrepaidSimPlanDetails.css';
import { useHistory } from "react-router-dom";
import Loader from "../Loader/Loader";
const PrepaidSimPlanDetails = () => {
   const history = useHistory();
  const [plan, setPlan] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    window.scrollTo(0, 0);
    const params = new URLSearchParams(window.location.search);
    const cfPath = params.get("cfPath");
     
    if (!cfPath) {
      setError("Content Fragment path not found in URL.");
      setLoading(false);
      return;
    }

    const fetchPlan = async () => {
      try {
        const url = `/graphql/execute.json/lyca-spa-react/prepaid-plan-details-by-path;cfPath=${cfPath}`;
        const response = await fetch(url, {
          method: "GET",
          credentials: "include"
        });

        const data = await response.json();

        if (!response.ok) {
          throw new Error(`Request failed with status ${response.status}`);
        }

        if (data.errors?.length) {
          throw new Error(data.errors[0].message || "GraphQL error occurred.");
        }

        const fetchedPlan = data?.data?.prepaidSimCfModelByPath?.item || null;

        if (!fetchedPlan) {
          throw new Error("No plan found for this path.");
        }

        setPlan(fetchedPlan);
      } catch (err) {
        console.error("Error fetching plan details:", err);
        setError(err.message || "Something went wrong.");
      } finally {
        setLoading(false);
      }
    };

    fetchPlan();
  }, []);

  if (loading) return <Loader message="Please Wait...." />
  if (error) return <div className="pd-wrapper-bg"><div className="pd-error">{error}</div></div>;
  if (!plan) return <div className="pd-wrapper-bg"><div className="pd-error">No plan details available.</div></div>;

  return (
    <div className="plan-details-wrapper">
      <div className="pd-container">
        
        {/* Left Side: Dummy Image Section mapped realistically */}
        <div className="pd-image-col">
          <img 
            src={plan.image._path || "https://pim-assets-paym.globalldplatform.com/_default_upload_bucket/PDP%20page%20banner_411X352_1x_5_11zon_3.webp"} 
            alt={plan.planTitle || "Sim Plan Offer"} 
            className="pd-offer-image"
          />
        </div>

        {/* Right Side: Exact typography, layout and structural replication */}
        <div className="pd-info-col">
          <div className="pd-badges-row">
            <span className="pd-yellow-badge">{plan.planLabel || "Free OPPO Voucher"}</span>
          </div>
          
          <div className="pd-icons-row">
            {plan.simImage._path && (
              <img src={plan.simImage._path} alt="SIM Badge" className="pd-badge-esim" />
            )}
            {plan.networkImage._path && (
              <img src={plan.networkImage._path} alt="Network Badge" className="pd-badge-5g " />
            )}
          </div>

          <div className="pd-title-row">
            <span className="pd-large-title">{plan.subTitle || "Data"}</span>
            <span className="pd-small-title">Data</span>
          </div>

          <div className="pd-price-row">
            <span className="pd-price-amount">{plan.price || "49.00"}</span>
            <span className="pd-validity">{plan.validity || "30 days"}</span>
          </div>

          <hr className="pd-divider" />

          {/* Heading just before features, mapped correctly */}
          <div className="pd-features-heading">{plan.planTitle || "Unlimited"}</div>

          <ul className="pd-features-list">
            {plan.features && plan.features.length > 0 ? (
              plan.features.map((feature, index) => {
                const featureText = typeof feature === 'object' ? (feature.plaintext || feature.title || JSON.stringify(feature)) : feature;
                return (
                  <li key={index}>
                    <span className="pd-check-icon">
                      <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#00d672" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
                        <polyline points="20 6 9 17 4 12"></polyline>
                      </svg>
                    </span>
                    <span className="pd-feature-text">{featureText}</span>
                  </li>
                );
              })
            ) : (
                <>
                  <li>
                    <span className="pd-check-icon">
                      <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#00d672" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
                        <polyline points="20 6 9 17 4 12"></polyline>
                      </svg>
                    </span>
                    <span className="pd-feature-text">Unlimited Talk & Text</span>
                  </li>
                  <li>
                    <span className="pd-check-icon">
                      <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#00d672" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
                        <polyline points="20 6 9 17 4 12"></polyline>
                      </svg>
                    </span>
                    <span className="pd-feature-text">Enjoy unlimited data with full-speed access for the first 40GB!</span>
                  </li>
                  <li>
                    <span className="pd-check-icon">
                      <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#00d672" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round">
                        <polyline points="20 6 9 17 4 12"></polyline>
                      </svg>
                    </span>
                    <span className="pd-feature-text">Unlimited International Minutes and Texts to 100 Countries</span>
                  </li>
                </>
            )}
          </ul>

          <div className="pd-buttons">
            <button onClick={() => history.push(
            `${plan.buyNowCtaLink._path ? plan.buyNowCtaLink._path : "/content/lyca-spa-react/us/en/prepaid-plans/buy-now.html"}?cfPath=${encodeURIComponent(plan.path)}`
          )} className="pd-btn-primary">{plan.buyNowCtaLabel || "Buy now"}</button>
            <button onClick={() => history.push(
            `${plan.viewMoreLink._path?.substring(0, plan.viewMoreLink._path.lastIndexOf('/')) + '.html'}`
          )} className="pd-btn-secondary">{"View all plans"}</button>
          </div>
        </div>

      </div>
    </div>
  );
};

export default PrepaidSimPlanDetails;
