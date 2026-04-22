import React, { useState, useRef, useEffect } from 'react';
import './PrepaidSimPlans.css';
import { useHistory } from "react-router-dom";

const SimCard = ({ item,path }) => {
  const history = useHistory();
  const handleAddToBasket = (item) => {
  const existingBasket = JSON.parse(localStorage.getItem("basket")) || [];
  const updatedBasket = [...existingBasket, item];
  localStorage.setItem("basket", JSON.stringify(updatedBasket));
  window.dispatchEvent(new Event('basketUpdated'));
};
  return (
    <div className="prepaid_sim-card">
      <div className="prepaid_card-top-section">
        {item.planTitle && (
          <div className="prepaid_card-label">{item.planTitle}</div>
        )}
        <div className="prepaid_title-price-row">
          <div className="prepaid_title-data">
            <span className="prepaid_big-title">{item.subTitle}</span>
            <span className="prepaid_small-text">Data</span>
          </div>
          <div className="prepaid_price-info ">
            <div className="current-price">
              <span className="prepaid_amount">{item.price}</span>
            </div>
            <span className="prepaid_validity">{item.validity}</span>
          </div>
        </div>
      </div>

      <div className="prepaid_card-bottom-section">
        <ul className="prepaid_features-list">
          {item.features && item.features.map((feature, i) => (
            <li key={i}>
              <span className="prepaid_check-icon">
                <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="#22c55e" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                  <polyline points="20 6 9 17 4 12"></polyline>
                </svg>
              </span>
              {feature}
            </li>
          ))}
        </ul>

        {item.viewMoreLabel && (
          <a onClick={() => history.push(
            `${item.viewMoreLink ? item.viewMoreLink : "/content/lyca-spa-react/us/en/prepaid-plans/plan-details.html"}?cfPath=${encodeURIComponent(item.path)}&path=${encodeURIComponent(path)}`
          )} className="prepaid_view-more">{item.viewMoreLabel}</a>
        )}
        <div className="prepaid_card-actions">
          <button onClick={() => history.push(
            `${item.buyNowCtaLink? item.buyNowCtaLink : "/content/lyca-spa-react/us/en/prepaid-plans/buy-now.html"}?cfPath=${encodeURIComponent(item.path)}&path=${encodeURIComponent(path)}`
          )} className="prepaid_btn-buy">{item.buyNowCtaLabel}</button>
          <button onClick={() => handleAddToBasket(item)} className="prepaid_btn-basket">{item.addToBasketCtaLabel}</button>
        </div>
      </div>

      {item.footerText && (
        <div className="prepaid_card-footer-band">{item.footerText}</div>
      )}
    </div>
  );
};

const PrepaidSimPlans = (props) => {
  const data = props?.heading ? props : [];
  const basket = JSON.parse(localStorage.getItem('basket'));
  const path = window.location.pathname;
  const validToggles = data?.toggleSwitchButton || [];

  const [activeTab, setActiveTab] = useState(validToggles?.length > 0 ? validToggles[0] : '');
  const [isFilterOpen, setIsFilterOpen] = useState(false);
  const [sortOption, setSortOption] = useState('Price: Recommend');
  const dropdownRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsFilterOpen(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);


  if (!data || !data.prepaidSimPlanItems) return null;

  // Filter plans based on Active Tab
  let filteredPlans = data.prepaidSimPlanItems;



  if (activeTab) {
    filteredPlans = filteredPlans?.filter(plan => {
      if (plan.category == activeTab) {
        return plan;
      }
    });
  }

  return (
    <div className="prepaid-plans-container">
      <div className="prepaid-plans-header">
        <h2>{data.heading}</h2>
        <p>{data.description}</p>
      </div>

      <div className="prepaid-plans-controls">
        <div className="prepaid-plans-toggle">
          {validToggles?.map((btn, i) => (

            <button
              key={i}
              className={`prepaid_toggle-btn ${activeTab === btn ? 'active' : ''}`}
              onClick={() => setActiveTab(btn)}
            >
              {btn}
            </button>
          ))}
        </div>

      </div>

      <div className="prepaid-plans-grid">
        {/* Render Existing Customer Card on First Row Right if applicable, or just prepend/append based on data size */}
        {filteredPlans?.map((item, index) => {
          // Inserting existing customer card realistically at index 3 if we have enough items, or just render it linearly
          return <SimCard key={index} item={item} path={path}/>;
        })}

        {/* Existing Customer Card */}
        <div className="prepaid_sim-card existing-customer-card">
          <h3>{data.existingCustomTitle}</h3>
          <ul className="existing-options">
            {data.existingPlanOptions && data.existingPlanOptions?.map((opt, i) => (
              <li key={i}>
                <span className="prepaid_check-icon">✓</span> {opt}
              </li>
            ))}
          </ul>
          <div className="existing-customer-action">
            <a href={data.ctaLink} className="prepaid_arrow-link">→</a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PrepaidSimPlans;
